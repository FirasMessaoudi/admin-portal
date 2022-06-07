/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaMaritalStatusLookup;
import com.elm.shj.admin.portal.orm.repository.MaritalStatusLookupRepository;
import com.elm.shj.admin.portal.services.dto.MaritalStatusLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling marital status lookup
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
public class MaritalStatusLookupService extends GenericService<JpaMaritalStatusLookup, MaritalStatusLookupDto, Long> {

    /**
     * checks if a marital status exists by its code
     *
     * @param maritalStatusCode the code of the marital status to look for
     * @return if the marital status is found
     */
    public boolean existsByCode(String maritalStatusCode) {
        return ((MaritalStatusLookupRepository) getRepository()).existsByCode(maritalStatusCode);
    }
}
