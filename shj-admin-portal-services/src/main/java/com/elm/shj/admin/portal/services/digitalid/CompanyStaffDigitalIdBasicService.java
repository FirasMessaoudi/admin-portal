/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.digitalid;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalIdBasic;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffDigitalIdBasicRepository;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDigitalIdBasicDto;
import com.elm.shj.admin.portal.services.dto.EDigitalIdStatus;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service handling company staff digital id basic
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffDigitalIdBasicService extends GenericService<JpaCompanyStaffDigitalIdBasic, CompanyStaffDigitalIdBasicDto, Long> {

    private final CompanyStaffDigitalIdBasicRepository companyStaffDigitalIdBasicRepository;


    /**
     * @param staffId
     * @param season
     * @return list of companyStaffDigitalId
     */
    public CompanyStaffDigitalIdBasicDto findByBasicInfo(long staffId, int season) {
        Optional<JpaCompanyStaffDigitalIdBasic> digitalId = companyStaffDigitalIdBasicRepository.findByBasicInfo(staffId,season,EDigitalIdStatus.VALID.name()) ;
        if (digitalId != null && digitalId.isPresent()) {
            return getMapper().fromEntity(digitalId.get(), mappingContext);
        }
        return null;

    }
}
