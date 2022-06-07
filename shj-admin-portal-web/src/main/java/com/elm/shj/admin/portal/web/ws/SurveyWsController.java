/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.orm.entity.ReadinessSiteCampQuestionVO;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.*;
import com.elm.shj.admin.portal.services.survey.InspectorReadinessSurveyService;
import com.elm.shj.admin.portal.services.survey.ReadinessSiteCampQuestionService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for exposing survey web services for external party.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)
@Slf4j
@RestController
@RequestMapping(Navigation.API_SURVEY_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SurveyWsController {

    private final UserSurveyService userSurveyService;
    private final UserSurveyQuestionService userSurveyQuestionService;
    private final SurveyQuestionLookupService surveyQuestionLookupService;
    private final ReadinessSiteCampQuestionService readinessSiteCampQuestionService;
    private final CampSiteLookupService campSiteLookupService;
    private final CampCategoryLookupService campCategoryLookupService;
    private final InspectorReadinessSurveyService inspectorReadinessSurveyService;


    /**
     * finds survey by uin and survey type
     *
     * @param digitalId  the UIN of the applicant
     * @param surveyType the selected ritual ID
     * @return the survey question if it is not already submitted
     */
    @GetMapping("/find-applicant-survey/{digitalId}/{surveyType}")
    public ResponseEntity<WsResponse<?>> findSurveyByApplicantDigitalIdAndSurveyType(@PathVariable("digitalId") String digitalId, @PathVariable("surveyType") String surveyType) {
        log.debug("List survey questions by digital Id {} and survey type {}", digitalId, surveyType);
        Optional<UserSurveyDto> userSurvey = userSurveyService.findSurveyByDigitalIdAndSurveyType(digitalId, surveyType);
        if (userSurvey.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.SURVEY_ALREADY_SUBMITTED.getCode())
                            .referenceNumber(digitalId).build()).build());
        } else if (surveyType.equals("END_OF_RITUAL")) {
            if (!userSurveyService.checkApplicantEndOfRitualDateValid(digitalId))
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.SURVEY_NOT_AVAILABLE.getCode())
                                .referenceNumber(digitalId).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(surveyQuestionLookupService.getSurveyQuestionsBySurveyType(surveyType)).build());
    }

    @GetMapping("/find-group-leader-survey/{digitalId}/{surveyType}")
    public ResponseEntity<WsResponse<?>> findSurveyByGroupLeaderDigitalIdAndSurveyType(@PathVariable("digitalId") String digitalId, @PathVariable("surveyType") String surveyType) {
        log.debug("List survey questions for group leader  by digital Id {} and survey type {}", digitalId, surveyType);
        Optional<UserSurveyDto> userSurvey = userSurveyService.findSurveyByDigitalIdAndSurveyType(digitalId, surveyType);
        if (userSurvey.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.SURVEY_ALREADY_SUBMITTED.getCode())
                            .referenceNumber(digitalId).build()).build());
        } else if (surveyType.equals("END_OF_RITUAL")) {
            if (!userSurveyService.checkGroupLeaderEndOfRitualDateValid(digitalId))
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                        .body(WsError.builder().error(WsError.EWsError.SURVEY_NOT_AVAILABLE.getCode())
                                .referenceNumber(digitalId).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(surveyQuestionLookupService.getSurveyQuestionsBySurveyType(surveyType)).build());
    }

    @PostMapping(value = "/submit-survey")
    public ResponseEntity<WsResponse<?>> SubmitUserSurvey(@RequestBody SurveyFormDto surveyFormDto) {
        UserSurveyDto userSurveyDto = surveyFormDto.getUserSurvey();
        List<UserSurveyQuestionDto> userSurveyQuestionDtoList = surveyFormDto.getUserSurveyQuestions();
        if (surveyFormDto.getUserSurvey().getDigitalId() == null || surveyFormDto.getUserSurvey().getDigitalId().equals("")) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.USER_NOT_FOUND.getCode()).referenceNumber(surveyFormDto.getUserSurvey().getDigitalId()).build()).build());
        }
        if (null == surveyFormDto.getUserSurvey().getSurveyType() || !surveyFormDto.getUserSurvey().getSurveyType().equals("DAILY") && !surveyFormDto.getUserSurvey().getSurveyType().equals("END_OF_RITUAL")) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_SURVEY_TYPE.getCode()).referenceNumber(surveyFormDto.getUserSurvey().getDigitalId()).build()).build());
        }
        Optional<UserSurveyDto> userSurvey = userSurveyService.findSurveyByDigitalIdAndSurveyType(surveyFormDto.getUserSurvey().getDigitalId(), surveyFormDto.getUserSurvey().getSurveyType());
        if (userSurvey.isPresent()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.SURVEY_ALREADY_SUBMITTED.getCode())
                            .referenceNumber(userSurveyDto.getDigitalId()).build()).build());
        }

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(userSurveyService.submitSurvey(userSurveyDto, userSurveyQuestionDtoList)).build());

    }

    @GetMapping("/findRating/{userSurveyId}")
    public ResponseEntity<WsResponse<?>> findQuestionRatingByUserSurveyId(@PathVariable("userSurveyId") long userSurveyId) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(userSurveyQuestionService.findQuestionRating(userSurveyId)).build());
    }

    @GetMapping("/camp-readiness-survey/questions/list/{campSiteCode}/{campCategoryCode}/{lang}")
    public ResponseEntity<WsResponse<?>> findReadinessSurveyQuestions(@PathVariable("campSiteCode") String campSiteCode, @PathVariable("campCategoryCode") String campCategoryCode, @PathVariable("lang") String lang) {
        if (campCategoryCode == null || campSiteCode == null || campSiteCode.trim().isEmpty() || campCategoryCode.trim().isEmpty()) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.INVALID_INPUT.getCode())
                            .build()).build());
        }
        List<ReadinessSiteCampQuestionVO> allBySiteCodeAndCampCategoryCode = readinessSiteCampQuestionService.findAllBySiteCodeAndCampCategoryCode(campSiteCode, campCategoryCode, lang);

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(allBySiteCodeAndCampCategoryCode).build());
    }

    @GetMapping("/camp-readiness-survey/camp-site/list")
    public ResponseEntity<WsResponse<?>> findReadinessSurveyCampSite() {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(campSiteLookupService.findAll()).build());
    }

    @GetMapping("/camp-readiness-survey/camp-category/list")
    public ResponseEntity<WsResponse<?>> findReadinessSurveyCampCategory() {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(campCategoryLookupService.findAll()).build());
    }

    @PostMapping(value = "/camp-readiness-survey/submit")
    public ResponseEntity<WsResponse<?>> createReadinessSurvey(@RequestBody ReadinessSurveyFormDto readinessSurveyFormDto) {

        try {
            InspectorReadinessSurveyDto inspectorReadinessSurveyDto = inspectorReadinessSurveyService.submitReadinessSurvey(readinessSurveyFormDto);
            return ResponseEntity.ok(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                            .body(inspectorReadinessSurveyDto).build());
        } catch (RuntimeException e) {
            log.error("createReadinessSurvey throws Exception {}", e.getMessage());
            return ResponseEntity.ok(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                            .body(e.getMessage()).build());

        }
    }


}
