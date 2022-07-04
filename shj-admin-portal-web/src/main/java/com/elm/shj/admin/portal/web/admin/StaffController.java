/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.card.BadgeService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.BadgeVO;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDto;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateCategorizingDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * Main controller for staff management
 *
 * @author r.chebbi
 * @since 1.1.0
 */

@RestController
@RequestMapping(Navigation.API_COMPANY_STAFF)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StaffController {
    private final CompanyStaffService companyStaffService;
    private final BadgeService badgeService;
    @PostMapping("/find")
    @RolesAllowed(AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT)
    public Page<CompanyStaffDto> findCompanyStaff(@RequestBody NotificationTemplateCategorizingDto criteria,
                                                @RequestParam List<Long> excludedIds,
                                                Pageable pageable) {
        log.debug("Find Company Staff matching criteria...");
        return companyStaffService.findAllByCriteriaAndNotInExcludedIds(criteria, excludedIds, pageable);
    }

    @GetMapping("/find-by-ids")
    @RolesAllowed(AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT)
    public Page<CompanyStaffDto> findByIds(@RequestParam List<Long> ids, Pageable pageable) {
        log.debug("Find Company Staff matching criteria...");
        return companyStaffService.findByIds(ids, pageable);
    }

    @GetMapping("/count/registered-staff")
    @RolesAllowed(AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT)
    public long countRegisteredStaff() {
        log.debug("Count Company Staff having current ritual...");
        return companyStaffService.countRegisteredStaff();
    }
    @GetMapping("/find/registered-staff")
    @RolesAllowed(AuthorityConstants.USER_DEFINED_NOTIFICATION_MANAGEMENT)
    public List<CompanyStaffDto> findRegisteredStaff() {
        log.debug("find Company Staff having current ritual...");
        return companyStaffService.findRegisteredStaff();
    }

    /**
     * @param suin
     * @return staff full cards to be printed as bmp
     */
    @GetMapping("/staff/all/{suin}")
    public List<BadgeVO> findStaffBadgeFrontAndBack(@PathVariable String suin) {
        List<BadgeVO> badges = new ArrayList<>();
        badges.add(badgeService.generateStaffCard(suin, true));
        badges.add(badgeService.generateStaffBackBadge(suin, true));
        return badges;
    }

    /**
     * @param suin
     * @return staff partial card to be printed as bmp
     */
    @GetMapping("/staff/preprinted/{suin}")
    public List<BadgeVO> findStaffPartialCard(@PathVariable String suin) {
        List<BadgeVO> badges = new ArrayList<>();

        badges.add(badgeService.generatePrePrintedStaffCard(suin));
        return badges;
    }

    /**
     * @param suin
     * @return staff images for preview
     */
    @GetMapping("/staff/preview/{suin}")
    public List<BadgeVO> findStaffBadgePreview(@PathVariable String suin) {
        List<BadgeVO> badges = new ArrayList<>();
        badges.add(badgeService.generateStaffCard(suin, false));
        badges.add(badgeService.generateStaffBackBadge(suin, false));
        return badges;
    }



}
