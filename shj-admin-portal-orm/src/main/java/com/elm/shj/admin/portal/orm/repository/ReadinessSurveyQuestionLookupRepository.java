/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaReadinessSurveyQuestionLookup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Repository for Readiness Survey Question Lookup table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface ReadinessSurveyQuestionLookupRepository extends JpaRepository<JpaReadinessSurveyQuestionLookup, Long> {



}