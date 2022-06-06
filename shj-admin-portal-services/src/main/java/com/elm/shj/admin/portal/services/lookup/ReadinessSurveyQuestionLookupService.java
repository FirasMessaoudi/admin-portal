/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaReadinessSurveyQuestionLookup;
import com.elm.shj.admin.portal.orm.entity.JpaSurveyQuestionLookup;
import com.elm.shj.admin.portal.orm.repository.ReadinessSiteCampQuestionRepository;
import com.elm.shj.admin.portal.orm.repository.ReadinessSurveyQuestionLookupRepository;
import com.elm.shj.admin.portal.orm.repository.SurveyQuestionLookupRepository;
import com.elm.shj.admin.portal.services.dto.ReadinessSiteCampQuestionDto;
import com.elm.shj.admin.portal.services.dto.ReadinessSurveyQuestionLookupDto;
import com.elm.shj.admin.portal.services.dto.SurveyQuestionLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling Readiness Survey Question Lookup
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReadinessSurveyQuestionLookupService extends GenericService<JpaReadinessSurveyQuestionLookup, ReadinessSurveyQuestionLookupDto, Long> {


    private final ReadinessSurveyQuestionLookupRepository readinessSurveyQuestionLookupRepository;



}
