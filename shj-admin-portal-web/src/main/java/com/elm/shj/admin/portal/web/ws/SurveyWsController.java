/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.SurveyQuestionLookupService;
import com.elm.shj.admin.portal.services.lookup.SurveyTypeLookupService;
import com.elm.shj.admin.portal.services.lookup.UserSurveyQuestionService;
import com.elm.shj.admin.portal.services.lookup.UserSurveyService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    /**
     * finds survey by uin and survey type
     *
     * @param digitalId               the UIN of the applicant
     * @param surveyType the selected ritual ID
     * @return the survey question if it is not already submitted
     */
    @GetMapping("/find-survey/{digitalId}/{surveyType}")
    public ResponseEntity<WsResponse<?>> findSurveyByDigitalIdAndSurveyType(@PathVariable("digitalId") String digitalId,@PathVariable("surveyType") String surveyType) {
        log.debug("List survey questions by digital Id {} and survey type {}", digitalId, surveyType);
        Optional<UserSurveyDto> userSurvey = userSurveyService.findSurveyByDigitalIdAndSurveyType(digitalId, surveyType);
        if(userSurvey.isPresent()){
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.SURVEY_ALREADY_SUBMITTED.getCode())
                            .referenceNumber(digitalId).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(surveyQuestionLookupService.getSurveyQuestionsBySurveyType(surveyType)).build());
    }

    //TODO: Remove multipart form data value and RequestPart. Use @RequestBody create one DTO and inside that DTO use these two parameters,
    // If both parameters are required.
    @PostMapping(value ="/submit-survey",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WsResponse<?>> SubmitUserSurvey(@RequestPart("userSurvey") UserSurveyDto userSurveyDto, @RequestPart("userSurveyQuestions") List<UserSurveyQuestionDto> userSurveyQuestionDtoList){
        log.debug("submit user survey");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(userSurveyService.submitSurvey(userSurveyDto,userSurveyQuestionDtoList)).build());

    }
    @GetMapping("/findRating/{userSurveyId}")
    public ResponseEntity<WsResponse<?>> findQuestionRatingByUserSurveyId(@PathVariable("userSurveyId") long userSurveyId) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(userSurveyQuestionService.findQuestionRating(userSurveyId)).build());
    }

}
