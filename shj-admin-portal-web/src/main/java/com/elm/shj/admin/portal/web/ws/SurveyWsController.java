/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.lookup.SurveyQuestionLookupService;
import com.elm.shj.admin.portal.services.lookup.UserSurveyService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final SurveyQuestionLookupService surveyQuestionLookupService;

    /**
     * finds chat contacts by uin and applicant ritual ID
     *
     * @param digitalId               the UIN of the applicant
     * @param surveyType the selected ritual ID
     * @return the survey question if it is not already submitted
     */
    @GetMapping("/get/{digitalId}/{surveyType}")
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



}
