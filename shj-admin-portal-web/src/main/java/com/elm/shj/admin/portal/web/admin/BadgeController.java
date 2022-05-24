/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.BadgeService;
import com.elm.shj.admin.portal.services.dto.BadgeVO;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Main controller for badge related services
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */

@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)@RestController
@RequestMapping(Navigation.API_BADGE)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Validated
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping("/applicant/{digitalId}")
    public BadgeVO generateApplicantBadge(@PathVariable String digitalId) {
        //TODO: do some validation for the passed digital id and return error message if needed
        if (digitalId.length() == 14) {
            //valid uin
            return badgeService.generateApplicantBadge(digitalId, false);
        }
        return null;
    }

    @GetMapping("/staff/{digitalId}")
    public BadgeVO generateStaffBadge(@PathVariable String digitalId) {
        if (digitalId.length() == 12) {
            // valid suin
        }
        return null;
    }

}
