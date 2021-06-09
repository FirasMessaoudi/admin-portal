/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaLanguageLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for JpaLanguageLookup.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
public interface LanguageLookupRepository extends JpaRepository<JpaLanguageLookup, Long> {

    boolean existsByCode(String languageCode);
}
