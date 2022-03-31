/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.card.BatchMainCollectionService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for exposing batch main collection web services for external party.
 *
 * @author f.messaoudi
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
@RequestMapping(Navigation.API_BATCH)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BatchMainCollectionWsController {


    private final BatchMainCollectionService batchMainCollectionService;

    /**
     * generate batch cards
     *
     * @param batchCollection the batch main collection with the batch reference number
     * @return
     */
    @PostMapping("/generate")
    public ResponseEntity<WsResponse<?>> generateBatchCards(@RequestBody BatchCollectionVO batchCollection) {
        return null;
    }

}
