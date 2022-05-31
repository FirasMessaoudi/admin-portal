/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaHousingMaster;
import com.elm.shj.admin.portal.orm.repository.HousingMasterRepository;
import com.elm.shj.admin.portal.services.dto.HousingMasterDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling housing master
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HousingMasterService extends GenericService<JpaHousingMaster, HousingMasterDto, Long> {

    private final HousingMasterRepository housingMasterRepository;

    public boolean existsByHousingReferenceCode(String code) {
        return housingMasterRepository.existsByHousingReferenceCode(code);
    }
}
