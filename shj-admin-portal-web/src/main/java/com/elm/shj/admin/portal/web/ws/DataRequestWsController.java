/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.data.huicIntegration.ErrorResponse;
import com.elm.shj.admin.portal.services.data.huicIntegration.ValidationService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Controller for exposing data request web services for external party.
 *
 * @author f.messaoudi
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
@RequestMapping(Navigation.API_HUIC_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataRequestWsController {

    private final ValidationService validationService;

    @PostMapping(value = "/save-applicant-main-data")
    public ResponseEntity<WsResponse<?>> saveApplicantMainData(@RequestBody List<ApplicantDto> applicantDtos) {

        List<ErrorResponse> errorResponses = validationService.validateData(applicantDtos);

        if (!errorResponses.isEmpty())
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-ritual-data")
    public ResponseEntity<WsResponse<?>> saveApplicantRitualData(@RequestBody List<ApplicantRitualDto> applicantRitualDtos) {

        List<ErrorResponse> errorResponses = validationService.validateData(applicantRitualDtos);

        if (!errorResponses.isEmpty())
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-health-data")
    public ResponseEntity<WsResponse<?>> saveApplicantHealthData(@RequestBody List<ApplicantHealthDto> applicantHealthDtos) {

        List<ErrorResponse> errorResponses = validationService.validateData(applicantHealthDtos);

        if (!errorResponses.isEmpty())
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-relative-data")
    public ResponseEntity<WsResponse<?>> saveApplicantRelativeData(@RequestBody List<ApplicantRelativeDto> applicantRelativeDtos) {

        List<ErrorResponse> errorResponses = validationService.validateData(applicantRelativeDtos);

        if (!errorResponses.isEmpty())
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-health-disease")
    public ResponseEntity<WsResponse<?>> saveApplicantHealthDisease(@RequestBody List<ApplicantHealthDiseaseDto> applicantHealthDiseaseDtos) {

        List<ErrorResponse> errorResponses = validationService.validateData(applicantHealthDiseaseDtos);

        if (!errorResponses.isEmpty())
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-health-immunization")
    public ResponseEntity<WsResponse<?>> saveApplicantHealthImmunization(@RequestBody List<ApplicantHealthImmunizationDto> applicantHealthImmunizationDtos) {

        List<ErrorResponse> errorResponses = validationService.validateData(applicantHealthImmunizationDtos);

        if (!errorResponses.isEmpty())
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-company-staff-main-data")
    public ResponseEntity<WsResponse<?>> saveCompanyStaffMainData(@RequestBody HuicCompanyStaffDto companyStaffDtos) {
        companyStaffDtos.getCompanyStaffs().forEach(companyStaffDto -> {
            companyStaffDto.setSeason(companyStaffDtos.getSeason());
        });
        List<ErrorResponse> errorResponses = validationService.validateData(companyStaffDtos.getCompanyStaffs());

        if (!errorResponses.isEmpty())
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-company-staff-ritual-data")
    public ResponseEntity<WsResponse<?>> saveCompanyStaffRitualData(@RequestBody HuicCompanyStaffRitualDto companyStaffRituals) {
        companyStaffRituals.getCompanyStaffRituals().forEach(companyStaffDto -> {
            companyStaffDto.setSeason(companyStaffRituals.getSeason());
            companyStaffDto.setTypeCode(companyStaffRituals.getRitualTypeCode());

        });
        List<ErrorResponse> errorResponses = validationService.validateData(companyStaffRituals.getCompanyStaffRituals());

        if (!errorResponses.isEmpty())
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }


}
