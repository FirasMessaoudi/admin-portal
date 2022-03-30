/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.prinitng.PrintRequestBatchService;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestLiteService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for exposing printing management for business operation portal.
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
@RequestMapping(Navigation.API_PRINTING_MANAGEMENT_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PrintingManagementWsController {

    private final PrintRequestLiteService printRequestLiteService;
    private final PrintRequestBatchService printRequestBatchService;

    @GetMapping("/find/print-request")
    public ResponseEntity<WsResponse<?>> findPrintRequest() {
        log.debug("List print requests based on search criteria...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(printRequestLiteService.findPrintRequest()).build());
    }

    @PutMapping("/update-print-request-status/{printRequestId}")
    public ResponseEntity<WsResponse<?>> updatePrintRequestStatus(@PathVariable long printRequestId) {
        printRequestLiteService.updatePrintRequestStatus(printRequestId);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
    }

    @PutMapping("/update-batch-details/{printRequestReferenceNumber}/{batchSequenceNumber}")
    public ResponseEntity<WsResponse<?>> updateBatchDetails(@PathVariable String printRequestReferenceNumber, @PathVariable String batchSequenceNumber, @RequestBody Map<String, String> batchCards) {
        printRequestBatchService.updatePrintRequestBatchCards(printRequestReferenceNumber, Integer.parseInt(batchSequenceNumber), batchCards);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
    }


}
