/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.survey;

import com.elm.shj.admin.portal.orm.entity.JpaInspectorReadinessSurvey;
import com.elm.shj.admin.portal.orm.entity.JpaUserSurvey;
import com.elm.shj.admin.portal.orm.repository.InspectorReadinessSurveyRepository;
import com.elm.shj.admin.portal.orm.repository.UserSurveyRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageService;
import com.elm.shj.admin.portal.services.applicant.RitualPackageService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.lookup.UserSurveyQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service handling Inspector Readiness Survey
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class InspectorReadinessSurveyService extends GenericService<JpaInspectorReadinessSurvey, InspectorReadinessSurveyDto, Long>{
    private final InspectorReadinessSurveyRepository inspectorReadinessSurveyRepository;
    private final  InspectorReadinessSurveyResultService inspectorReadinessSurveyResultService;
    @Transactional
    public InspectorReadinessSurveyDto submitReadinessSurvey(ReadinessSurveyFormDto readinessSurveyFormDto)  throws RuntimeException {
        InspectorReadinessSurveyDto survey = readinessSurveyFormDto.getSurvey();
        List<InspectorReadinessSurveyResultDto> surveyResult = readinessSurveyFormDto.getSurveyResult();
        InspectorReadinessSurveyDto savedSurvey = this.save(survey);
        surveyResult.forEach(e-> e.setInspectorReadinessSurveyId((int)savedSurvey.getId()));
        inspectorReadinessSurveyResultService.saveAll(surveyResult);

        return savedSurvey;
    }


}
