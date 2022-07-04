/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import com.elm.shj.admin.portal.orm.entity.JpaPackageHousing;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageHousingRepository;
import com.elm.shj.admin.portal.orm.repository.PackageHousingRepository;
import com.elm.shj.admin.portal.services.dto.PackageHousingDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service handling package housing
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PackageHousingService extends GenericService<JpaPackageHousing, PackageHousingDto, Long> {

    private final PackageHousingRepository packageHousingRepository;
    private final ApplicantPackageHousingRepository applicantPackageHousingRepository;

    public List<PackageHousingDto> findAllCamps() {
        log.info("PackageHousingService ::: Start findAllCamps ");
        List<PackageHousingDto> packageHousingDtos = mapList(packageHousingRepository.findAllCamps());
        log.info("PackageHousingService ::: Finish findAllCamps ::: packageHousingDtosListSize: {} ", packageHousingDtos.size());
        return  packageHousingDtos;
    }

    public PackageHousingDto findCamp(long applicantPackageId, long uin) {
        log.info("PackageHousingService ::: Start findCamp ::: applicantPackageId: {}, uin: {}",applicantPackageId, uin);
        Optional<JpaApplicantPackageHousing> applicantPackageHousing = applicantPackageHousingRepository.findTopByApplicantPackageApplicantUinAndApplicantPackageIdOrderByCreationDateDesc(uin, applicantPackageId);
        if (applicantPackageHousing.isPresent()) {
            log.info("PackageHousingService ::: Finish findCamp BedNumber: {}, RoomNumber: {}",applicantPackageHousing.get().getBedNumber(), applicantPackageHousing.get().getRoomNumber());
            return getMapper().fromEntity(applicantPackageHousing.get().getPackageHousing(), mappingContext);
        } else {
            log.info("PackageHousingService ::: Finish findCamp not found with applicantPackageId: {}, uin: {}",applicantPackageId, uin);
            return null;
        }
    }

    public List<PackageHousingDto> findByRitualPackageId(long id) {
        log.info("PackageHousingService ::: Start findByRitualPackageId ::: id: {}",id);
        List<PackageHousingDto> packageHousingDtos = mapList(packageHousingRepository.findByRitualPackageId(id));
        log.info("PackageHousingService ::: Finish packageHousingDtosListsize: {}", packageHousingDtos.size());
        return packageHousingDtos;
    }

    public PackageHousingDto findStaffPackageHousingByCompanyRitualSeason(long companyRitualSeason) {
        log.info("PackageHousingService ::: Start findStaffPackageHousingByCompanyRitualSeason ::: companyRitualSeason: {}", companyRitualSeason);
        JpaPackageHousing jpaPackageHousing = packageHousingRepository.findStaffPackageHousingByCompanyRitualSeason(companyRitualSeason);
        if(jpaPackageHousing == null) {
            log.info("PackageHousingService ::: Finish findStaffPackageHousingByCompanyRitualSeason not found ::: companyRitualSeason: {}", companyRitualSeason);
            return null;
        }
        log.info("PackageHousingService ::: Start findStaffPackageHousingByCompanyRitualSeason ::: jpaPackageHousingLocationNameEn: {}", jpaPackageHousing.getLocationNameEn());
        return getMapper().fromEntity(jpaPackageHousing, mappingContext);
    }

    public PackageHousingDto findByRitualPackageIdAndSiteCode(long id, String siteCode) {
        log.info("PackageHousingService ::: Start findByRitualPackageIdAndSiteCode ::: ritualPackage: {}", id);
        Optional<JpaPackageHousing> jpaPackageHousing = packageHousingRepository.findTopByRitualPackageIdAndSiteCode(id, siteCode);
        if (jpaPackageHousing.isPresent()) {
            log.info("Finish findByRitualPackageIdAndSiteCode ::: ritualPackage: {}", id);
            return getMapper().fromEntity(jpaPackageHousing.get(), mappingContext);
        }
        log.info("Finish findByRitualPackageIdAndSiteCode not found ::: ritualPackage: {}", id);
        return null;

    }

    public List<PackageHousingDto> findAllByRitualPackageIdAndSiteCode(long id, String siteCode) {
        log.info("PackageHousingService ::: Start findAllByRitualPackageIdAndSiteCode ::: packageId: {}", id);
        List<JpaPackageHousing> jpaPackageHousing = packageHousingRepository.findAllByRitualPackageIdAndSiteCode(id, siteCode);
        if (!jpaPackageHousing.isEmpty()) {
            log.info("Finish findAllByRitualPackageIdAndSiteCode ::: packageId: {}", id);
            return mapList(jpaPackageHousing);
        }
        log.info("Finish findAllByRitualPackageIdAndSiteCode not found ::: packageId: {}", id);
        return Collections.emptyList();

    }
}
