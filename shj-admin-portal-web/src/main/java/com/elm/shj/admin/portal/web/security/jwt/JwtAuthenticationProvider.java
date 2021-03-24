/*
 * Copyright (c) 2017 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.security.jwt;

import com.elm.dcc.foundation.providers.recaptcha.exception.RecaptchaException;
import com.elm.dcc.foundation.providers.recaptcha.model.RecaptchaInfo;
import com.elm.dcc.foundation.providers.recaptcha.service.RecaptchaService;
import com.elm.shj.admin.portal.services.dto.UserDto;
import com.elm.shj.admin.portal.services.dto.UserPasswordHistoryDto;
import com.elm.shj.admin.portal.services.user.PasswordHistoryService;
import com.elm.shj.admin.portal.services.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * JWT based Authentication provider used for Spring Security authentication process
 *
 * @author Aymen Dhaoui <adhaoui@elm.sa>
 * @since 1.0.0
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final String RECAPTCHA_RESPONSE_PARAM_NAME = "grt";

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RecaptchaService recaptchaService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Value("${login.failed.max.attempts}")
    private int allowedFailedLogins;

    @Autowired
    private PasswordHistoryService passwordHistoryService;

    @Value("${dcc.validation.password.expires.in.months}")
    private int passwordAgeInMonths;

    @Value("${login.simultaneous.enabled}")
    private boolean simultaneousLoginEnabled;


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(noRollbackFor = {BadCredentialsException.class, RecaptchaException.class})
    public Authentication authenticate(final Authentication authentication) {
        logger.debug("starting authentication process");
        long idNumber = Long.parseLong(authentication.getName());
        String password = (String) authentication.getCredentials();

        UserDto user = userService.findByNin(idNumber).orElseThrow(() ->
                // throw RecaptchaException to prevent DOS attack in case of idNumberStr is not exist
                new RecaptchaException("idNumber not found."));
        // check if user is active
        if (!user.isActivated()) {
            logger.info("User {} is deactivated.", user.getNin());
            throw new BadCredentialsException("invalid credentials.");
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        if (!BCrypt.checkpw(password, user.getPasswordHash())) {
            userService.updateLoginTries(user);
            if (user.getNumberOfTries() >= allowedFailedLogins) {
                throw new RecaptchaException("Requires captcha.");
            }
            logger.debug("wrong password.");
            throw new BadCredentialsException("invalid credentials.");
        }

        // Check captcha
        if (user.getNumberOfTries() > allowedFailedLogins) {
            String recaptchaResponse = request.getParameter(RECAPTCHA_RESPONSE_PARAM_NAME);
            if (StringUtils.isBlank(recaptchaResponse)) {
                throw new RecaptchaException("Invalid captcha.");
            }
            RecaptchaInfo recaptchaInfo;
            try {
                recaptchaInfo = recaptchaService.verifyRecaptcha(request.getRemoteAddr(), recaptchaResponse);
            } catch (IllegalArgumentException e) {
                throw new RecaptchaException("Invalid character in captcha response.");
            }
            if (recaptchaInfo == null || !recaptchaInfo.isSuccess()) {
                userService.updateLoginTries(user);
                throw new RecaptchaException("Invalid captcha.");
            }
        }

        // check if user is already logged in if simultaneous login flag is disabled
        if (!simultaneousLoginEnabled) {
            Date oldTokenExpiryDate = user.getTokenExpiryDate();
            if (oldTokenExpiryDate != null) {
                LocalDateTime oldTokenExpiryDateTime = oldTokenExpiryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                if (oldTokenExpiryDateTime.isAfter(LocalDateTime.now())) {
                    // old token is still active, simultaneous login is not allowed
                    throw new BadCredentialsException("User is already logged in");
                }
            }
        }

        // check if the password expired
        boolean passwordExpiredFlag = true;
        //each user should have a password history, for newly registered user, change password is required in the first login
        Optional<UserPasswordHistoryDto> userPasswordHistory = passwordHistoryService.findLastByUserId(user.getId());
        if (userPasswordHistory.isPresent()) {
            //check the date compared with configured password age
            LocalDate passwordCreationDate = userPasswordHistory.get().getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            passwordExpiredFlag = passwordCreationDate.plusMonths(passwordAgeInMonths).isBefore(LocalDate.now()) || user.isChangePasswordRequired();
        }

        List<String> grantedAuthorities = new ArrayList<>();
        Set<Long> userRoleIds = new HashSet<>();
        user.getUserRoles().forEach(userRoleDto -> {
            userRoleIds.add(userRoleDto.getId());
            userRoleDto.getRole().getRoleAuthorities().forEach(roleAuthorityDto -> {
                grantedAuthorities.add(roleAuthorityDto.getAuthority().getCode());
            });
        });
        // generate the token
        String token = jwtTokenService.generateToken(idNumber, grantedAuthorities, user.getId(), passwordExpiredFlag, userRoleIds, request);
        logger.debug("generated token for {} is {}", idNumber, token);
        // save user login info
        userService.updateUserLoginInfo(user.getId(), jwtTokenService.retrieveExpirationDateFromToken(token).orElse(new Date()));

        return new JwtToken(token, authentication, AuthorityUtils.createAuthorityList(grantedAuthorities.toArray(new String[]{})), passwordExpiredFlag, user.getFirstName(), user.getFamilyName(), user.getUserRoles());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Class<?> aClass) {
        return JwtToken.class.isAssignableFrom(aClass);
    }

}
