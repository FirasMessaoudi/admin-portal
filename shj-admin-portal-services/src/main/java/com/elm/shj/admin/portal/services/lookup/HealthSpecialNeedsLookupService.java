/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaHealthSpecialNeedsTypeLookup;
import com.elm.shj.admin.portal.orm.repository.HealthSpecialNeedsTypeLookupRepository;
import com.elm.shj.admin.portal.services.dto.HealthSpecialNeedsTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling health special needs lookup
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
public class HealthSpecialNeedsLookupService extends GenericService<JpaHealthSpecialNeedsTypeLookup, HealthSpecialNeedsTypeLookupDto, Long> {

    /**
     * Checks if a special needs exists by its code
     *
     * @param specialNeedsCode the code of the special needs to look for
     * @return if the special needs is found
     */
    public boolean existsByCode(String specialNeedsCode) {
        return ((HealthSpecialNeedsTypeLookupRepository) getRepository()).existsByCode(specialNeedsCode);
    }
}
