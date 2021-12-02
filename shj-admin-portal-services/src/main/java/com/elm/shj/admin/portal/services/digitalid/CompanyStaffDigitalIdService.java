/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.digitalid;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalId;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffDigitalIdRepository;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDigitalIdDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling company staff digital id
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffDigitalIdService extends GenericService<JpaCompanyStaffDigitalId, CompanyStaffDigitalIdDto, Long> {
    private final CompanyStaffDigitalIdRepository companyStaffDigitalIdRepository;

    /**
     * @param id
     * @param seasonYear
     * @return list of companyStaffDigitalId
     */
    public List<CompanyStaffDigitalIdDto> findByDigitalIdByBasicInfo(long id, long seasonYear) {
        return mapList(companyStaffDigitalIdRepository.findByBasicInfo(id, seasonYear));
    }

}
