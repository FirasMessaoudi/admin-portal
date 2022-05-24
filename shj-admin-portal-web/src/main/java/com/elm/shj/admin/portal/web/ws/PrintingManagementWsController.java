/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.EPrintRequestStatus;
import com.elm.shj.admin.portal.services.dto.PrintRequestBatchDto;
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
import java.util.List;
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
        log.info("Start findAllPrintRequest");
        List<PrintRequestDto> allPrintRequest = printRequestLiteService.findAllPrintRequest();
        log.info("Finish findAllPrintRequest {}, PrintRequestDtoListSize: {}", "SUCCESS", allPrintRequest == null? null: allPrintRequest.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(allPrintRequest).build());
    }

    @PutMapping("/update-print-request-status/{printRequestId}/{target}")
    public ResponseEntity<WsResponse<?>> updatePrintRequestStatusToSendToPrinting(@PathVariable long printRequestId, @PathVariable String target) {
        log.info("Start updatePrintRequestStatusToSendToPrinting  printRequestId {},  target {}" , printRequestId,  target);
        printRequestLiteService.updatePrintRequestStatusToSendToPrinting(printRequestId, target);
        log.info("Finish updatePrintRequestStatusToSendToPrinting {}, PrintRequestDtoListSize: {}", "SUCCESS");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
    }

    @GetMapping("/find/print-request-batches/{refrenceNumber}/{target}")
    public ResponseEntity<WsResponse<?>> findPrintRequestBatches(@PathVariable String refrenceNumber, @PathVariable String target) {
        log.info("Start findPrintRequestBatches  refrenceNumber {},  target {}" , refrenceNumber,  target);
        List<PrintRequestBatchDto> printRequestBatches = printRequestLiteService.findPrintRequestBatches(refrenceNumber, target);
        log.info("Finish findPrintRequestBatches {}, PrintRequestBatchDtoListSize: {}", "SUCCESS", printRequestBatches == null? null: printRequestBatches.size());
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(printRequestBatches).build());
    }

    @PutMapping("/update-batch-details/{printRequestReferenceNumber}/{batchSequenceNumber}")
    public ResponseEntity<WsResponse<?>> updateBatchDetails(@PathVariable String printRequestReferenceNumber, @PathVariable String batchSequenceNumber, @RequestBody Map<String, String> batchCards) {
        log.info("Start updateBatchDetails  printRequestReferenceNumber {},  batchSequenceNumber {}, batchCardsListsize {}" , printRequestReferenceNumber,  batchSequenceNumber, batchCards == null? null: batchCards.size());
        try {
            printRequestBatchService.updatePrintRequestBatchCards(printRequestReferenceNumber, Integer.parseInt(batchSequenceNumber), batchCards);
            log.info("Finish updateBatchDetails {}", "SUCCESS");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
        } catch (PrintRequestBatchException e) {
            log.info("Finish updateBatchDetails {}, {}", "FAILURE", e.getMessage());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(e.getMessage()).build());
        }


    }

    @PutMapping("/update-print-request/status/{printRequestReferenceNumber}/{printRequestStatus}")
    public ResponseEntity<WsResponse<?>> updatePrintRequestStatus(@PathVariable String printRequestReferenceNumber, @PathVariable String printRequestStatus) {
        log.info("Start updatePrintRequestStatus  printRequestReferenceNumber {},  printRequestStatus {}" , printRequestReferenceNumber,  printRequestStatus);
        try {
            PrintRequestDto printRequestDto = printRequestService.findByReferenceNumber(printRequestReferenceNumber);
            if (printRequestDto == null) {
                log.info("Finish updatePrintRequestStatus {}, {}", "FAILURE", "Invalid Print Request");
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body("Invalid Print Request").build());
            }
            boolean isNotFound = Arrays.stream(EPrintRequestStatus.values()).noneMatch(p -> p.name().equalsIgnoreCase(printRequestStatus));
            if (isNotFound) {
                log.info("Finish updatePrintRequestStatus {}, {}", "FAILURE", "Invalid Status");
                return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body("Invalid Status").build());
            }
            printRequestService.updatePrintRequestStatus(printRequestDto.getId(), printRequestStatus);
            log.info("Finish updatePrintRequestStatus {}", "SUCCESS");
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).build());
        } catch (RuntimeException e) {
            log.info("Finish updatePrintRequestStatus {}, {}", "FAILURE", e.getMessage());
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(e.getMessage()).build());
        }


    }


}
