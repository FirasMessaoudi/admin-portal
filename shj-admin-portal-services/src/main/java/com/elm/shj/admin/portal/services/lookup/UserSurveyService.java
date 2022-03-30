/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurvey;
import com.elm.shj.admin.portal.orm.repository.UserSurveyRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service handling User Survey Question
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserSurveyService extends GenericService<JpaUserSurvey, UserSurveyDto, Long> {

    private final UserSurveyRepository userSurveyRepository;
    private final UserSurveyQuestionService userSurveyQuestionService;
    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    public Optional<UserSurveyDto> findSurveyByDigitalIdAndSurveyType(String digitalId, String surveyType){
        LocalDateTime today = LocalDateTime.now();
        System.out.println(today);
        if(today.getHour() <= 5){
            today = LocalDateTime.now().minusDays(1);
        }
        Optional<JpaUserSurvey> userSurvey = userSurveyRepository.findByDigitalIdAndSurveyTypeAndCreationDate(digitalId, surveyType, localDateTimeToDate(today));
        if(userSurvey.isPresent()){
            return Optional.of(getMapper().fromEntity(userSurvey.get(),mappingContext));
        }
        return Optional.empty();

    }
    @Transactional
    public UserSurveyDto submitSurvey(UserSurveyDto userSurveyDto, List<UserSurveyQuestionDto> userSurveyQuestionDtoList) {
        UserSurveyDto surveyBuilder = UserSurveyDto.builder()
                .digitalId(userSurveyDto.getDigitalId())
                .creationDate(userSurveyDto.getCreationDate())
                .surveyType(userSurveyDto.getSurveyType())
                .build();
        UserSurveyDto submittedSurvey = save(surveyBuilder);
        if(submittedSurvey != null){
            userSurveyQuestionDtoList.forEach((surveyQuestion) ->{
                userSurveyQuestionService.submitSurveyQuestion(submittedSurvey,surveyQuestion);
            });
        }
        return  submittedSurvey;
    }
}
