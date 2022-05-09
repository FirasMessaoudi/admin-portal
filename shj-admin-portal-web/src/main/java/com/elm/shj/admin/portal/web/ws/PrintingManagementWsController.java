/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.EPrintRequestStatus;
import com.elm.shj.admin.portal.services.dto.PrintRequestDto;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestBatchException;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestBatchService;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestLiteService;
import com.elm.shj.admin.portal.services.prinitng.PrintRequestService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
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
    private final PrintRequestService printRequestService;

    @GetMapping("/find/print-request")
    public ResponseEntity<WsResponse<?>> findAllPrintRequest() {
        log.debug("List print requests based on search criteria...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(printRequestLiteService.findAllPrintRequest()).build());
    }

    @PutMapping("/update-print-request-status/{printRequestId}/{target}")
    public ResponseEntity<WsResponse<?>> updatePrintRequestStatusToSendToPrinting(@PathVariable long printRequestId, @PathVariable String target) {
        printRequestLiteService.updatePrintRequestStatusToSendToPrinting(printRequestId, target);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
    }

    @GetMapping("/find/print-request-batches/{refrenceNumber}/{target}")
    public ResponseEntity<WsResponse<?>> findPrintRequest(@PathVariable String refrenceNumber, @PathVariable String target) {
        log.debug("List print requests based on search criteria...");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(printRequestLiteService.findPrintRequestBatches(refrenceNumber, target)).build());
    }

    @PutMapping("/update-batch-details/{printRequestReferenceNumber}/{batchSequenceNumber}")
    public ResponseEntity<WsResponse<?>> updateBatchDetails(@PathVariable String printRequestReferenceNumber, @PathVariable String batchSequenceNumber, @RequestBody Map<String, String> batchCards) {
        try {
            printRequestBatchService.updatePrintRequestBatchCards(printRequestReferenceNumber, Integer.parseInt(batchSequenceNumber), batchCards);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
        } catch (PrintRequestBatchException e) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(e.getMessage()).build());
        }


    }

    @PutMapping("/update-print-request/status/{printRequestReferenceNumber}/{printRequestStatus}")
    public ResponseEntity<WsResponse<?>> updatePrintRequestStatus(@PathVariable String printRequestReferenceNumber, @PathVariable String printRequestStatus) {

        try {
            PrintRequestDto printRequestDto = printRequestService.findByReferenceNumber(printRequestReferenceNumber);
            if (printRequestDto == null) {
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body("Invalid Print Request").build());
            }
            boolean isNotFound = Arrays.stream(EPrintRequestStatus.values()).noneMatch(p -> p.name().equalsIgnoreCase(printRequestStatus));
            if (isNotFound)
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body("Invalid Status").build());
            printRequestService.updatePrintRequestStatus(printRequestDto.getId(), printRequestStatus);
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
        } catch (RuntimeException e) {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(e.getMessage()).build());
        }


    }


}
