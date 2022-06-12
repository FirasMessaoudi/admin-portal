/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.security.otp;

import com.elm.dcc.foundation.providers.recaptcha.exception.RecaptchaException;
import com.elm.dcc.foundation.providers.recaptcha.model.RecaptchaInfo;
import com.elm.dcc.foundation.providers.recaptcha.service.RecaptchaService;
import com.elm.shj.admin.portal.services.dto.UserDto;
import com.elm.shj.admin.portal.services.otp.OtpService;
import com.elm.shj.admin.portal.services.user.UserService;
import com.elm.shj.admin.portal.web.error.DeactivatedUserException;
import com.elm.shj.admin.portal.web.error.UserAlreadyLoggedInException;
import com.elm.shj.admin.portal.web.security.jwt.JwtToken;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Custom Authentication Provider to construct and OtpToken with a fresh generated pin
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private static final String RECAPTCHA_RESPONSE_PARAM_NAME = "grt";
    private static final long WS_USER_ID = 2;
    private static final long HUIC_USER_ID = 3;

    private final UserService userService;
    private final OtpService otpService;
    private final JwtTokenService jwtTokenService;
    private final RecaptchaService recaptchaService;

    @Value("${login.failed.max.attempts}")
    private int allowedFailedLogins;

    @Value("${login.simultaneous.enabled}")
    private boolean simultaneousLoginEnabled;


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(noRollbackFor = {BadCredentialsException.class, RecaptchaException.class})
    public Authentication authenticate(final Authentication authentication) {
        log.debug("starting authentication process");

        long idNumber = Long.parseLong(authentication.getName());
        String password = (String) authentication.getCredentials();

        UserDto user = userService.findByNin(idNumber).orElseThrow(() ->
                // throw RecaptchaException to prevent DOS attack in case of idNumberStr is not exist
                new RecaptchaException("idNumber not found."));
        // check if user is active
        if (!user.isActivated()) {
            log.info("User {} is deactivated.", user.getNin());
            throw new DeactivatedUserException("User is not active.");
        }

        if (!BCrypt.checkpw(password, user.getPasswordHash())) {
            if (!isWsUser(user.getId())) {
                userService.updateLoginTries(user);
                if (user.getNumberOfTries() >= allowedFailedLogins) {
                    throw new RecaptchaException("Requires captcha.");
                }
            }
            log.debug("wrong password.");
            throw new BadCredentialsException("invalid credentials.");
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        if (!isWsUser(user.getId()) && !isHuicUser(user.getId())) {
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
        }

        // check if user is already logged in if simultaneous login flag is disabled
        if (!simultaneousLoginEnabled && !isWsUser(user.getId()) && !isHuicUser(user.getId())) {
            Date oldTokenExpiryDate = user.getTokenExpiryDate();
            if (oldTokenExpiryDate != null) {
                LocalDateTime oldTokenExpiryDateTime = oldTokenExpiryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                if (oldTokenExpiryDateTime.isAfter(LocalDateTime.now())) {
                    // old token is still active, simultaneous login is not allowed
                    throw new UserAlreadyLoggedInException("User is already logged in");
                }
            }
        }

        // in case of WS user return JwtToken directly, otherwise return an OtpToken
        if (isWsUser(user.getId()) || isHuicUser(user.getId())) {
            List<String> grantedAuthorities = new ArrayList<>();
            Set<Long> userRoleIds = new HashSet<>();
            user.getUserRoles().forEach(userRoleDto -> {
                userRoleIds.add(userRoleDto.getId());
                userRoleDto.getRole().getRoleAuthorities().forEach(roleAuthorityDto -> {
                    grantedAuthorities.add(roleAuthorityDto.getAuthority().getCode());
                });
            });

            // generate the token
            String token = jwtTokenService.generateToken(idNumber, grantedAuthorities, user.getId(), false, userRoleIds, request);
            log.debug("generated token for {} is {}", idNumber, token);

            // save user login info
            userService.updateUserLoginInfo(user.getId(), jwtTokenService.retrieveExpirationDateFromToken(token).orElse(new Date()));

            return new JwtToken(token, authentication, AuthorityUtils.createAuthorityList(grantedAuthorities.toArray(new String[]{})), false, user.getFirstName(), user.getFamilyName(), user.getId(), user.getUserRoles());
        }

        // generate OTP for the given principal

        String otp = otpService.createOtp(Long.toString(idNumber), "+966", String.valueOf(user.getMobileNumber()));
        log.debug("###################### OTP for [{}] : {}", idNumber, otp);

        String maskedMobileNumber = user.getMobileNumber() == null ? null : Integer.toString(user.getMobileNumber()).replaceAll("\\b\\d+(\\d{3})", "*******$1");
        String maskedEmail = user.getEmail() == null ? null : user.getEmail().replaceAll("\\b(\\w{2})[^@]+@(\\w{2})\\S+(\\.[^\\s.]+)", "$1***@$2****$3");

        // return the Otp Token
        return new OtpToken(true, otpService.getOtpExpiryMinutes(), authentication.getPrincipal(), user.getFirstName(), user.getFamilyName(), maskedMobileNumber, maskedEmail);
    }

    /**
     * Check logged in user id is WS user or not
     *
     * @param userId
     * @return true if it is WS user
     */
    private boolean isWsUser(long userId) {
        return userId == WS_USER_ID;
    }

    /**
     * Check logged in user id is HUIC user or not
     *
     * @param userId
     * @return true if it is HUIC user
     */
    private boolean isHuicUser(long userId) {
        return userId == HUIC_USER_ID;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(OtpToken.class);
    }
}
