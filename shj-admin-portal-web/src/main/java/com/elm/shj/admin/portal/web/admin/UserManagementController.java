/*
 * Copyright (c) 2018 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.dcc.foundation.commons.validation.SafeFile;
import com.elm.dcc.foundation.providers.recaptcha.exception.RecaptchaException;
import com.elm.dcc.foundation.providers.recaptcha.model.RecaptchaInfo;
import com.elm.dcc.foundation.providers.recaptcha.service.RecaptchaService;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.UserDto;
import com.elm.shj.admin.portal.services.dto.UserPasswordHistoryDto;
import com.elm.shj.admin.portal.services.user.PasswordHistoryService;
import com.elm.shj.admin.portal.services.user.UserService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtToken;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.groups.Default;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

/**
 * Main controller for admin user management page
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping(Navigation.API_USERS)
@Validated
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserManagementController {

    public static final String CONFIDENTIAL = "<CONFIDENTIAL>";
    public static final String NEW_PWRD_FIELD_NAME = "newPassword";
    public static final String OLD_PWRD_FIELD_NAME = "oldPassword";
    private static final String PWRD_HISTORY_ERROR_MESSAGE_KEY = "{dcc.commons.validation.constraints.password-history}";
    private static final String PWRD_CONTAINS_USERNAME_ERROR_MESSAGE_KEY = "{dcc.commons.validation.constraints.password-contains-username}";
    private static final String OLD_PWRD_ERROR_MESSAGE_KEY = "{dcc.commons.validation.constraints.invalid}";
    private static final String CHANGE_PWRD_METHOD_NAME = "changeUserPassword";
    public static final String RECAPTCHA_TOKEN_NAME = "grt";

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PasswordHistoryService passwordHistoryService;
    private final JwtTokenService jwtTokenService;
    private final RecaptchaService recaptchaService;


    /**
     * finds users by role id, nin or account status
     *
     * @param roleId    the user role id to find (<=0: all, > 0: valid id)
     * @param nin       the user nin to find (<=0: all, > 0: valid nin)
     * @param activated the user account status (1: true, 0: false, <0: all)
     * @return the found users or <code>null</code>
     */
    @GetMapping("/list/{roleId}/{nin}/{activated}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.USER_MANAGEMENT + "')")
    public Page<UserDto> search(Pageable pageable, @PathVariable long roleId, @PathVariable String nin, @PathVariable int activated, Authentication authentication) {
        log.debug("Handler for {}", "Search Users");
        if (roleId <= 0 && Long.parseLong(nin) == -1 && activated < 0) {
            return userService.findAllNotDeleted(pageable, jwtTokenService.retrieveUserIdFromToken(((JwtToken) authentication).getToken()).orElse(0L)
                    );
        }
        Page<UserDto> usersPage = userService.searchByRoleStatusOrNin(pageable,
                (roleId <= 0 ? null : roleId),
                (Long.parseLong(nin) == -1 ? null : nin.trim()),
                (activated < 0 ? null : BooleanUtils.toBoolean(activated)),
                jwtTokenService.retrieveUserIdFromToken(((JwtToken) authentication).getToken()).orElse(0L),
                jwtTokenService.retrieveUserRoleIdsFromToken(((JwtToken) authentication).getToken()).orElse(new HashSet<>()));
        return usersPage.map(UserManagementController::maskUserInfo);
    }

    /**
     * finds a user by his ID
     *
     * @param userId the user id to find
     * @return the found user or <code>null</code>
     */
    @GetMapping("/find/{userId}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.EDIT_USER + "', '" + AuthorityConstants.VIEW_MY_PROFILE + "')")
    public UserDto findUser(@PathVariable long userId) {
        log.debug("Handler for {}", "Find User");
        return maskUserInfo(userService.findOne(userId));
    }

    /**
     * finds a user by his nin
     *
     * @param nin the nin of the user to find
     * @return the found user or <code>null</code>>
     */
    @GetMapping("/find/nin/{nin}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.EDIT_USER + "')")
    public UserDto findByNin(@PathVariable Long nin) {
        log.debug("Find by nin {}", nin);
        return maskUserInfo(userService.findByNin(nin).orElse(null));
    }

    /**
     * Reset user password by admin as part of user management
     *
     * @param idNumber
     */
    @GetMapping("/reset-user-password/{idNumber}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.RESET_USER_PASSWORD + "')")
    public void resetUserPassword(@PathVariable Long idNumber) {
        UserDto user = userService.findByNin(idNumber).orElseThrow(() -> new UsernameNotFoundException("No user found with username " + idNumber));
        userService.resetPassword(user);
    }

    /**
     * Resets the user password
     *
     * @return the found user or <code>null</code>
     */
    @PostMapping("/reset-password")
    public void resetUserPassword(@RequestBody @Valid ResetPasswordCmd command,
                                  @RequestParam(RECAPTCHA_TOKEN_NAME) String reCaptchaToken) {
        if (StringUtils.isBlank(reCaptchaToken)) {
            log.info("recaptcha response is not provided in the request...");
            throw new RecaptchaException("Invalid Captcha");
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        RecaptchaInfo recaptchaInfo;
        try {
            recaptchaInfo = recaptchaService.verifyRecaptcha(request.getRemoteAddr(), reCaptchaToken);
        } catch (IllegalArgumentException e) {
            throw new RecaptchaException("Invalid character in captcha response.");
        }
        if (recaptchaInfo == null || !recaptchaInfo.isSuccess()) {
            throw new RecaptchaException("Invalid captcha.");
        }

        UserDto user = userService.findByNin(command.getIdNumber()).orElseThrow(() -> new UsernameNotFoundException("No user found with username " + command.getIdNumber()));

        boolean dateOfBirthMatched;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // decide which date of birth to use
        if (user.getDateOfBirthGregorian() != null) {
            String userDateFormatted = sdf.format(user.getDateOfBirthGregorian());
            String commandDataOfBirthFormatted = sdf.format(command.getDateOfBirthGregorian());
            dateOfBirthMatched = commandDataOfBirthFormatted.equals(userDateFormatted);
        } else {
            dateOfBirthMatched = command.getDateOfBirthHijri() == user.getDateOfBirthHijri();
        }
        if (dateOfBirthMatched) {
            userService.resetPassword(user);
        } else {
            log.debug("invalid data for username {}", command.getIdNumber());
            throw new UsernameNotFoundException("invalid data");
        }
    }

    /**
     * Change the user password
     *
     * @return the found user or <code>null</code>
     */
    @PostMapping("/change-password")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.RESET_PASSWORD + "')")
    public void changeUserPassword(@RequestBody @Valid ChangePasswordCmd command) throws MethodArgumentNotValidException, NoSuchMethodException {
        log.debug("Handler for {}", "Change User Password");

        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof JwtToken)) {
            return;
        }

        JwtToken loggedInUser = (JwtToken) SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserIdNumberStr = ((User) loggedInUser.getPrincipal()).getUsername();
        long loggedInUserIdNumber = Long.parseLong(loggedInUserIdNumberStr);
        String oldPasswordHash = userService.retrievePasswordHash(loggedInUserIdNumber);
        if (!BCrypt.checkpw(command.getOldPassword(), oldPasswordHash)) {
            rejectInvalidOldPassword(command, OLD_PWRD_ERROR_MESSAGE_KEY);
        }
        // current password cannot be used as new password
        if (command.getNewPassword().equals(command.getOldPassword())) {
            //raise an error
            rejectNewPassword(command, PWRD_HISTORY_ERROR_MESSAGE_KEY);
        }
        // password should not contain the user's username which is id number
        if (command.getNewPassword().contains(loggedInUserIdNumberStr)) {
            rejectNewPassword(command, PWRD_CONTAINS_USERNAME_ERROR_MESSAGE_KEY);
        }

        //retrieve old passwords and compare the new password with the returned list
        Optional<UserPasswordHistoryDto> matchedOldPassword = Optional.empty();
        Optional<Long> userIdFromToken = jwtTokenService.retrieveUserIdFromToken(loggedInUser.getToken());
        if (userIdFromToken.isPresent()) {
            matchedOldPassword = passwordHistoryService.findByUserId(userIdFromToken.get()).stream().filter(passHistory -> BCrypt.checkpw(command.getNewPassword(), passHistory.getOldPasswordHash())).findFirst();
        }

        if (matchedOldPassword.isPresent()) {
            //raise an error
            BindingResult beanPropertyBindingResult =
                    new BeanPropertyBindingResult(command, command.getClass().getName());
            beanPropertyBindingResult.rejectValue(NEW_PWRD_FIELD_NAME, StringUtils.EMPTY, null, PWRD_HISTORY_ERROR_MESSAGE_KEY);
            MethodParameter methodParameter = new MethodParameter(this.getClass().getMethod(CHANGE_PWRD_METHOD_NAME,
                    command.getClass()), 0);
            throw new MethodArgumentNotValidException(methodParameter, beanPropertyBindingResult);
        }
        //no matching, update the password
        userService.updateUserPassword(loggedInUserIdNumber, passwordEncoder.encode(command.getNewPassword()));
        userService.clearToken(loggedInUserIdNumber);
        //keep password history
        userIdFromToken.ifPresent(aLong -> passwordHistoryService.addUserPasswordHistory(aLong, oldPasswordHash));
    }

    /**
     * Reject provided password value and throw an exception
     *
     * @param command         {@link ChangePasswordCmd}
     * @param errorMessageKey Error message key defined in localization message file
     * @throws MethodArgumentNotValidException
     * @throws NoSuchMethodException
     */
    private void rejectNewPassword(ChangePasswordCmd command, String errorMessageKey) throws MethodArgumentNotValidException,
            NoSuchMethodException {
        BindingResult beanPropertyBindingResult =
                new BeanPropertyBindingResult(command, command.getClass().getName());
        beanPropertyBindingResult.rejectValue(NEW_PWRD_FIELD_NAME, StringUtils.EMPTY, null, errorMessageKey);
        MethodParameter methodParameter = new MethodParameter(this.getClass().getMethod(CHANGE_PWRD_METHOD_NAME,
                command.getClass()), 0);
        throw new MethodArgumentNotValidException(methodParameter, beanPropertyBindingResult);
    }

    /**
     * Reject invalid old password value and throw an exception
     *
     * @param command         {@link ChangePasswordCmd}
     * @param errorMessageKey Error message key defined in localization message file
     * @throws MethodArgumentNotValidException
     * @throws NoSuchMethodException
     */
    private void rejectInvalidOldPassword(ChangePasswordCmd command, String errorMessageKey) throws MethodArgumentNotValidException,
            NoSuchMethodException {
        BindingResult beanPropertyBindingResult =
                new BeanPropertyBindingResult(command, command.getClass().getName());
        beanPropertyBindingResult.rejectValue(OLD_PWRD_FIELD_NAME, StringUtils.EMPTY, null, errorMessageKey);
        MethodParameter methodParameter = new MethodParameter(this.getClass().getMethod(CHANGE_PWRD_METHOD_NAME,
                command.getClass()), 0);
        throw new MethodArgumentNotValidException(methodParameter, beanPropertyBindingResult);
    }

    /**
     * Upload user avatar
     *
     * @param userAvatarFile the user avatar file
     * @param userId         the user id
     * @return the encoded user avatar
     */
    @PostMapping("/avatar/{userId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.EDIT_USER + "')")
    public ResponseEntity<String> updateUserAvatar(@RequestParam("avatar") @SafeFile MultipartFile userAvatarFile, @PathVariable Long userId) throws IOException {
        log.debug("Handler for {}", "Update User Avatar");
        String encodedAvatarStr = userService.updateUserAvatar(userId, userAvatarFile.getBytes());
        return ResponseEntity.ok(encodedAvatarStr);
    }

    /**
     * Updates an existing user
     *
     * @param user the user to update
     * @return the updated user
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.EDIT_USER + "')")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Validated({UserDto.UpdateUserValidationGroup.class, Default.class}) UserDto user) {
        log.debug("Handler for {}", "Update User");

        UserDto databaseUser = userService.findOne(user.getId());

        // sets form fields to database user instance
        databaseUser.setDateOfBirthGregorian(user.getDateOfBirthGregorian());
        databaseUser.setDateOfBirthHijri(user.getDateOfBirthHijri());
        databaseUser.setActivated(user.isActivated());
        databaseUser.setEmail(user.getEmail());
        databaseUser.setFamilyName(user.getFamilyName());
        databaseUser.setFatherName(user.getFatherName());
        databaseUser.setFirstName(user.getFirstName());
        databaseUser.setGender(user.getGender());
        databaseUser.setGrandFatherName(user.getGrandFatherName());
        databaseUser.setMobileNumber(user.getMobileNumber());
        databaseUser.setNin(user.getNin());
        databaseUser.setUserRoles(user.getUserRoles());
        UserDto savedUser = maskUserInfo(userService.save(databaseUser));

        return ResponseEntity.ok(Objects.requireNonNull(savedUser));
    }

    /**
     * Add new user.
     *
     * @param user the user to create
     * @return the created user
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.ADD_USER + "')")
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated({UserDto.CreateUserValidationGroup.class, Default.class}) UserDto user) {
        log.debug("Handler for {}", "Create User");
        UserDto savedUser = null;
        try {
            savedUser = userService.createUser(user, false);
        } catch (Exception e) {
            log.error("Error while creating user.", e);
            return ResponseEntity.of(Optional.empty());
        }
        return ResponseEntity.ok(Objects.requireNonNull(savedUser));
    }

    /**
     * Deletes the user by his ID
     *
     * @param userId the user id for to load details
     * @return the {@link ResponseEntity} with status
     */
    @PostMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.DELETE_USER + "')")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        log.debug("Handler for {}", "delete user");
        userService.deleteUser(userId);
        return ResponseEntity.ok(StringUtils.EMPTY);
    }

    /**
     * Activate inactive user
     *
     * @param userId
     * @return
     */
    @PostMapping("/activate/{userId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.CHANGE_USER_STATUS + "')")
    public ResponseEntity<String> activateUser(@PathVariable long userId) {
        log.debug("Handler for {}", "activate user");
        userService.activateUser(userId);
        return ResponseEntity.ok(StringUtils.EMPTY);
    }

    /**
     * Deactivate active user
     *
     * @param userId
     * @return
     */
    @PostMapping("/deactivate/{userId}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.CHANGE_USER_STATUS + "')")
    public ResponseEntity<String> deactivateUser(@PathVariable long userId) {
        log.debug("Handler for {}", "deactivate user");
        userService.deactivateUser(userId);
        return ResponseEntity.ok(StringUtils.EMPTY);
    }

    /**
     * Masking sensitive data from user information
     *
     * @param user the user to filter
     * @return the filtered user
     */
    private static UserDto maskUserInfo(UserDto user) {
        if (user != null) {
            user.setPasswordHash(CONFIDENTIAL);
            user.setPassword(CONFIDENTIAL);
        }
        return user;
    }
}
