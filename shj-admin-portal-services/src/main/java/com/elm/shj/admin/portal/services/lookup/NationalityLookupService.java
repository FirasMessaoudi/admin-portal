/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaNationalityLookup;
import com.elm.shj.admin.portal.orm.repository.NationalityLookupRepository;
import com.elm.shj.admin.portal.services.dto.NationalityLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling nationality lookup
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NationalityLookupService extends GenericService<JpaNationalityLookup, NationalityLookupDto, Long> {

    private final NationalityLookupRepository nationalityLookupRepository;

    /**
     * Checks if a nationality exists by its code
     *
     * @param countryCode the code of the nationality to look for
     * @return if the nationality is found
     */
    public boolean existsByCode(String countryCode) {
        return ((NationalityLookupRepository) getRepository()).existsByCode(countryCode);
    }

    /**
     * finds if a nationality by its code
     *
     * @param countryCode the code of the nationality to look for
     * @return the found nationality
     */
    public NationalityLookupDto findByCode(String countryCode) {
        return getMapper().fromEntity(nationalityLookupRepository.findFirstByCode(countryCode), mappingContext);
    }

    /**
     * Find nationality with all its labels based on the code.
     *
     * @param countryCode
     * @return
     */
    public List<NationalityLookupDto> findAllByCode(String countryCode) {
        return getMapper().fromEntityList(nationalityLookupRepository.findAllByCode(countryCode), mappingContext);
    }
}
