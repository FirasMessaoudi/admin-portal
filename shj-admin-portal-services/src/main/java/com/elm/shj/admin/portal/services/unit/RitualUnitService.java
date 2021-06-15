/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.unit;

import com.elm.shj.admin.portal.orm.entity.JpaRitualUnit;
import com.elm.shj.admin.portal.orm.repository.RitualUnitRepository;
import com.elm.shj.admin.portal.services.dto.RitualUnitDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling ritual unit
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
public class RitualUnitService extends GenericService<JpaRitualUnit, RitualUnitDto, Long> {

    /**
     * Checks if a ritual unit exists by its code
     *
     * @param unitCode the code of the unit to look for
     * @return if the unit is found
     */
    public boolean existsByCode(String unitCode) {
        return ((RitualUnitRepository) getRepository()).existsByCode(unitCode);
    }
}
