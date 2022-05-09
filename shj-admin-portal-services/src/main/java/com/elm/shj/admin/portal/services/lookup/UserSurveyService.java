/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurvey;
import com.elm.shj.admin.portal.orm.repository.UserSurveyRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageService;
import com.elm.shj.admin.portal.services.applicant.RitualPackageService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
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
    private final ApplicantPackageService applicantPackageService;
    private final RitualPackageService ritualPackageService;
    @Value("${survey.activation.date}")
    private int surveyActivationDate;

    public Optional<UserSurveyDto> findSurveyByDigitalIdAndSurveyType(String digitalId, String surveyType) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getInstance().getTime();
        if (surveyType.equals("DAILY")) {
            if (cal.get(Calendar.HOUR_OF_DAY) <= surveyActivationDate) {
                cal.add(Calendar.DATE, -1);
                currentDate = cal.getTime();
            }
            Optional<JpaUserSurvey> userSurvey = userSurveyRepository.findByDigitalIdAndSurveyTypeAndSurveyDate(digitalId, surveyType, currentDate);
            if (userSurvey.isPresent()) {
                return Optional.of(getMapper().fromEntity(userSurvey.get(), mappingContext));
            }
        }
        else if (surveyType.equals("END_OF_RITUAL")){
            Optional<JpaUserSurvey> userSurvey = userSurveyRepository.findByDigitalIdAndSurveyType(digitalId, surveyType);
            if (userSurvey.isPresent()) {
                return Optional.of(getMapper().fromEntity(userSurvey.get(), mappingContext));
            }
        }
        return Optional.empty();
    }

    public boolean checkApplicantEndOfRitualDateValid(String digitalId) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getInstance().getTime();
        Date endRitualDate = applicantPackageService.findJpaApplicantPackageByApplicantUin(digitalId).getEndDate();
        if (currentDate.after(endRitualDate)) {
            return true;
        }
        else return false;
    }
    public boolean checkGroupLeaderEndOfRitualDateValid(String digitalId) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getInstance().getTime();
        Date endRitualDate = ritualPackageService.findByGroupLeaderDigitalId(digitalId).getEndDate();
        if (currentDate.after(endRitualDate)) {
            return true;
        }
        else return false;
    }

    @Transactional
    public UserSurveyDto submitSurvey(UserSurveyDto userSurveyDto, List<UserSurveyQuestionDto> userSurveyQuestionDtoList) {
        UserSurveyDto surveyBuilder = UserSurveyDto.builder()
                .digitalId(userSurveyDto.getDigitalId())
                .creationDate(userSurveyDto.getCreationDate())
                .surveyDate(userSurveyDto.getSurveyDate())
                .surveyType(userSurveyDto.getSurveyType())
                .build();
        UserSurveyDto submittedSurvey = save(surveyBuilder);
        if (submittedSurvey != null) {
            userSurveyQuestionDtoList.forEach((surveyQuestion) -> {
                userSurveyQuestionService.submitSurveyQuestion(submittedSurvey, surveyQuestion);
            });
        }
        return submittedSurvey;
    }
}
