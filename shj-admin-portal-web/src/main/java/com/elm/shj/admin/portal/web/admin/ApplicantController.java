/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.card.BadgeService;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.BadgeVO;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateCategorizingDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.ws.WsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * Main controller for applicant management pages
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_APPLICANTS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantController {

    private final ApplicantService applicantService;
    private final BadgeService badgeService;
    /**
     * finds a list of applicants matching criteria
     *
     * @return the list of applicants or empty list
     */
    @PostMapping("/find")
    @RolesAllowed(AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT)
    public Page<ApplicantDto> findApplicants(@RequestBody NotificationTemplateCategorizingDto criteria,
                                             @RequestParam List<Long> excludedIds,
                                             Pageable pageable) {
        log.debug("Find applicants matching criteria...");
        return applicantService.findAllByCriteriaAndNotInExcludedIds(criteria, excludedIds, pageable);
    }

    @GetMapping("/find-by-ids")
    @RolesAllowed(AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT)
    public Page<ApplicantDto> findByIds(@RequestParam List<Long> ids, Pageable pageable) {
        log.debug("Find applicants matching criteria...");
        return applicantService.findByIds(ids, pageable);
    }

    @GetMapping("/count/having-current-ritual")
    @RolesAllowed(AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT)
    public long countApplicantHavingCurrentRitual() {
        log.debug("Count applicants having current ritual...");
        return applicantService.countHavingActiveRitual();
    }

    @PostMapping("/count/categorized")
    @RolesAllowed(AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT)
    public long countCategorizedApplicants(@RequestBody NotificationTemplateCategorizingDto criteria) {
        log.debug("Count applicants having current ritual...");
        return applicantService.countAllByCriteria(criteria, null);
    }

    @GetMapping("/badge/generate/{applicantUin}/{withQr}")
    public BadgeVO findApplicantBadge(@PathVariable String applicantUin, @PathVariable boolean withQr) {
        return badgeService.generateApplicantBadge(applicantUin, withQr);

    }

    @GetMapping("/badge/applicant/frontback/{applicantUin}")
    public List<BadgeVO> findApplicantBadgeFrontAndBack(@PathVariable String applicantUin) {
        List<BadgeVO> badges = new ArrayList<>();
        badges.add(badgeService.generateApplicantBadge(applicantUin, false));
        badges.add(badgeService.generateBackBadge(applicantUin));
        return badges;
    }

}
