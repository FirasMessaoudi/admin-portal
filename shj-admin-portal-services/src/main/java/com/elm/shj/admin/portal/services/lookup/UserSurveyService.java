/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaUserSurvey;
import com.elm.shj.admin.portal.orm.repository.UserSurveyRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantPackageService;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonService;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonDto;
import com.elm.shj.admin.portal.services.dto.UserSurveyDto;
import com.elm.shj.admin.portal.services.dto.UserSurveyQuestionDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final CompanyRitualSeasonService companyRitualSeasonService;
    private final ApplicantService applicantService;
    @Value("${daily.survey.activation.hour}")
    private int dailySurveyActivationHour;

    public Optional<UserSurveyDto> findSurveyByDigitalIdAndSurveyType(String digitalId, String surveyType) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getInstance().getTime();
        if (surveyType.equals("DAILY")) {
            if (LocalDateTime.now().isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.of(dailySurveyActivationHour,0)))) {
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
        Optional<ApplicantDto> applicant = applicantService.findByUin(digitalId);
        Long applicantId = -1L;
        if(applicant.isPresent()){
            applicantId = applicant.get().getId();
        } else {
            return false;
        }
        Date endRitualDate = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantId).getEndDate();
        Date maxDate = new Date(endRitualDate.getTime()+ 172800000);
        Date minDate = new Date(endRitualDate.getTime()- 86400000);
        if (currentDate.after(minDate) && currentDate.before(maxDate)) {
            return true;
        }
        else return false;
    }
    public boolean checkGroupLeaderEndOfRitualDateValid(String digitalId) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getInstance().getTime();
        Optional<CompanyRitualSeasonDto> companyRitualSeason = companyRitualSeasonService.findLatestCompanyRitualSeasonBySuin(digitalId);
        if (!companyRitualSeason.isPresent()) return false;
        Date endRitualDate = DateUtils.toGregorian(companyRitualSeason.get().getRitualSeason().getSeasonEnd());
        Date maxDate = new Date(endRitualDate.getTime()+ 172800000);
        Date minDate = new Date(endRitualDate.getTime()- 86400000);
        if (currentDate.after(minDate) && currentDate.before(maxDate)) {
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
