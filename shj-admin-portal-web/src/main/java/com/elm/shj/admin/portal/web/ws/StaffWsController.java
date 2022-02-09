/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.orm.entity.CompanyStaffVO;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for exposing company staff web services for external party.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)
@Slf4j
@RestController
@RequestMapping(Navigation.API_STAFF_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StaffWsController {

    private final CompanyStaffService companyStaffService;

    /**
     * finds company staff
     *
     * @param suin the UIN of the staff
     * @return the found company staff with basic info and latest ritual
     */
    @GetMapping("/find/{suin}")
    public ResponseEntity<WsResponse<?>> findCompanyStaffBySuin(@PathVariable String suin) {
        log.debug("find company staff by suin {} ", suin);
        Optional<CompanyStaffVO> staffRitualBySuin = companyStaffService.findStaffRitualBySuin(suin);
        if (staffRitualBySuin.isPresent())
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(staffRitualBySuin).build());

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                .body(WsError.builder().error(WsError.EWsError.COMPANY_STAFF_NOT_FOUND.getCode()).referenceNumber(suin).build()).build());

    }

}
