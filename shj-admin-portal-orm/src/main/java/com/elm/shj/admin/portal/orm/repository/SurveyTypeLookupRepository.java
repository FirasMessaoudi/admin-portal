/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaSurveyTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Survey Type Lookup table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface SurveyTypeLookupRepository extends JpaRepository<JpaSurveyTypeLookup, Long> {

}
