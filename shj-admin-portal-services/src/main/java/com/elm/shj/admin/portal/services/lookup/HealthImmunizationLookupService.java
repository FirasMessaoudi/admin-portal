/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaHealthImmunizationLookup;
import com.elm.shj.admin.portal.orm.repository.HealthImmunizationLookupRepository;
import com.elm.shj.admin.portal.services.dto.HealthImmunizationLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling health immunization lookup
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Service
@Slf4j
public class HealthImmunizationLookupService extends GenericService<JpaHealthImmunizationLookup, HealthImmunizationLookupDto, Long> {

    /**
     * Checks if an immunization exists by its code
     *
     * @param immunizationCode the code of the immunization to look for
     * @return if the immunization is found
     */
    public boolean existsByCode(String immunizationCode) {
        return ((HealthImmunizationLookupRepository) getRepository()).existsByCode(immunizationCode);
    }
}
