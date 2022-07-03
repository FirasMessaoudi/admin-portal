/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaPackageHousing;
import com.elm.shj.admin.portal.orm.entity.JpaPackageTransportation;
import com.elm.shj.admin.portal.orm.repository.PackageTransportationRepository;
import com.elm.shj.admin.portal.services.dto.PackageHousingDto;
import com.elm.shj.admin.portal.services.dto.PackageTransportationDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service handling package Transportation
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PackageTransportationService extends GenericService<JpaPackageTransportation, PackageTransportationDto, Long> {
    private final PackageTransportationRepository packageTransportationRepository;

    public List<PackageTransportationDto> findByRitualPackageId(long id) {
        log.info("Start findByRitualPackageId {} id", id);
        List<PackageTransportationDto> packageTransportationDtoList= mapList(packageTransportationRepository.findByRitualPackageId(id));
        log.info("Finish findByRitualPackageId {} id", id);
        return packageTransportationDtoList;
    }

    public PackageTransportationDto findByRitualPackageIdAndTypeCode(long id, String typeCode) {
        log.info("PackageHousingService ::: Start findByRitualPackageIdAndTypeCode ::: ritualPackage: {}", id);
        Optional<JpaPackageTransportation> jpaPackageTransportation = packageTransportationRepository.findTopByRitualPackageIdAndTypeCode(id, typeCode);
        if (jpaPackageTransportation.isPresent()) {
            log.info("PackageHousingService ::: Finish findByRitualPackageIdAndTypeCode ::: ritualPackage: {}", id);
            return getMapper().fromEntity(jpaPackageTransportation.get(), mappingContext);
        }
        log.info("PackageHousingService ::: Finish findByRitualPackageIdAndTypeCode not found ::: ritualPackage: {}", id);
        return null;

    }

    public List<PackageTransportationDto> findAllByRitualPackageIdAndTypeCode(long id, String typeCode) {
        log.info("PackageHousingService ::: Start findAllByRitualPackageIdAndTypeCode ::: ritualPackage: {}", id);
        List<JpaPackageTransportation> jpaPackageTransportation = packageTransportationRepository.findAllByRitualPackageIdAndTypeCode(id, typeCode);
        if (!jpaPackageTransportation.isEmpty()) {
            log.info("PackageHousingService ::: Finish findByRitualPackageIdAndTypeCode ::: ritualPackage: {}", id);
            return mapList(jpaPackageTransportation);
        }
        log.info("PackageHousingService ::: Finish findByRitualPackageIdAndTypeCode not found ::: ritualPackage: {}", id);
        return Collections.emptyList();

    }

}
