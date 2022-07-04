/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.ApplicantRitualPackageVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageHousingRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private final ApplicantPackageHousingBasicService applicantPackageHousingBasicService;
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

    public List<ApplicantPackageHousingDto> findAllByApplicantPackageIdAndHousingPackageId(long applicantPackageId, long packageHousingId) {
        log.debug("Start findAllByApplicantPackageIdAndHousingPackageId applicantPackageId:{} , packageHousingId:{}", applicantPackageId, packageHousingId);
        List<JpaApplicantPackageHousing> jpaApplicantPackageHousing = applicantPackageHousingRepository.findByApplicantPackageIdAndPackageHousingId(applicantPackageId, packageHousingId);
        log.debug("Finish findByApplicantPackageIdAndHousingPackageId applicantPackageId:{} , packageHousingId:{}", applicantPackageId, packageHousingId);
        return !jpaApplicantPackageHousing.isEmpty() ? mapList(jpaApplicantPackageHousing) : Collections.emptyList();
    }

    @Transactional
    public Boolean updateApplicantHousingCamp(UpdateApplicantHousingCampDto updateApplicantHousingCampDto){
        log.info("Start updateApplicantHousingCamp .. {}", updateApplicantHousingCampDto);
        ApplicantDto applicantDto = applicantService.findByUin(updateApplicantHousingCampDto.getApplicantUin()).orElse(null);
        if(applicantDto == null)
            return false;

        ApplicantRitualPackageVo applicantPackage = applicantPackageService.findLatestApplicantRitualPackage(Long.parseLong(updateApplicantHousingCampDto.getApplicantUin()));
        ApplicantPackageDto applicantPackageDto = applicantPackageService.findByIdAndApplicantUin(applicantPackage.getApplicantPackageId(),Long.parseLong(updateApplicantHousingCampDto.getApplicantUin()));

        List<PackageHousingDto> menaHousingList = packageHousingService.findAllByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.MENA.name());
        List<PackageHousingDto> arafetHousingList = packageHousingService.findAllByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.ARAFAT.name());

        if (menaHousingList.isEmpty()) {
            PackageHousingDto packageHousingMena = PackageHousingDto.builder()
                    .ritualPackage(applicantPackageDto.getRitualPackage())
                    .housingZone(HousingZoneDto.builder().id(1L).build())
                    .referenceNumber(updateApplicantHousingCampDto.getMenaCampRefCode())
                    .typeCode(EHousingType.CAMP.name())
                    .siteCode(EHousingSite.MENA.name())
                    .locationNameEn(updateApplicantHousingCampDto.getMenaCampRefCode())
                    .locationNameAr(updateApplicantHousingCampDto.getMenaCampRefCode())
                    .validityStart(new GregorianCalendar(2022, Calendar.JULY, 5).getTime())
                    .validityEnd(new GregorianCalendar(2022, Calendar.JULY, 13).getTime())
                    .isDefault(Boolean.TRUE)
                    .build();
            packageHousingMena = packageHousingService.save(packageHousingMena);

            ApplicantPackageHousingBasicDto applicantPackageHousingBasicDto = ApplicantPackageHousingBasicDto.builder()
                    .applicantPackageId(applicantPackageDto.getId())
                    .packageHousingId(packageHousingMena.getId())
                    .siteCampRefCode(updateApplicantHousingCampDto.getMenaCampRefCode())
                    .siteBedNumber(updateApplicantHousingCampDto.getMenaBedNumber())
                    .siteCorridor(updateApplicantHousingCampDto.getMenaCorridor())
                    .siteFloor(updateApplicantHousingCampDto.getMenaFloor())
                    .siteRoom(updateApplicantHousingCampDto.getMenaRoom())
                    .siteTent(updateApplicantHousingCampDto.getMenaTent())
                    .build();
            applicantPackageHousingBasicService.save(applicantPackageHousingBasicDto);
        } else {
            menaHousingList.forEach(menaHousing ->{
                ApplicantPackageHousingDto applicantPackageHousingMena = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), menaHousing.getId());
                if (applicantPackageHousingMena != null) {
                    applicantPackageHousingMena.setSiteCampRefCode(updateApplicantHousingCampDto.getMenaCampRefCode());
                    applicantPackageHousingMena.setSiteBedNumber(updateApplicantHousingCampDto.getMenaBedNumber());
                    applicantPackageHousingMena.setSiteCorridor(updateApplicantHousingCampDto.getMenaCorridor());
                    applicantPackageHousingMena.setSiteFloor(updateApplicantHousingCampDto.getMenaFloor());
                    applicantPackageHousingMena.setSiteRoom(updateApplicantHousingCampDto.getMenaRoom());
                    applicantPackageHousingMena.setSiteTent(updateApplicantHousingCampDto.getMenaTent());
                    applicantPackageHousingMena.setUpdateDate(new Date());
                    save(applicantPackageHousingMena);

                } else {
                    ApplicantPackageHousingBasicDto applicantPackageHousingBasicDto = ApplicantPackageHousingBasicDto.builder()
                            .applicantPackageId(applicantPackageDto.getId())
                            .packageHousingId(menaHousing.getId())
                            .siteCampRefCode(updateApplicantHousingCampDto.getMenaCampRefCode())
                            .siteBedNumber(updateApplicantHousingCampDto.getMenaBedNumber())
                            .siteCorridor(updateApplicantHousingCampDto.getMenaCorridor())
                            .siteFloor(updateApplicantHousingCampDto.getMenaFloor())
                            .siteRoom(updateApplicantHousingCampDto.getMenaRoom())
                            .siteTent(updateApplicantHousingCampDto.getMenaTent())
                            .updateDate(new Date())
                            .build();
                    applicantPackageHousingBasicService.save(applicantPackageHousingBasicDto);
                }
            });
        }

        if (arafetHousingList.isEmpty()) {
            PackageHousingDto packageHousingMena = PackageHousingDto.builder()
                    .ritualPackage(applicantPackageDto.getRitualPackage())
                    .housingZone(HousingZoneDto.builder().id(1L).build())
                    .referenceNumber(updateApplicantHousingCampDto.getArafatCampRefCode())
                    .typeCode(EHousingType.CAMP.name())
                    .siteCode(EHousingSite.ARAFAT.name())
                    .locationNameEn(updateApplicantHousingCampDto.getArafatCampRefCode())
                    .locationNameAr(updateApplicantHousingCampDto.getArafatCampRefCode())
                    .validityStart(new GregorianCalendar(2022, Calendar.JULY, 8).getTime())
                    .validityEnd(new GregorianCalendar(2022, Calendar.JULY, 8).getTime())
                    .isDefault(Boolean.TRUE)
                    .build();
            packageHousingMena = packageHousingService.save(packageHousingMena);

            ApplicantPackageHousingBasicDto applicantPackageHousingBasicDto = ApplicantPackageHousingBasicDto.builder()
                    .applicantPackageId(applicantPackageDto.getId())
                    .packageHousingId(packageHousingMena.getId())
                    .siteCampRefCode(updateApplicantHousingCampDto.getArafatCampRefCode())
                    .siteBedNumber(updateApplicantHousingCampDto.getArafatBedNumber())
                    .siteCorridor(updateApplicantHousingCampDto.getArafatCorridor())
                    .siteFloor(updateApplicantHousingCampDto.getArafatFloor())
                    .siteRoom(updateApplicantHousingCampDto.getArafatRoom())
                    .siteTent(updateApplicantHousingCampDto.getArafatTent())
                    .build();
            applicantPackageHousingBasicService.save(applicantPackageHousingBasicDto);
        } else {
            arafetHousingList.forEach(arafatHousing -> {
                ApplicantPackageHousingDto applicantPackageHousingArafet = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), arafatHousing.getId());
                if (applicantPackageHousingArafet != null) {
                    applicantPackageHousingArafet.setSiteCampRefCode(updateApplicantHousingCampDto.getArafatCampRefCode());
                    applicantPackageHousingArafet.setSiteBedNumber(updateApplicantHousingCampDto.getArafatBedNumber());
                    applicantPackageHousingArafet.setSiteCorridor(updateApplicantHousingCampDto.getArafatCorridor());
                    applicantPackageHousingArafet.setSiteFloor(updateApplicantHousingCampDto.getArafatFloor());
                    applicantPackageHousingArafet.setSiteRoom(updateApplicantHousingCampDto.getArafatRoom());
                    applicantPackageHousingArafet.setSiteTent(updateApplicantHousingCampDto.getArafatTent());
                    applicantPackageHousingArafet.setUpdateDate(new Date());
                    save(applicantPackageHousingArafet);

                } else {
                    ApplicantPackageHousingBasicDto applicantPackageHousingBasicDto = ApplicantPackageHousingBasicDto.builder()
                            .applicantPackageId(applicantPackageDto.getId())
                            .packageHousingId(arafatHousing.getId())
                            .siteCampRefCode(updateApplicantHousingCampDto.getArafatCampRefCode())
                            .siteBedNumber(updateApplicantHousingCampDto.getArafatBedNumber())
                            .siteCorridor(updateApplicantHousingCampDto.getArafatCorridor())
                            .siteFloor(updateApplicantHousingCampDto.getArafatFloor())
                            .siteRoom(updateApplicantHousingCampDto.getArafatRoom())
                            .siteTent(updateApplicantHousingCampDto.getArafatTent())
                            .updateDate(new Date())
                            .build();
                    applicantPackageHousingBasicService.save(applicantPackageHousingBasicDto);
                }
            });
        }
        log.info("Finish updateApplicantHousingCamp");
        return true;
    }

    public ApplicantCampDetailDto findApplicantCampDetails(String applicantUin){
        log.info("Start findApplicantCampDetails .. {}", applicantUin);
        ApplicantDto applicantDto = applicantService.findByUin(applicantUin).orElse(null);
        ApplicantCampDetailDto applicantCampDetail = new ApplicantCampDetailDto();
        if(applicantDto == null) {
            log.info("Finish findApplicantCampDetails not found with applicantUin:{}", applicantUin);
            return applicantCampDetail;
        }

        ApplicantRitualPackageVo applicantPackage = applicantPackageService.findLatestApplicantRitualPackage(Long.parseLong(applicantUin));
        ApplicantPackageDto applicantPackageDto = applicantPackageService.findByIdAndApplicantUin(applicantPackage.getApplicantPackageId(),Long.parseLong(applicantUin));

        PackageHousingDto menaHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.MENA.name());
        PackageHousingDto arafatHousing = packageHousingService.findByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.ARAFAT.name());

        if (menaHousing != null) {
            ApplicantPackageHousingDto applicantPackageHousingMena = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), menaHousing.getId());
            if (applicantPackageHousingMena != null) {
                //set applicant package housing mena information
                applicantCampDetail.setMenaCampRefCode(applicantPackageHousingMena.getSiteCampRefCode());
                applicantCampDetail.setMenaCorridor(applicantPackageHousingMena.getSiteCorridor());
                applicantCampDetail.setMenaFloor(applicantPackageHousingMena.getSiteFloor());
                applicantCampDetail.setMenaTent(applicantPackageHousingMena.getSiteTent());
                applicantCampDetail.setMenaBedNumber(applicantPackageHousingMena.getSiteBedNumber());
                applicantCampDetail.setMenaRoom(applicantPackageHousingMena.getSiteRoom());

            }
        }

        if(arafatHousing != null) {
            ApplicantPackageHousingDto applicantPackageHousingArafat = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), arafatHousing.getId());
            if (applicantPackageHousingArafat != null) {
                //set applicant package housing arafat information
                applicantCampDetail.setArafatCampRefCode(applicantPackageHousingArafat.getSiteCampRefCode());
                applicantCampDetail.setArafatCorridor(applicantPackageHousingArafat.getSiteCorridor());
                applicantCampDetail.setArafatFloor(applicantPackageHousingArafat.getSiteFloor());
                applicantCampDetail.setArafatTent(applicantPackageHousingArafat.getSiteTent());
                applicantCampDetail.setArafatBedNumber(applicantPackageHousingArafat.getSiteBedNumber());
                applicantCampDetail.setArafatRoom(applicantPackageHousingArafat.getSiteRoom());
            }
        }
        log.info("Finish findApplicantCampDetails .. {}", applicantUin);
        return applicantCampDetail;
    }

    public GroupApplicantCampDto findGroupApplicantCampReferenceNumber(Long groupId) {
        log.info("Start findGroupApplicantCampReferenceNumber with groupId: {}", groupId);
        List<Long> applicantIdlIst = applicantService.findApplicantIdByGroupId(groupId);
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
                if (applicantPackageHousingMena != null) {
                    applicantPackageHousingMenaList.add(applicantPackageHousingMena);
                } else {
                    applicantPackageHousingMenaList.add(ApplicantPackageHousingDto.builder().build());
                }
            }

            if (arafatHousing != null) {
                ApplicantPackageHousingDto applicantPackageHousingArafat = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), arafatHousing.getId());
                if (applicantPackageHousingArafat != null) {
                    applicantPackageHousingArafatList.add(applicantPackageHousingArafat);
                } else {
                    applicantPackageHousingArafatList.add(ApplicantPackageHousingDto.builder().build());
                }
            }
        });

        if(!applicantPackageHousingMenaList.isEmpty()) {
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
        log.info("Finish findGroupApplicantCampReferenceNumber with groupId: {}", groupId);
        return groupApplicantCamp;
    }

    @Transactional
    public Boolean updateGroupApplicantHousingCamp(GroupApplicantCampDto groupApplicantCamp){
        log.info("Start updateGroupApplicantHousingCamp with GroupApplicantCampDto: {}", groupApplicantCamp);
        List<Long> applicantIdlIst = applicantService.findApplicantIdByGroupId(groupApplicantCamp.getGroupId());
        log.info("applicantIdlIst ... {}" + applicantIdlIst) ;

        if(applicantIdlIst.isEmpty()){
            return false;
        }
        applicantIdlIst.forEach(applicantId -> {
            ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantId);

            List<PackageHousingDto> menaHousingList = packageHousingService.findAllByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.MENA.name());
            List<PackageHousingDto> arafetHousingList = packageHousingService.findAllByRitualPackageIdAndSiteCode(applicantPackageDto.getRitualPackage().getId(), ECampSite.ARAFAT.name());

            if (menaHousingList.isEmpty()) {
                PackageHousingDto packageHousingMena = PackageHousingDto.builder()
                        .ritualPackage(applicantPackageDto.getRitualPackage())
                        .housingZone(HousingZoneDto.builder().id(1L).build())
                        .referenceNumber(groupApplicantCamp.getMenaCampRefNumber())
                        .typeCode(EHousingType.CAMP.name())
                        .siteCode(EHousingSite.MENA.name())
                        .locationNameEn(groupApplicantCamp.getMenaCampRefNumber())
                        .locationNameAr(groupApplicantCamp.getMenaCampRefNumber())
                        .validityStart(new GregorianCalendar(2022, Calendar.JULY, 5).getTime())
                        .validityEnd(new GregorianCalendar(2022, Calendar.JULY, 13).getTime())
                        .isDefault(Boolean.TRUE)
                        .build();
                packageHousingMena = packageHousingService.save(packageHousingMena);

                ApplicantPackageHousingBasicDto applicantPackageHousingBasicDto = ApplicantPackageHousingBasicDto.builder()
                        .applicantPackageId(applicantPackageDto.getId())
                        .packageHousingId(packageHousingMena.getId())
                        .siteCampRefCode(groupApplicantCamp.getMenaCampRefNumber())
                        .build();
                applicantPackageHousingBasicService.save(applicantPackageHousingBasicDto);
            } else {
                menaHousingList.forEach(menaHousing -> {
                    ApplicantPackageHousingDto applicantPackageHousingMena = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), menaHousing.getId());
                    if (applicantPackageHousingMena != null) {
                        applicantPackageHousingMena.setSiteCampRefCode(groupApplicantCamp.getMenaCampRefNumber());
                        applicantPackageHousingMena.setUpdateDate(new Date());
                        save(applicantPackageHousingMena);

                    } else {
                        ApplicantPackageHousingBasicDto applicantPackageHousingBasicDto = ApplicantPackageHousingBasicDto.builder()
                                .applicantPackageId(applicantPackageDto.getId())
                                .packageHousingId(menaHousing.getId())
                                .siteCampRefCode(groupApplicantCamp.getMenaCampRefNumber())
                                .updateDate(new Date())
                                .build();
                        applicantPackageHousingBasicService.save(applicantPackageHousingBasicDto);
                    }
                });
            }

            if (arafetHousingList.isEmpty()) {
                PackageHousingDto packageHousingMena = PackageHousingDto.builder()
                        .ritualPackage(applicantPackageDto.getRitualPackage())
                        .housingZone(HousingZoneDto.builder().id(1L).build())
                        .referenceNumber(groupApplicantCamp.getArafatCampRefNumber())
                        .typeCode(EHousingType.CAMP.name())
                        .siteCode(EHousingSite.ARAFAT.name())
                        .locationNameEn(groupApplicantCamp.getArafatCampRefNumber())
                        .locationNameAr(groupApplicantCamp.getArafatCampRefNumber())
                        .validityStart(new GregorianCalendar(2022, Calendar.JULY, 8).getTime())
                        .validityEnd(new GregorianCalendar(2022, Calendar.JULY, 8).getTime())
                        .isDefault(Boolean.TRUE)
                        .build();
                packageHousingMena = packageHousingService.save(packageHousingMena);

                ApplicantPackageHousingBasicDto applicantPackageHousingBasicDto = ApplicantPackageHousingBasicDto.builder()
                        .applicantPackageId(applicantPackageDto.getId())
                        .packageHousingId(packageHousingMena.getId())
                        .siteCampRefCode(groupApplicantCamp.getArafatCampRefNumber())
                        .build();
                applicantPackageHousingBasicService.save(applicantPackageHousingBasicDto);
            } else {
                arafetHousingList.forEach(arafatHousing -> {
                    ApplicantPackageHousingDto applicantPackageHousingArafet = findByApplicantPackageIdAndHousingPackageId(applicantPackageDto.getId(), arafatHousing.getId());
                    if (applicantPackageHousingArafet != null) {
                        applicantPackageHousingArafet.setSiteCampRefCode(groupApplicantCamp.getArafatCampRefNumber());
                        applicantPackageHousingArafet.setUpdateDate(new Date());
                        save(applicantPackageHousingArafet);

                    } else {
                        ApplicantPackageHousingBasicDto applicantPackageHousingBasicDto = ApplicantPackageHousingBasicDto.builder()
                                .applicantPackageId(applicantPackageDto.getId())
                                .packageHousingId(arafatHousing.getId())
                                .siteCampRefCode(groupApplicantCamp.getArafatCampRefNumber())
                                .updateDate(new Date())
                                .build();
                        applicantPackageHousingBasicService.save(applicantPackageHousingBasicDto);
                    }
                });
            }
        });
        log.info("Finish updateGroupApplicantHousingCamp");
        return true;
    }
}
