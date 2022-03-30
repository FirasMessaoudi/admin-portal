/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaSurveyQuestionLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Survey Question Lookup table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
public interface SurveyQuestionLookupRepository extends JpaRepository<JpaSurveyQuestionLookup, Long> {

    List<JpaSurveyQuestionLookup> findAllBySurveyTypeCodeOrderByQuestionIndexAsc( String surveyTypeCode);

}