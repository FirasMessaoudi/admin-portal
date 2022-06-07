/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCampSiteLookup;
import com.elm.shj.admin.portal.orm.entity.JpaReadinessSurveyQuestionLookup;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository for Camp site Lookup table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface CampSiteLookupRepository extends JpaRepository<JpaCampSiteLookup, Long> {



}