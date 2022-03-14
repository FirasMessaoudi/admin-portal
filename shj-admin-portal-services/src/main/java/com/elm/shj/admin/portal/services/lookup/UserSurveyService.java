/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurvey;
import com.elm.shj.admin.portal.orm.repository.UserSurveyRepository;
import com.elm.shj.admin.portal.services.dto.UserSurveyDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Optional<UserSurveyDto> findSurveyByDigitalIdAndSurveyType(String digitalId, String surveyType){
        LocalDateTime today = LocalDateTime.now();
        if(today.getHour() <= 5){
            today = LocalDateTime.now().minusDays(1);
        }
        Optional<JpaUserSurvey> userSurvey = userSurveyRepository.findByDigitalIdAndSurveyTypeAndCreationDate(digitalId, surveyType, today);
        if(userSurvey.isPresent()){
            return Optional.of(getMapper().fromEntity(userSurvey.get(),mappingContext));
        }
        return Optional.empty();

    }
}
