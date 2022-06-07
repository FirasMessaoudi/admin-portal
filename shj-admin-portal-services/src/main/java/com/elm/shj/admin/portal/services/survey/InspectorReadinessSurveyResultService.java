/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.survey;

import com.elm.shj.admin.portal.orm.entity.JpaInspectorReadinessSurvey;
import com.elm.shj.admin.portal.orm.entity.JpaInspectorReadinessSurveyResult;
import com.elm.shj.admin.portal.services.dto.InspectorReadinessSurveyDto;
import com.elm.shj.admin.portal.services.dto.InspectorReadinessSurveyResultDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling Inspector Readiness Survey Result
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class InspectorReadinessSurveyResultService extends GenericService<JpaInspectorReadinessSurveyResult, InspectorReadinessSurveyResultDto, Long> {




}
