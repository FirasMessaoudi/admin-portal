/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.company.CompanyLiteService;
import com.elm.shj.admin.portal.services.dto.CompanyLiteDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Main controller for company related pages
 *
 * @author ahmad flaifel
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
@RequestMapping(Navigation.API_COMPANY)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyWsController {

    private final CompanyLiteService companyLiteService;
    @GetMapping("/establishment/find")
    public ResponseEntity<WsResponse<?>> findEstablishmentCompanies() {
        log.info("Start findEstablishmentCompanies");
        List<CompanyLiteDto> establishmentCompanies = companyLiteService.findEstablishmentCompanies();
        log.info("Finish findEstablishmentCompanies  establishmentCompaniesListSize: {}", "SUCCESS", establishmentCompanies.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(establishmentCompanies).build());
    }

    @GetMapping("/service-group/find/{establishmentCompanyCode}")
    public ResponseEntity<WsResponse<?>> findServiceGroupCompanies(@PathVariable String establishmentCompanyCode) {
        log.info("Start findServiceGroupCompanies");
        List<CompanyLiteDto> serviceGroupCompanies = companyLiteService.findServiceGroupCompanies(establishmentCompanyCode);
        log.info("Finish findEstablishmentCompanies  findServiceGroupCompanies: {}", "SUCCESS", serviceGroupCompanies.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(serviceGroupCompanies).build());
    }

    @GetMapping("/internal-hajj/find")
    public ResponseEntity<WsResponse<?>> findInternalHajjCompanies() {
        log.info("Start findInternalHajjCompanies");
        List<CompanyLiteDto> internalHajjCompanies = companyLiteService.findInternalHajjCompanies();
        log.info("Finish findEstablishmentCompanies  findInternalHajjCompanies: {}", "SUCCESS", internalHajjCompanies.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(internalHajjCompanies).build());
    }



}
