/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaReadinessSurveyQuestionCategoryLookup;
import com.elm.shj.admin.portal.orm.entity.JpaReadinessSurveyQuestionLookup;
import com.elm.shj.admin.portal.orm.repository.SurveyQuestionLookupRepository;
import com.elm.shj.admin.portal.services.dto.ReadinessSurveyQuestionCategoryLookupDto;
import com.elm.shj.admin.portal.services.dto.ReadinessSurveyQuestionLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling Readiness Survey Question Category Lookup
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReadinessSurveyQuestionCategoryLookupService extends GenericService<JpaReadinessSurveyQuestionCategoryLookup, ReadinessSurveyQuestionCategoryLookupDto, Long> {


}
