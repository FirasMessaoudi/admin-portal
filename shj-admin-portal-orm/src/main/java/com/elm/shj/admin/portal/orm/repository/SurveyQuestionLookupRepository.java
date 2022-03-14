/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaSurveyQuestionLookup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Survey Question Lookup table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface SurveyQuestionLookupRepository extends JpaRepository<JpaSurveyQuestionLookup, Long> {

    List<JpaSurveyQuestionLookup> findAllBySurveyTypeCodeOrderByCodeAsc(String surveyTypeCode);
}
