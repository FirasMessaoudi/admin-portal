/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonLiteService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Scheduler to add automatically group leader and relatives to applicant chat contacts
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantChatContactScheduler {

    private final ApplicantService applicantService;
    private final ApplicantChatContactService applicantChatContactService;
    private final CompanyStaffService companyStaffService;
    private final CompanyRitualSeasonLiteService companyRitualSeasonLiteService;
    private final ApplicantRitualService applicantRitualService;

    /**
     * Scheduled job to create/update staff chat contacts
     */
    @Scheduled(cron = "0 0/1 * * * *")
    @SchedulerLock(name = "add-chat-contacts-task")
    public void updateApplicantStaffContacts() {
        log.debug("Update staff chat contacts scheduler started...");
        String staffTitle = ECompanyStaffTitle.GROUP_LEADER.name();

        applicantService.findAllNotHavingChatContactWithTitle(staffTitle).forEach(applicant -> {
            // create staff chat contact for each applicant who belongs to a group
            String applicantUin = applicant.getDigitalIds().get(0).getUin();
            CompanyRitualSeasonLiteDto latestCompanyRitualSeason = companyRitualSeasonLiteService.getLatestCompanyRitualSeasonByApplicantUin(Long.parseLong(applicantUin));
            Optional<CompanyStaffDto> companyStaff = companyStaffService.findGroupLeaderByApplicantUin(applicantUin, latestCompanyRitualSeason.getId());

            applicantChatContactService.save(ApplicantChatContactDto
                    .builder()
                    .applicantUin(applicantUin)
                    .contactUin(companyStaff.map(s -> s.getDigitalIds().get(0).getSuin()).orElse(null))
                    .mobileNumber(companyStaff.map(CompanyStaffDto::getMobileNumber).orElse(null))
                    .applicantRitual(applicantRitualService.findByApplicantUinAndCompanyRitualSeasonId(applicantUin, latestCompanyRitualSeason.getId()))
                    .avatar(companyStaff.map(CompanyStaffDto::getPhoto).orElse(null))
                    .systemDefined(true)
                    .staffTitleCode(staffTitle)
                    .alias(staffTitle)
                    .type(ContactTypeLookupDto.builder().id(EChatContactType.STAFF.getId()).build())
                    .build()
            );
        });
    }
}
