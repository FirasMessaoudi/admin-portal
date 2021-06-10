/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaLanguageLookup;
import com.elm.shj.admin.portal.orm.repository.LanguageLookupRepository;
import com.elm.shj.admin.portal.services.dto.LanguageLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling language lookup
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
public class LanguageLookupService extends GenericService<JpaLanguageLookup, LanguageLookupDto, Long> {

    /**
     * Checks if a language exists by its code
     *
     * @param languageCode the code of the language to look for
     * @return if the language is found
     */
    public boolean existsByCode(String languageCode) {
        return ((LanguageLookupRepository) getRepository()).existsByCode(languageCode);
    }
}
