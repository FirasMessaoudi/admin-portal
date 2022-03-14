/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaSurveyQuestionLookup;
import com.elm.shj.admin.portal.orm.repository.SurveyQuestionLookupRepository;
import com.elm.shj.admin.portal.services.dto.SurveyQuestionLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling Survey Question Lookup
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SurveyQuestionLookupService extends GenericService<JpaSurveyQuestionLookup, SurveyQuestionLookupDto, Long> {

    private final SurveyQuestionLookupRepository surveyQuestionLookupRepository;

    public List<SurveyQuestionLookupDto> getSurveyQuestionsBySurveyType(String surveyType){
        List<JpaSurveyQuestionLookup> questionsList = surveyQuestionLookupRepository.findAllBySurveyTypeCodeOrderByCodeAsc(surveyType);
        List<SurveyQuestionLookupDto> surveyQuestionList = getMapper().fromEntityList(questionsList, mappingContext);
        return surveyQuestionList;
    }





}
