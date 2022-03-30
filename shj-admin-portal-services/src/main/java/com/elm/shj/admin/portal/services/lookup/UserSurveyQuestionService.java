/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurveyQuestion;
import com.elm.shj.admin.portal.orm.repository.UserSurveyQuestionRepository;
import com.elm.shj.admin.portal.services.dto.SurveyQuestionLookupDto;
import com.elm.shj.admin.portal.services.dto.UserSurveyDto;
import com.elm.shj.admin.portal.services.dto.UserSurveyQuestionDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling User Survey Question
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserSurveyQuestionService extends GenericService<JpaUserSurveyQuestion, UserSurveyQuestionDto, Long> {
    private final UserSurveyQuestionRepository userSurveyQuestionRepository ;
    public UserSurveyQuestionDto submitSurveyQuestion(UserSurveyDto userSurveyDto, UserSurveyQuestionDto userSurveyQuestionDto){
       UserSurveyQuestionDto surveyQuestionBuilder = UserSurveyQuestionDto.builder()
                .userSurvey(userSurveyDto)
                .surveyQuestion(userSurveyQuestionDto.getSurveyQuestion())
                .rate(userSurveyQuestionDto.getRate())
                .build();
       return save(surveyQuestionBuilder);
    }
    public List<Integer> findQuestionRating (long  userSurvey)
    {
        return userSurveyQuestionRepository.findRateByUserSurveyId(userSurvey);
    }

}
