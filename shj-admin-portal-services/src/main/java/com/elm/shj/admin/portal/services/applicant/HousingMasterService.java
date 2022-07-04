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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        log.info("Start existsByHousingReferenceCode code: {}", code);
        boolean isHousingMasterExists = housingMasterRepository.existsByHousingReferenceCode(code);
        log.info("Finish existsByHousingReferenceCode code: {}", code);
        return isHousingMasterExists;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public HousingMasterDto findLatestHousingMasterByReferenceCode(String refNumber) {
        log.info("Start findLatestHousingMasterByReferenceCode with {} reference number.", refNumber);
        Optional<JpaHousingMaster> housingMaster = housingMasterRepository.findTopByHousingReferenceCodeOrderByCreationDateDesc(refNumber);
        if (housingMaster.isPresent()) {
            log.info("Finish findLatestHousingMasterByReferenceCode and found {} housing master id", housingMaster.get().getId());
            return getMapper().fromEntity(housingMaster.get(), mappingContext);
        }
        return null;
    }
}
