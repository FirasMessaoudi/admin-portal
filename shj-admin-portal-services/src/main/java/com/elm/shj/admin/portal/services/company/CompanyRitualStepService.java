/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStepLookup;
import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualStepRepository;
import com.elm.shj.admin.portal.orm.repository.GroupApplicantListRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepDto;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepLookupDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffLiteDto;
import com.elm.shj.admin.portal.services.dto.ECompanyRitualStepEditMode;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.lookup.CompanyRitualStepLookupService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service handling company ritual step
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRitualStepService extends GenericService<JpaCompanyRitualStep, CompanyRitualStepDto, Long> {
    private final CompanyRitualStepRepository companyRitualStepRepository;
    private final GroupApplicantListRepository groupApplicantListRepository;
    private final CompanyRitualStepLookupService companyRitualStepLookupService;


    /**
     * find company ritual steps by applicant uin
     *
     * @return list of company ritual steps
     */
    public List<CompanyRitualStepDto> findCompanyRitualStepsByApplicantUin(String applicantUin) {
        try {
            //TODO: aflaifel: get the group id only, no need to get the group applicant list object.
            Optional<JpaGroupApplicantList> groupApplicantList = groupApplicantListRepository.findTopByApplicantUinOrderByCreationDateDesc(applicantUin);
            if (groupApplicantList.isPresent()) {
                List<JpaCompanyRitualStep> companyRitualSteps = companyRitualStepRepository.findByApplicantGroupIdOrderByStepIndexAsc(groupApplicantList.get().getApplicantGroup().getId());
                List<CompanyRitualStepDto> result = mapList(companyRitualSteps);
                result.forEach(companyRitualStep -> companyRitualStep.setReferenceNumber(companyRitualSteps.get(0).getApplicantGroup().getReferenceNumber()));
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * find company ritual steps by staff uin
     *
     * @return company ritual step that is active for current day
     */
    public List<CompanyRitualStepDto> findTodayCompanyRitualStepsByCompanyRitualSeasonId(long companyRitualSeasonId) {
        List<JpaCompanyRitualStep> jpaRitualSteps = companyRitualStepRepository.findByApplicantGroupCompanyRitualSeasonId(companyRitualSeasonId);
        List<CompanyRitualStepDto> ritualSteps = mapList(jpaRitualSteps);
        return ritualSteps.stream().filter(r -> {
            LocalDate date = r.getTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            return date.isEqual(LocalDate.now());

        }).collect(Collectors.toList());

    }

    /**
     * find company ritual steps by group Id
     *
     * @return company ritual steps
     */
    public List<CompanyRitualStepDto> findCompanyRitualStepsByCompanyRitualSeasonId(long companyRitualSeasonId) {
        List<JpaCompanyRitualStep> jpaRitualSteps = companyRitualStepRepository.findByApplicantGroupCompanyRitualSeasonId(companyRitualSeasonId);
        List<CompanyRitualStepDto> ritualSteps = mapList(jpaRitualSteps);
        return ritualSteps;

    }


    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean updateGroupRitualStep(long groupId, String stepCode, long hijriDate, String timeAsString) {
        log.info("Start updateGroupRitualStep groupId:{}  stepCode: {}, date: {}, time:{}",groupId, stepCode, hijriDate, timeAsString);
        LocalDate date = DateUtils.convertToLocalDate( DateUtils.toGregorian(hijriDate)).toLocalDate();
        LocalTime time = LocalTime.parse(timeAsString, DateTimeFormatter.ofPattern("HH:mm"));
        CompanyRitualStepLookupDto companyRitualSteplk = companyRitualStepLookupService.findCompanyRitualStep(stepCode);
        Optional<JpaCompanyRitualStep> companyRitualStep = companyRitualStepRepository.findByStepCodeAndApplicantGroupId(stepCode, groupId);
        if (companyRitualStep.isPresent() == false) {
            log.debug("updateGroupRitualStep companyRitualStep not found");
            return false;
        }if (companyRitualSteplk == null) {
            log.debug("updateGroupRitualStep companyRitualSteplk not found");
            return false;
        }
        CompanyRitualStepDto companyRitualStepDto = getMapper().fromEntity(companyRitualStep.get(), mappingContext);
        LocalDateTime stepDateTime = companyRitualStepDto.getTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        LocalDateTime newDateTime = null;
        if (companyRitualSteplk.getEditMode().equals(ECompanyRitualStepEditMode.DATE.name())) {
            newDateTime = stepDateTime.toLocalTime().atDate(date);
            log.debug("updateGroupRitualStep companyRitualSteplookup EditMode DATE");
        } else if (companyRitualSteplk.getEditMode().equals(ECompanyRitualStepEditMode.TIME.name())) {
            newDateTime = stepDateTime.toLocalDate().atTime(time);
            log.debug("updateGroupRitualStep companyRitualSteplookup EditMode TIME");
        } else if (companyRitualSteplk.getEditMode().equals(ECompanyRitualStepEditMode.DATE_TIME.name())) {
            log.debug("updateGroupRitualStep companyRitualSteplookup EditMode DATE_TIME");
            newDateTime = LocalDateTime.of(date, time);
        } else {
            log.debug("updateGroupRitualStep companyRitualSteplookup EditMode not found supported");
            return false;
        }
       Date stepDate=  Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
        log.debug("processing updateGroupRitualStep  stepDate: {}, companyRitualStepId:{}", stepDate,companyRitualStep.get().getId());
        int affectedRows = companyRitualStepRepository.updateGroupRitualStep( stepDate,companyRitualStep.get().getId());

        log.info("Finish updateGroupRitualStep  affectedRows: {}", affectedRows);
        return affectedRows != 0;
    }
}
