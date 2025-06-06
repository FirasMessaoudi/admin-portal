/*
 * Copyright (c) 2017 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.user;

import com.elm.dcc.foundation.providers.email.service.EmailService;
import com.elm.shj.admin.portal.orm.entity.JpaUser;
import com.elm.shj.admin.portal.orm.repository.RoleRepository;
import com.elm.shj.admin.portal.orm.repository.UserRepository;
import com.elm.shj.admin.portal.services.dto.RoleDto;
import com.elm.shj.admin.portal.services.dto.UserDto;
import com.elm.shj.admin.portal.services.dto.UserRoleDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.sms.HUICSmsService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.SSLException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Service handling user management operations
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService extends GenericService<JpaUser, UserDto, Long> {

    public static final String CREATE_USER_SMS_NOTIFICATION_KEY = "user.mngt.new.user.sms.notification";
    public static final String RESET_PASSWORD_SMS_NOTIFICATION_KEY = "reset.password.sms.notification";
    public static final String REGISTRATION_EMAIL_SUBJECT = "Welcome to Hajj App Platform مرحبا بك في منصة تطبيق الحج";
    public static final String REGISTRATION_EMAIL_TPL_NAME = "email-registration.ftl";
    public static final String RESET_PASSWORD_EMAIL_SUBJECT = "Reset User Password إعادة تعيين كلمة السر";
    public static final String RESET_PASSWORD_EMAIL_TPL_NAME = "email-reset-password.ftl";
    private static final long SYSTEM_USER_ROLE_ID = 2L;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final HUICSmsService huicSmsService;
    private final EmailService emailService;
    private final HttpServletRequest request;

    /**
     * Finds all non deleted users.
     *
     * @param pageable
     * @param loggedInUserId
     * @return
     */
    public Page<UserDto> findAllNotDeleted(Pageable pageable, long loggedInUserId) {
        // exclude system users in returned list
        return mapPage(userRepository.findDistinctByDeletedFalseAndIdNotAndUserRolesRoleIdNotIn(pageable, loggedInUserId, RoleRepository.EXCLUDED_USERS_ROLES_ID_LIST));
    }

    /**
     * Finds a user by his nin
     *
     * @param nin the nin of the user to find
     * @return the founded user or empty structure
     */
    public Optional<UserDto> findByNin(long nin) {
        JpaUser user = userRepository.findByNinAndDeletedFalseAndUserRolesRoleDeletedFalseAndUserRolesRoleActivatedTrue(nin);
        return (user != null) ? Optional.of(getMapper().fromEntity(user, mappingContext)) : Optional.empty();
    }

    /**
     * finds users by role id, nin or account status
     *
     * @param roleId              the user role id to find
     * @param nin                 the user nin to find
     * @param activated           the user account status
     * @param loggedInUserRoleIds
     * @return the found users or <code>null</code>
     */
    public Page<UserDto> searchByRoleStatusOrNin(Pageable pageable, Long roleId, String nin, Boolean activated, long loggedInUserId, Set loggedInUserRoleIds) {
        //TODO: need to review the exclude part.
        return mapPage(userRepository.findByRoleOrNinOrStatus(pageable, roleId, (nin == null ? null : "%" + nin + "%"), activated, loggedInUserId,
                // exclude system users in returned list
                (loggedInUserRoleIds.contains(RoleRepository.SYSTEM_ADMIN_USER_ROLE_ID)) ? null : RoleRepository.SYSTEM_ADMIN_ROLE_ID));
    }

    /**
     * Deletes a user from the system
     *
     * @param userId the id of the user to delete
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void deleteUser(long userId) {
        userRepository.markDeleted(userId);
    }

    /**
     * Activate inactive user
     *
     * @param userId
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void activateUser(long userId) {
        userRepository.activate(userId);
    }

    /**
     * Deactivate active user
     *
     * @param userId
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void deactivateUser(long userId) {
        userRepository.deactivate(userId);
    }

    /**
     * Retrieves PasswordHash from DB
     *
     * @param idNumber the id number of the user to retrieve
     * @return the user's PasswordHash
     */
    public String retrievePasswordHash(long idNumber) {
        return userRepository.retrievePasswordHash(idNumber);
    }

    /**
     * Clears user token from the system
     *
     * @param userId      the user id to update
     * @param tokenExpiry the token expiry
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateUserLoginInfo(long userId, Date tokenExpiry) {
        userRepository.updateLoginInfo(0, new Date(), new Date(), tokenExpiry, userId);
    }

    /**
     * Updates user in the system
     *
     * @param idNumber the id number of the user to update
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateUserPassword(long idNumber, String passwordHash) {
        userRepository.updatePassword(idNumber, passwordHash);
    }

    /**
     * Clears user token from the system
     *
     * @param idNumber the id number of the user to delete
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void clearToken(long idNumber) {
        userRepository.clearToken(idNumber);
    }

    /**
     * Checks if user has a token in the system
     *
     * @param idNumber the id number of the user
     * @return whether user has a token in the system
     */
    public boolean hasToken(long idNumber) {
        Date tokenExpiryDate = userRepository.retrieveTokenExpiryDate(idNumber);
        log.info("Token Expiry Date {} found for {}", tokenExpiryDate, idNumber);
        return tokenExpiryDate != null;
    }

    /**
     * Update user avatar
     *
     * @param userId the user id
     * @param avatar the user avatar
     * @return Encoded avatar string
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public String updateUserAvatar(long userId, byte[] avatar) {
        String encodedAvatarStr = Base64.getEncoder().encodeToString(avatar);
        userRepository.updateAvatar(userId, encodedAvatarStr);
        return encodedAvatarStr;
    }

    /**
     * Creates a new user
     *
     * @param user the user information to save
     * @return UserDto saved one
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public UserDto createUser(UserDto user, boolean selfRegistration) {
        user.setPassword(generatePassword());
        // encode the password
        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        // set default values for new user
        user.setId(0);
        user.setBlockDate(null);
        user.setBlocked(false);
        user.setChangePasswordRequired(true);
        user.setDeleted(false);
        user.setActivated(true);
        user.setLastLoginDate(null);
        user.setNumberOfTries(0);
        user.setUpdateDate(null);
        user.setCreationDate(new Date());
        if (selfRegistration) {
            RoleDto rDTO = new RoleDto();
            rDTO.setId(SYSTEM_USER_ROLE_ID);
            UserRoleDto userRoleDto = constructNewUserRoleDTO(user, rDTO);
            Set userRoles = new HashSet<UserRoleDto>();
            userRoles.add(userRoleDto);
            user.setUserRoles(userRoles);
        } else {
            user.getUserRoles().forEach(userRole -> {
                userRole.setUser(user);
                userRole.setCreationDate(new Date());
                userRole.setId(0);
            });
        }
        //update UserRole objects
        user.getUserRoles().forEach(userRole -> {
            userRole.setUser(user);
            userRole.setCreationDate(new Date());
            userRole.setId(0);
        });
        // save user information
        UserDto savedUser = save(user);
        // user created successfully, send SMS notification which contains the temporary password and Email which contains the NIN
        boolean notificationSent = notifyRegisteredUser(user);

        return savedUser;
    }

    /**
     * Updates the number of login tries, count failed login
     *
     * @param user the user to update
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateLoginTries(UserDto user) {
        userRepository.updateLoginTries(user.getId(), user.getNumberOfTries() + 1, new Date());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public UserDto save(UserDto user) {
        // convert avatar file to encoded bytes
        if (user.getAvatarFile() != null && !user.getAvatarFile().isEmpty()) {
            try {
                user.setAvatar(Base64.getEncoder().encodeToString(user.getAvatarFile().getBytes()));
            } catch (IOException e) {
                log.error("Error while setting avatar bytes for user.", e);
            }
        }
        return super.save(user);
    }

    /**
     * Generate new password for the user
     *
     * @return generated password
     */
    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%&*-_+";
        return RandomStringUtils.random(8, characters);
    }

    /**
     * Check if Saudi or not
     *
     * @param idNumber
     * @return
     */
    private boolean isCitizen(long idNumber) {
        return Long.toString(idNumber).startsWith("1");
    }

    /**
     * Reset user password.
     *
     * @param user
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void resetPassword(UserDto user) {
        String newPassword = generatePassword();
        user.setPassword(newPassword);
        // first notify user with the new password, if notification failed, password will not be changed
        if (!notifyUserOnPasswordReset(user)) {
            log.error("Password reset cannot be done, unable to notify user with the new password.");
            return;
        }

        String updatedPwd = passwordEncoder.encode(newPassword);
        userRepository.resetPwd(user.getId(), updatedPwd, true);
    }


    protected UserRoleDto constructNewUserRoleDTO(UserDto user, RoleDto rDTO) {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setUser(user);
        userRoleDto.setRole(rDTO);
        userRoleDto.setMainRole(true);

        return userRoleDto;
    }

    /**
     * Send SMS and Email for user on password reset.
     *
     * @param user
     * @return
     */
    public boolean notifyUserOnPasswordReset(UserDto user) {
        // Send SMS notification
        String[] smsNotificationArgs = new String[]{user.getPassword()};
        String locale = isCitizen(user.getNin()) ? "ar" : "en";
        String createdUserSms = messageSource.getMessage(RESET_PASSWORD_SMS_NOTIFICATION_KEY, smsNotificationArgs, Locale.forLanguageTag(locale));
        boolean smsSent = false;
        try {
            smsSent = huicSmsService.sendMessage(966, String.valueOf(user.getMobileNumber()), createdUserSms, "comments");
        } catch (SSLException e) {
            log.error("Unable to send SMS for {}", user.getMobileNumber(), e);
        }
        log.debug("SMS notification status: {}", smsSent);

        // Send Email notification
        boolean emailSent = false;
        try {
            emailSent = emailService.sendMailFromTemplate(Arrays.asList(user.getEmail()), null,
                    RESET_PASSWORD_EMAIL_SUBJECT, RESET_PASSWORD_EMAIL_TPL_NAME, ImmutableMap.of("user", user));
        } catch (Exception e) {
            log.error("Unable to send email for {}", user.getEmail(), e);
        }
        log.debug("Email notification status: {}", emailSent);

        return smsSent || emailSent;
    }

    /**
     * Send SMS and Email for registered user.
     *
     * @param user
     * @return
     */
    public boolean notifyRegisteredUser(UserDto user) {
        // Send SMS notification
        String[] smsNotificationArgs = new String[]{user.getPassword()};
        String locale = isCitizen(user.getNin()) ? "ar" : "en";
        String createdUserSms = messageSource.getMessage(CREATE_USER_SMS_NOTIFICATION_KEY, smsNotificationArgs, Locale.forLanguageTag(locale));
        boolean smsSent = false;
        try {
            smsSent = huicSmsService.sendMessage(966, String.valueOf(user.getMobileNumber()), createdUserSms, "comments");
        } catch (SSLException e) {
            log.error("Unable to send SMS for {}", user.getMobileNumber(), e);
        }
        log.debug("SMS notification status: {}", smsSent);

        String appUrl = request.getScheme() + "://" + request.getHeader("host");
        String logoURL = appUrl + "/assets/images/login_logo.png";
        String headerURL = appUrl + "/assets/images/header.png";
        String dotsPatternURL = appUrl + "/assets/images/dots-pattern.png";
        // Send Email notification
        boolean emailSent = emailService.sendMailFromTemplate(Arrays.asList(user.getEmail()), null,
                REGISTRATION_EMAIL_SUBJECT, REGISTRATION_EMAIL_TPL_NAME, ImmutableMap.of("user", user, "logoURL", logoURL, "headerURL", headerURL, "dotsPatternURL", dotsPatternURL));
        log.debug("Email notification status: {}", emailSent);

        return smsSent || emailSent;
    }
}
