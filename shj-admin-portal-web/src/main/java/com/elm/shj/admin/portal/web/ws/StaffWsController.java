/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO;
import com.elm.shj.admin.portal.orm.entity.CompanyStaffVO;
import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.GroupApplicantListService;
import com.elm.shj.admin.portal.services.applicant.PackageHousingService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.EUserType;
import com.elm.shj.admin.portal.services.dto.PackageHousingDto;
import com.elm.shj.admin.portal.services.dto.PrintRequestDto;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestLiteService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    private final ApplicantLiteService applicantLiteService;
    private final GroupApplicantListService groupApplicantListService;
    private final PackageHousingService packageHousingService;
    private final PrintRequestLiteService printRequestLiteService;

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

    /**
     * finds company staff Or Applicant
     *
     * @param suin the UIN of the staff
     * @param value ShaaerNumber( uin/suin) or IdNumber(nin,Iqama number,passport Number)
     * @param  isShaaerNumber if the user want to search by uin/suin
     * @return the found company staff or applicant
     */
    @GetMapping("/search/{suin}/{value}/{isShaaerNumber}")
    public ResponseEntity<WsResponse<?>> findApplicantOrStaff(@PathVariable String suin, @PathVariable String value, @PathVariable boolean isShaaerNumber) {
        log.debug("find staff or applicant {} - isShaaerNumber {}", value, isShaaerNumber);
        if (isShaaerNumber && value.length() == 12) {
            Optional<ApplicantStaffVO> staff = companyStaffService.findStaffBySuin(value);
            if (staff.isPresent()) {
                ApplicantStaffVO applicantStaffVO = staff.get();
                applicantStaffVO.setUserType(EUserType.STAFF.getId());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                        .body(applicantStaffVO).build());
            }
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(value).build()).build());
        }

        if (isShaaerNumber && value.length() == 14) {
            Optional<ApplicantStaffVO> applicant = applicantLiteService.findApplicantRitualByUin(value);
            if (applicant.isPresent()) {
                ApplicantStaffVO applicantStaffVO = applicant.get();
                applicantStaffVO.setUserType(EUserType.APPLICANT.getId());
                if(applicantStaffVO.getGroupLeaderSuin()!= null && applicantStaffVO.getGroupLeaderSuin().equals(suin)){
                    applicantStaffVO.setSameStaffGroup(true);
                }
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                        .body(applicantStaffVO).build());
            }

            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(value).build()).build());
        }

        if (isShaaerNumber) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(value).build()).build());

        }

        Optional<ApplicantStaffVO> staff = companyStaffService.findStaffByIdNumber(value);
        if (staff.isPresent()) {
            ApplicantStaffVO applicantStaffVO = staff.get();
            applicantStaffVO.setUserType(EUserType.STAFF.getId());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(applicantStaffVO).build());
        }

        Optional<ApplicantStaffVO> applicant = applicantLiteService.findApplicantRitualByIdNumber(value);
        if (applicant.isPresent()) {
            ApplicantStaffVO applicantStaffVO = applicant.get();
            applicantStaffVO.setUserType(EUserType.APPLICANT.getId());
            if(applicantStaffVO.getGroupLeaderSuin().equals(suin)){
                applicantStaffVO.setSameStaffGroup(true);
            }
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(applicantStaffVO).build());
        }

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(value).build()).build());

    }

    @GetMapping("/applicant-group/{suin}")
    public ResponseEntity<WsResponse<?>> loadApplicantGroupList(@PathVariable String suin) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(groupApplicantListService.findGroupApplicantListBySuin(suin)).build());
    }

    @GetMapping("/find/package-housing/{companyRitualSeason}")
    public ResponseEntity<WsResponse<?>> findStaffPackageHousingByCompanyRitualSeason(@PathVariable long companyRitualSeason) {
        PackageHousingDto packageHousingDto=  packageHousingService.findStaffPackageHousingByCompanyRitualSeason(companyRitualSeason);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(packageHousingDto).build());
    }

    @GetMapping("/find/print-request")
    public ResponseEntity<WsResponse<?>> findAllPrintRequest() {
        log.debug("List print requests based on search criteria...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(printRequestLiteService.findAllPrintRequest()).build());
    }

    @PutMapping("/update-print-request-status/{printRequestId}")
    public ResponseEntity<WsResponse<?>> updatePrintRequestStatus(@PathVariable long printRequestId) {
        printRequestLiteService.updatePrintRequestStatus(printRequestId);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
    }

    @GetMapping("/find/print-request-batches/{refrenceNumber}/{target}")
    public ResponseEntity<WsResponse<?>> findPrintRequest(@PathVariable String refrenceNumber, @PathVariable String target) {
        log.debug("List print requests based on search criteria...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(printRequestLiteService.findPrintRequestBatches(refrenceNumber, target)).build());
    }

}
