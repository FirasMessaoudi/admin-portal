/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageHousingRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service handling applicant package housing
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantPackageHousingService extends GenericService<JpaApplicantPackageHousing, ApplicantPackageHousingDto, Long> {

    private final ApplicantPackageHousingRepository applicantPackageHousingRepository;
    private final ApplicantService applicantService;
    private final ApplicantPackageService applicantPackageService;
    private final PackageHousingService packageHousingService;
    private static final String GROUP_APPLICANT_CAMP_MULTI= "multi";

    public List<ApplicantPackageHousingDto> findApplicantPackageHousingByUinAndApplicantPackageId(long applicantUin, long companyRitualSeasonId) {
        log.debug("Start findApplicantPackageHousingByUinAndApplicantPackageId uin:{} , companyRitualSeasonId:{}", applicantUin, companyRitualSeasonId);
        List<JpaApplicantPackageHousing> jpaApplicantPackageHousings = applicantPackageHousingRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageId(applicantUin, companyRitualSeasonId);
        log.debug("Finish findAllByApplicantPackageApplicantUinAndPackageCateringPackageHousingId uin:{} , ApplicantPackageHousingsListSize:{}", applicantUin, jpaApplicantPackageHousings.size());
        return getMapper().fromEntityList(jpaApplicantPackageHousings, mappingContext);
    }

    public ApplicantPackageHousingDto findByApplicantPackageIdAndHousingPackageId(long applicantPackageId, long packageHousingId) {
        log.debug("Start findByApplicantPackageIdAndHousingPackageId applicantPackageId:{} , packageHousingId:{}", applicantPackageId, packageHousingId);
        Optional<JpaApplicantPackageHousing> jpaApplicantPackageHousing = applicantPackageHousingRepository.findTopByApplicantPackageIdAndPackageHousingId(applicantPackageId, packageHousingId);
        log.debug("Finish findByApplicantPackageIdAndHousingPackageId applicantPackageId:{} , packageHousingId:{}", applicantPackageId, packageHousingId);
        return jpaApplicantPackageHousing.isPresent() ? getMapper().fromEntity(jpaApplicantPackageHousing.get(), mappingContext) : null;
    }

    @Transactional
    public Boolean updateApplicantHousingCamp(UpdateApplicantHousingCampDto updateApplicantHousingCampDto){
        log.info("updateApplicantHousingCampDto .. {}", updateApplicantHousingCampDto);
        ApplicantDto applicantDto = applicantService.findByUin(updateApplicantHousingCampDto.getApplicantUin()).orElse(null);
        if(applicantDto == null)
            return false;

        ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantDto.getId());

        PackageHousingDto menaHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.MENA.name());
        PackageHousingDto arafatHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.ARAFAT.name());

        if (menaHousing == null && arafatHousing == null) {
            return  false;
        }

        ApplicantPackageHousingDto applicantPackageHousingMena = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), menaHousing.getId());
        ApplicantPackageHousingDto applicantPackageHousingArafat = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), arafatHousing.getId());

        if (applicantPackageHousingMena != null && applicantPackageHousingArafat != null) {
            //update applicant package housing mena information
            applicantPackageHousingMena.setSiteCampRefCode(updateApplicantHousingCampDto.getMenaCampRefCode());
            applicantPackageHousingMena.setSiteBedNumber(updateApplicantHousingCampDto.getMenaBedNumber());
            applicantPackageHousingMena.setSiteCorridor(updateApplicantHousingCampDto.getMenaCorridor());
            applicantPackageHousingMena.setSiteFloor(updateApplicantHousingCampDto.getMenaFloor());
            applicantPackageHousingMena.setSiteRoom(updateApplicantHousingCampDto.getMenaRoom());
            applicantPackageHousingMena.setSiteTent(updateApplicantHousingCampDto.getMenaTent());
            save(applicantPackageHousingMena);

            //update applicant package housing arafat information
            applicantPackageHousingArafat.setSiteCampRefCode(updateApplicantHousingCampDto.getArafatCampRefCode());
            applicantPackageHousingArafat.setSiteBedNumber(updateApplicantHousingCampDto.getArafatBedNumber());
            applicantPackageHousingArafat.setSiteCorridor(updateApplicantHousingCampDto.getArafatCorridor());
            applicantPackageHousingArafat.setSiteFloor(updateApplicantHousingCampDto.getArafatFloor());
            applicantPackageHousingArafat.setSiteRoom(updateApplicantHousingCampDto.getArafatRoom());
            applicantPackageHousingArafat.setSiteTent(updateApplicantHousingCampDto.getArafatTent());
            save(applicantPackageHousingArafat);
            return true;
        }

        return false;
    }

    public ApplicantCampDetailDto findApplicantCampDetails(String applicantUin){
        log.info("updateApplicantHousingCampDto .. {}", applicantUin);
        ApplicantDto applicantDto = applicantService.findByUin(applicantUin).orElse(null);
        ApplicantCampDetailDto applicantCampDetail = new ApplicantCampDetailDto();
        if(applicantDto == null)
            return applicantCampDetail;

        ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantDto.getId());

        PackageHousingDto menaHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.MENA.name());
        PackageHousingDto arafatHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.ARAFAT.name());

        if (menaHousing == null && arafatHousing == null) {
            return  applicantCampDetail;
        }

        ApplicantPackageHousingDto applicantPackageHousingMena = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), menaHousing.getId());
        ApplicantPackageHousingDto applicantPackageHousingArafat = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), arafatHousing.getId());

        if (applicantPackageHousingMena != null && applicantPackageHousingArafat != null) {
            //set applicant package housing mena information
            applicantCampDetail.setMenaCampRefCode(applicantPackageHousingMena.getSiteCampRefCode());
            applicantCampDetail.setMenaCorridor(applicantPackageHousingMena.getSiteCorridor());
            applicantCampDetail.setMenaFloor(applicantPackageHousingMena.getSiteFloor());
            applicantCampDetail.setMenaTent(applicantPackageHousingMena.getSiteTent());
            applicantCampDetail.setMenaBedNumber(applicantPackageHousingMena.getSiteBedNumber());
            applicantCampDetail.setMenaRoom(applicantPackageHousingMena.getSiteRoom());

            //set applicant package housing arafat information
            applicantCampDetail.setArafatCampRefCode(applicantPackageHousingArafat.getSiteCampRefCode());
            applicantCampDetail.setArafatCorridor(applicantPackageHousingArafat.getSiteCorridor());
            applicantCampDetail.setArafatFloor(applicantPackageHousingArafat.getSiteFloor());
            applicantCampDetail.setArafatTent(applicantPackageHousingArafat.getSiteTent());
            applicantCampDetail.setArafatBedNumber(applicantPackageHousingArafat.getSiteBedNumber());
            applicantCampDetail.setArafatRoom(applicantPackageHousingArafat.getSiteRoom());
        }

        return applicantCampDetail;
    }

    public GroupApplicantCampDto findGroupApplicantCampReferenceNumber(Long groupId){
        log.info("groupId .. {}", groupId);
        List<Long> applicantIdlIst = applicantService.findApplicantByGroupId(groupId);
        log.info("applicantIdlIst ... {}" + applicantIdlIst) ;

        List<ApplicantPackageHousingDto> applicantPackageHousingMenaList = new ArrayList<>();
        List<ApplicantPackageHousingDto> applicantPackageHousingArafatList = new ArrayList<>();
        GroupApplicantCampDto groupApplicantCamp = new GroupApplicantCampDto();

        applicantIdlIst.forEach(applicantId -> {
            ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantId);

            PackageHousingDto menaHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.MENA.name());
            PackageHousingDto arafatHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.ARAFAT.name());

            if (menaHousing != null) {
                ApplicantPackageHousingDto applicantPackageHousingMena = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), menaHousing.getId());
                applicantPackageHousingMenaList.add(applicantPackageHousingMena);
            }

            if (arafatHousing != null) {
                ApplicantPackageHousingDto applicantPackageHousingArafat = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), arafatHousing.getId());
                applicantPackageHousingArafatList.add(applicantPackageHousingArafat);
            }
        });

        if(!applicantPackageHousingMenaList.isEmpty()){
            if(applicantPackageHousingMenaList.stream().map(ApplicantPackageHousingDto::getSiteCampRefCode).distinct().count() <= 1){
                groupApplicantCamp.setMenaCampRefNumber(applicantPackageHousingMenaList.get(0).getSiteCampRefCode());
            } else {
                groupApplicantCamp.setMenaCampRefNumber(GROUP_APPLICANT_CAMP_MULTI);
            }
        }

        if(!applicantPackageHousingArafatList.isEmpty()){
            if(applicantPackageHousingArafatList.stream().map(ApplicantPackageHousingDto::getSiteCampRefCode).distinct().count() <= 1){
                groupApplicantCamp.setArafatCampRefNumber(applicantPackageHousingArafatList.get(0).getSiteCampRefCode());
            } else {
                groupApplicantCamp.setArafatCampRefNumber(GROUP_APPLICANT_CAMP_MULTI);
            }
        }
        groupApplicantCamp.setGroupId(groupId);

        return groupApplicantCamp;
    }

    @Transactional
    public Boolean updateGroupApplicantHousingCamp(GroupApplicantCampDto groupApplicantCamp){
        log.info("groupId .. {}", groupApplicantCamp.getGroupId());
        List<Long> applicantIdlIst = applicantService.findApplicantByGroupId(groupApplicantCamp.getGroupId());
        log.info("applicantIdlIst ... {}" + applicantIdlIst) ;

        if(applicantIdlIst.isEmpty()){
            return false;
        }
        applicantIdlIst.forEach(applicantId -> {
            ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantId);

            PackageHousingDto menaHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.MENA.name());
            PackageHousingDto arafatHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.ARAFAT.name());

            if (menaHousing != null) {
                ApplicantPackageHousingDto applicantPackageHousingMena = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), menaHousing.getId());
                applicantPackageHousingMena.setSiteCampRefCode(groupApplicantCamp.getMenaCampRefNumber());
                save(applicantPackageHousingMena);
            }

            if (arafatHousing != null) {
                ApplicantPackageHousingDto applicantPackageHousingArafat = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), arafatHousing.getId());
                applicantPackageHousingArafat.setSiteCampRefCode(groupApplicantCamp.getArafatCampRefNumber());
                save(applicantPackageHousingArafat);
            }
        });

        return true;
    }
}
