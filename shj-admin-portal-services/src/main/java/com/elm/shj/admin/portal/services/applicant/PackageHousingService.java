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
        return mapList(packageHousingRepository.findAllCamps());
    }

    public PackageHousingDto findCamp(long applicantPackageId, long uin) {
        Optional<JpaApplicantPackageHousing> applicantPackageHousing = applicantPackageHousingRepository.findTopByApplicantPackageApplicantUinAndApplicantPackageIdOrderByCreationDateDesc(uin, applicantPackageId);
        if (applicantPackageHousing.isPresent()) {
            return getMapper().fromEntity(applicantPackageHousing.get().getPackageHousing(), mappingContext);
        } else {
            return null;
        }
    }

    public List<PackageHousingDto> findByRitualPackageId(long id) {
        return mapList(packageHousingRepository.findByRitualPackageId(id));
    }

    public PackageHousingDto findStaffPackageHousingByCompanyRitualSeason(long companyRitualSeason) {
        JpaPackageHousing jpaPackageHousing = packageHousingRepository.findStaffPackageHousingByCompanyRitualSeason(companyRitualSeason);
        return getMapper().fromEntity(jpaPackageHousing,mappingContext);
    }
}
