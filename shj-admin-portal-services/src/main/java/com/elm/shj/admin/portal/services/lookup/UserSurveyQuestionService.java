/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurveyQuestion;
import com.elm.shj.admin.portal.services.dto.UserSurveyQuestionDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling User Survey Question
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
public class UserSurveyQuestionService extends GenericService<JpaUserSurveyQuestion, UserSurveyQuestionDto, Long> {
}
