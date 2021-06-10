/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaCountryLookup;
import com.elm.shj.admin.portal.orm.repository.CountryLookupRepository;
import com.elm.shj.admin.portal.services.dto.CountryLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling country lookup
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
public class CountryLookupService extends GenericService<JpaCountryLookup, CountryLookupDto, Long> {


    /**
     * Checks if a country exists by its code
     *
     * @param countryCode the code of the country to look for
     * @return if the country is found
     */
    public boolean existsByCode(String countryCode) {
        return ((CountryLookupRepository) getRepository()).existsByCode(countryCode);
    }

    /**
     * finds if a country by its code
     *
     * @param countryCode the code of the country to look for
     * @return the found country
     */
    public CountryLookupDto findByCode(String countryCode) {
        return getMapper().fromEntity(((CountryLookupRepository) getRepository()).findFirstByCode(countryCode), mappingContext);
    }
}
