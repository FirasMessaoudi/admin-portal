/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO;
import com.elm.shj.admin.portal.orm.entity.ApplicantVo;
import com.elm.shj.admin.portal.orm.entity.CompanyStaffVO;
import com.elm.shj.admin.portal.services.applicant.ApplicantLiteService;
import com.elm.shj.admin.portal.services.applicant.GroupApplicantListService;
import com.elm.shj.admin.portal.services.applicant.PackageHousingService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.EUserType;
import com.elm.shj.admin.portal.services.dto.PackageHousingDto;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestBatchService;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestLiteService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private final PrintRequestBatchService printRequestBatchService;

    /**
     * finds company staff
     *
     * @param suin the UIN of the staff
     * @return the found company staff with basic info and latest ritual
     */
    @GetMapping("/find/{suin}")
    public ResponseEntity<WsResponse<?>> findCompanyStaffBySuin(@PathVariable String suin) {
        log.info("Start findCompanyStaffBySuin  suin {}", suin);
        Optional<CompanyStaffVO> staffRitualBySuin = companyStaffService.findStaffRitualBySuin(suin);
        if (staffRitualBySuin.isPresent()) {
            log.info("Finish findCompanyStaffBySuin {},CompanyStaffVO Suin {}", "SUCCESS", staffRitualBySuin.isPresent() ? staffRitualBySuin.get().getSuin() : null);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(staffRitualBySuin).build());
        }
        log.info("Finish findCompanyStaffBySuin {}, {}", "FAILURE", "COMPANY_STAFF_NOT_FOUND");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                .body(WsError.builder().error(WsError.EWsError.COMPANY_STAFF_NOT_FOUND.getCode()).referenceNumber(suin).build()).build());

    }

    /**
     * finds company staff Or Applicant
     *
     * @param suin      the UIN of the staff
     * @param value     ShaaerNumber( uin/suin) or IdNumber(nin,Iqama number,passport Number)
     * @param valueType {0: shaaerDigitalId, 1: id number, 2: qr code or barcode}
     * @return the found company staff or applicant
     */
    @GetMapping("/search/{suin}/{value}/{valueType}")
    public ResponseEntity<WsResponse<?>> findApplicantOrStaff(@PathVariable String suin, @PathVariable String value, @PathVariable int valueType) {
        log.info("Start findApplicantOrStaff  suin: {}, value: {},   valueType: {}", suin, value, valueType);

        if (value.length() == 13 || value.length() == 12) {
            Optional<ApplicantStaffVO> staff;
            if (valueType == 0) {
                log.debug("findApplicantOrStaff value: {} findStaffBySuin ", value);
                staff = companyStaffService.findStaffBySuin(value);
            } else {
                log.debug("findApplicantOrStaff value: {} findStaffBySuinAndCardId ", value);
                staff = companyStaffService.findStaffBySuinAndCardId(value);
            }
            if (staff.isPresent()) {
                ApplicantStaffVO applicantStaffVO = staff.get();
                applicantStaffVO.setUserType(EUserType.STAFF.getId());
                log.info("Finish findCompanyStaffBySuin {}, applicantStaffVO FullNameEn {}", "SUCCESS", applicantStaffVO == null ? null : applicantStaffVO.getFullNameEn());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                        .body(applicantStaffVO).build());
            }
            log.info("Finish findCompanyStaffBySuin {}, {}", "FAILURE", "INVALID_INPUT");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(value).build()).build());
        }

        if (value.length() == 14 || value.length() == 15) {
            Optional<ApplicantStaffVO> applicant;

            log.debug("findApplicantOrStaff value: {} findApplicantRitualByUin ", value);
            applicant = applicantLiteService.findApplicantRitualByUin(value);

            if (applicant.isPresent()) {
                ApplicantStaffVO applicantStaffVO = applicant.get();
                applicantStaffVO.setUserType(EUserType.APPLICANT.getId());
                if (applicantStaffVO.getGroupLeaderSuin() != null && applicantStaffVO.getGroupLeaderSuin().equals(suin)) {
                    applicantStaffVO.setSameStaffGroup(true);
                }
                log.info("Finish findCompanyStaffBySuin {}, applicantStaffVO FullNameEn {}, SameStaffGroup {}", "SUCCESS", applicantStaffVO == null ? null : applicantStaffVO.getFullNameEn(), applicantStaffVO == null ? null : applicantStaffVO.isSameStaffGroup());
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                        .body(applicantStaffVO).build());
            }
            log.info("Finish findCompanyStaffBySuin {}, {}", "FAILURE", "INVALID_INPUT");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(value).build()).build());
        }

        if (valueType == 0 || valueType == 2) {
            log.info("Finish findCompanyStaffBySuin {}, {}", "FAILURE", "INVALID_INPUT");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(value).build()).build());

        }

        Optional<ApplicantStaffVO> staff = companyStaffService.findStaffByIdNumber(value);
        if (staff.isPresent()) {
            ApplicantStaffVO applicantStaffVO = staff.get();
            applicantStaffVO.setUserType(EUserType.STAFF.getId());
            log.info("Finish findCompanyStaffBySuin {}, applicantStaffVO FullNameEn {}", "SUCCESS", applicantStaffVO == null ? null : applicantStaffVO.getFullNameEn());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(applicantStaffVO).build());
        }

        Optional<ApplicantStaffVO> applicant = applicantLiteService.findApplicantRitualByIdNumber(value);
        if (applicant.isPresent()) {
            ApplicantStaffVO applicantStaffVO = applicant.get();
            applicantStaffVO.setUserType(EUserType.APPLICANT.getId());
            if (applicantStaffVO.getGroupLeaderSuin().equals(suin)) {
                applicantStaffVO.setSameStaffGroup(true);
            }
            log.info("Finish findCompanyStaffBySuin {}, applicantStaffVO FullNameEn {}, SameStaffGroup {}", "SUCCESS", applicantStaffVO == null ? null : applicantStaffVO.getFullNameEn(), applicantStaffVO == null ? null : applicantStaffVO.isSameStaffGroup());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                    .body(applicantStaffVO).build());
        }
        log.info("Finish findCompanyStaffBySuin {}, {}", "FAILURE", "INVALID_INPUT");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode()).referenceNumber(value).build()).build());

    }

    @GetMapping("/applicant-group/{suin}/{includeLocations}")
    public ResponseEntity<WsResponse<?>> loadApplicantGroupList(@PathVariable String suin, @PathVariable boolean includeLocations) {
        log.info("Start loadApplicantGroupList  suin: {}, includeLocations:{}", suin, includeLocations);
        List<ApplicantVo> groupApplicantListBySuin;
        if (includeLocations) {
            groupApplicantListBySuin = groupApplicantListService.findGroupApplicantsLastLocationsBySuin(suin);
        } else {
            groupApplicantListBySuin = groupApplicantListService.findGroupApplicantListBySuin(suin);
        }
        log.info("Finish loadApplicantGroupList {} groupApplicantListBySuinSize: {}", "SUCCESS", groupApplicantListBySuin == null ? null : groupApplicantListBySuin.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(groupApplicantListBySuin).build());
    }

    @GetMapping("/find/package-housing/{companyRitualSeason}")
    public ResponseEntity<WsResponse<?>> findStaffPackageHousingByCompanyRitualSeason(@PathVariable long companyRitualSeason) {
        log.info("Start findStaffPackageHousingByCompanyRitualSeason  companyRitualSeason: {}", companyRitualSeason);
        PackageHousingDto packageHousingDto = packageHousingService.findStaffPackageHousingByCompanyRitualSeason(companyRitualSeason);
        log.info("Finish findStaffPackageHousingByCompanyRitualSeason  packageHousingDto: {}", "SUCCESS", packageHousingDto == null ? null : packageHousingDto.getReferenceNumber());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(packageHousingDto).build());
    }




}
