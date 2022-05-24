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

import java.util.ArrayList;
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
        log.info("Start saveApplicantMainData ApplicantDtosSize {}", applicantDtos == null? null:applicantDtos.size());
        List<ErrorResponse> errorResponses = validationService.validateData(applicantDtos);

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveApplicantMainData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveApplicantMainData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-ritual-data")
    public ResponseEntity<WsResponse<?>> saveApplicantRitualData(@RequestBody List<ApplicantRitualDto> applicantRitualDtos) {
        log.info("Start saveApplicantRitualData applicantRitualDtosSize: {}", applicantRitualDtos == null? null:applicantRitualDtos.size());
        List<ErrorResponse> errorResponses = validationService.validateData(applicantRitualDtos);

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveApplicantRitualData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveApplicantRitualData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-health-data")
    public ResponseEntity<WsResponse<?>> saveApplicantHealthData(@RequestBody List<ApplicantHealthDto> applicantHealthDtos) {
        log.info("Start saveApplicantHealthData applicantHealthDtosSize: {}", applicantHealthDtos == null? null:applicantHealthDtos.size());
        List<ErrorResponse> errorResponses = validationService.validateData(applicantHealthDtos);

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveApplicantHealthData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveApplicantHealthData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-relative-data")
    public ResponseEntity<WsResponse<?>> saveApplicantRelativeData(@RequestBody List<ApplicantRelativeDto> applicantRelativeDtos) {
        log.info("Start saveApplicantRelativeData applicantRelativeDtosSize: {}",  applicantRelativeDtos == null? null:applicantRelativeDtos.size());
        List<ErrorResponse> errorResponses = validationService.validateData(applicantRelativeDtos);

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveApplicantRelativeData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveApplicantRelativeData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-health-disease")
    public ResponseEntity<WsResponse<?>> saveApplicantHealthDisease(@RequestBody List<ApplicantHealthDiseaseDto> applicantHealthDiseaseDtos) {
        log.info("Start saveApplicantHealthDisease applicantHealthDiseaseDtosSize: {}", applicantHealthDiseaseDtos == null? null:applicantHealthDiseaseDtos.size());
        List<ErrorResponse> errorResponses = validationService.validateData(applicantHealthDiseaseDtos);

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveApplicantHealthDisease {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveApplicantHealthDisease {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-applicant-health-immunization")
    public ResponseEntity<WsResponse<?>> saveApplicantHealthImmunization(@RequestBody List<ApplicantHealthImmunizationDto> applicantHealthImmunizationDtos) {
        log.info("Start saveApplicantHealthImmunization applicantHealthImmunizationDtosSize: {}", applicantHealthImmunizationDtos == null? null:applicantHealthImmunizationDtos.size());
        List<ErrorResponse> errorResponses = validationService.validateData(applicantHealthImmunizationDtos);

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveApplicantHealthImmunization {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveApplicantHealthImmunization {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-company-staff-main-data")
    public ResponseEntity<WsResponse<?>> saveCompanyStaffMainData(@RequestBody HuicCompanyStaffDto companyStaffDtos) {
        log.info("Start saveCompanyStaffMainData HuicCompanyStaffDto Season: {}", companyStaffDtos == null? null:companyStaffDtos.getSeason());
        companyStaffDtos.getCompanyStaffs().forEach(companyStaffDto -> {
            companyStaffDto.setSeason(companyStaffDtos.getSeason());
        });
        List<ErrorResponse> errorResponses = validationService.validateData(companyStaffDtos.getCompanyStaffs());

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveCompanyStaffMainData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveCompanyStaffMainData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-company-staff-ritual-data")
    public ResponseEntity<WsResponse<?>> saveCompanyStaffRitualData(@RequestBody HuicCompanyStaffRitualDto companyStaffRituals) {
        log.info("Start saveCompanyStaffRitualData RitualTypeCode : {}", companyStaffRituals == null? null:companyStaffRituals.getRitualTypeCode());
        companyStaffRituals.getCompanyStaffRituals().forEach(companyStaffDto -> {
            companyStaffDto.setSeason(companyStaffRituals.getSeason());
            companyStaffDto.setTypeCode(companyStaffRituals.getRitualTypeCode());

        });
        List<ErrorResponse> errorResponses = validationService.validateData(companyStaffRituals.getCompanyStaffRituals());

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveCompanyStaffRitualData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveCompanyStaffRitualData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-ritual-seasons")
    public ResponseEntity<WsResponse<?>> saveRitualSeasons(@RequestBody List<RitualSeasonDto> ritualSeasons) {
        log.info("Start saveRitualSeasons RitualSeasonDtosSize: {}", ritualSeasons == null? null:ritualSeasons.size());
        List<ErrorResponse> errorResponses = validationService.validateData(ritualSeasons);

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveRitualSeasons {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveRitualSeasons {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-housing-data")
    public ResponseEntity<WsResponse<?>> saveHousingData(@RequestBody List<PackageHousingDto> packageHousings) {
        log.info("Start saveHousingData PackageHousingDtosSize: {}", packageHousings == null? null:packageHousings.size());
        List<ErrorResponse> errorResponses = validationService.validateData(packageHousings);

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveRitualSeasons {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }

        log.info("Finish saveRitualSeasons {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-companies")
    public ResponseEntity<WsResponse<?>> saveCompanies(@RequestBody List<CompanyDto> companies) {
        log.info("Start saveCompanies CompanyDtosSize: {}", companies == null? null:companies.size());
        List<ErrorResponse> errorResponses = validationService.validateData(companies);

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveCompanies {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveCompanies {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-planned-packages")
    public ResponseEntity<WsResponse<?>> savePlannedPackages() {
        log.info("Start savePlannedPackages");
        List<ErrorResponse> errorResponses = validationService.validateData(new ArrayList<>());

        if (!errorResponses.isEmpty()) {
            log.info("Finish savePlannedPackages {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish savePlannedPackages {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-tafweej-data")
    public ResponseEntity<WsResponse<?>> saveTafweejData() {
        log.info("Start saveTafweejData");
        List<ErrorResponse> errorResponses = validationService.validateData(new ArrayList<>());

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveTafweejData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveTafweejData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-pre-arrival-data")
    public ResponseEntity<WsResponse<?>> savePreArrivalData() {
        log.info("Start savePreArrivalData");
        List<ErrorResponse> errorResponses = validationService.validateData(new ArrayList<>());

        if (!errorResponses.isEmpty()) {
            log.info("Finish savePreArrivalData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish savePreArrivalData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-group-list-data")
    public ResponseEntity<WsResponse<?>> saveGroupListData() {
        log.info("Start saveGroupListData");
        List<ErrorResponse> errorResponses = validationService.validateData(new ArrayList<>());

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveGroupListData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveGroupListData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

    @PostMapping(value = "/save-group-main-data")
    public ResponseEntity<WsResponse<?>> saveGroupMainData() {
        log.info("Start saveGroupMainData");
        List<ErrorResponse> errorResponses = validationService.validateData(new ArrayList<>());

        if (!errorResponses.isEmpty()) {
            log.info("Finish saveGroupMainData {}, errorResponses: {}","FAILURE" ,errorResponses);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(
                    errorResponses).build());
        }
        log.info("Finish saveGroupMainData {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                Collections.emptyList()).build());
    }

}
