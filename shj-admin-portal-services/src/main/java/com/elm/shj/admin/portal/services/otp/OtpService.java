/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.otp;

import com.elm.shj.admin.portal.services.dto.OtpCacheDto;
import com.elm.shj.admin.portal.services.sms.HUICSmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

/**
 * Service handling otp operations
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OtpService {

    private static final String OTP_SMS_NOTIFICATION_MSG = "otp.login.sms.notification";

    @Value("${otp.expiry.minutes}")
    private int otpExpiryMinutes;

    @Value("${otp.mock.enabled}")
    private boolean mockEnabled;

    private final OtpCacheService otpCacheService;

    private final OtpGenerator otpGenerator;
    private final HUICSmsService huicSmsService;
    private final MessageSource messageSource;

    /**
     * Creates and starts a new otp-transaction
     *
     * @param principal the key to attach to the created otp
     * @return the created otp
     */
    public String createOtp(String principal,Integer countryCode, String mobileNumber) {
        log.info("start createOtp with username: {} and countryCode: {}, and mobileNumber: {}", principal, countryCode, mobileNumber);
        if (mockEnabled) {
            log.info("No creation, otp mocked.");
            return "";
        }
        try {
            String generatedOtp = otpGenerator.generateOtp(principal);
            log.info("Otp generated for username:{} and otp: {}", principal, generatedOtp);
            // remove old otp records if exist for the same principle
            otpCacheService.deletePrincipleOtps(principal);
            OtpCacheDto createdOtpCacheDto = otpCacheService.save(OtpCacheDto.builder().principle(principal).otp(generatedOtp).creationDate(new Date()).build());
            log.info("Created otp {} for {} principle ", createdOtpCacheDto.getOtp(), createdOtpCacheDto.getPrinciple());
            String locale = principal.startsWith("1") ? "ar" : "en";
            String registerUserSms = messageSource.getMessage(OTP_SMS_NOTIFICATION_MSG, new String[]{generatedOtp}, Locale.forLanguageTag(locale));
            log.info("end createOtp with username: {} and countryCode: {}, and mobileNumber: {}", principal, countryCode, mobileNumber);
            return huicSmsService.sendMessage(countryCode,mobileNumber, registerUserSms, "comments") ? generatedOtp : null;
        } catch (NoSuchAlgorithmException | InvalidKeyException | RuntimeException | SSLException e) {
            log.error("Unable to generate OTP : " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Validates the given otp for the request
     *
     * @param principal   the key to check against
     * @param otpToVerify the otp to verify
     * @return result of verification
     */
    public boolean validateOtp(String principal, String otpToVerify) {
        log.info("Start Verify otp for username:{} and otpToVerify: {}", principal, otpToVerify);
        if (mockEnabled) {
            log.info("Otp mocked.");
            return true;
        }
        Optional<OtpCacheDto> otpCache = otpCacheService.findByPrincipleAndOtp(principal, otpToVerify);
        if (otpCache.isPresent()) {
            otpCacheService.deletePrincipleOtps(principal);
            log.info("End with otp Verified for username:{} and otpToVerify: {}", principal, otpToVerify);
            return true;
        }
        log.info("Otp not matched with OtpCache for username:{} and otpToVerify: {}", principal, otpToVerify);
        return false;
    }

    /**
     * Returns the Otp expiry duration in minutes as per the configuration
     *
     * @return the otp expiry duration in minutes
     */
    public int getOtpExpiryMinutes() {
        return otpExpiryMinutes;
    }

}
