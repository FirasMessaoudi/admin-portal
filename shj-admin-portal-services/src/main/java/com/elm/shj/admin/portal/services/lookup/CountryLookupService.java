/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaCountryLookup;
import com.elm.shj.admin.portal.orm.repository.CountryLookupRepository;
import com.elm.shj.admin.portal.services.dto.CountryLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling country lookup
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CountryLookupService extends GenericService<JpaCountryLookup, CountryLookupDto, Long> {

    private final CountryLookupRepository countryLookupRepository;

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
        return getMapper().fromEntity(countryLookupRepository.findFirstByCode(countryCode), mappingContext);
    }

    /**
     * Find country with all its labels based on the code.
     *
     * @param countryCode
     * @return
     */
    public List<CountryLookupDto> findAllByCode(String countryCode) {
        return getMapper().fromEntityList(countryLookupRepository.findAllByCode(countryCode), mappingContext);
    }
}
