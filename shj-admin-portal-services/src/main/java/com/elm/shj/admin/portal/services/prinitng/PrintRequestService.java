/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.prinitng;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequest;
import com.elm.shj.admin.portal.orm.repository.PrintRequestBatchRepository;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Service handling print request
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrintRequestService extends GenericService<JpaPrintRequest, PrintRequestDto, Long> {

    private static final SimpleDateFormat REF_NUMBER_FORMAT = new SimpleDateFormat("SSS");
    private static final int REQUEST_REF_NUMBER_LENGTH = 12;
    private final CompanyStaffCardService staffCardService;
    private final PrintRequestBatchRepository printRequestBatchRepository;

    public PrintRequestDto prepare(List<CardVO> cards) {
        // create and save the print request
        PrintRequestDto printRequest = new PrintRequestDto();
        printRequest.setReferenceNumber(generateReferenceNumber());
        printRequest.setStatusCode(EPrintRequestStatus.NEW.name());
        cards.forEach(card -> {
            PrintRequestCardDto printRequestCard = new PrintRequestCardDto();
            printRequestCard.setCard(card);
            printRequestCard.setCardId(card.getId());
            printRequestCard.setPrintRequest(printRequest);
            printRequest.getPrintRequestCards().add(printRequestCard);
        });
        // return the print request
        return printRequest;
    }
    /**
     * Generates a unique identifier for the print request
     *
     * @return a unique identifier for the print request
     */
    public String generateReferenceNumber() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .filteredBy(t -> t >= '0' && t <= '9')
                .build();
        return generator.generate(REQUEST_REF_NUMBER_LENGTH - 6) + REF_NUMBER_FORMAT.format(new Date());
    }

    public PrintRequestDto processBatching(PrintRequestDto printRequest, List<EPrintBatchType> selectedBatchTypes, String target) {

        printRequest.getPrintRequestBatches().clear();
        if (selectedBatchTypes.size() > 0) {
            // Group printing cards by selected batch types
            Map<List<String>, List<PrintRequestCardDto>> groupedRequestCards = printRequest.getPrintRequestCards()
                    .stream().collect(Collectors.groupingBy(requestCard -> {
                        List<String> groupingByCriteriaList = new ArrayList<>();
                        if (target.equalsIgnoreCase(EPrintingRequestTarget.APPLICANT.name())) {

                            selectedBatchTypes.forEach(type -> {
                                if (type.equals(EPrintBatchType.NATIONALITY)) {
                                    groupingByCriteriaList.add(requestCard.getCard().getNationalityCode());
                                }
                                //TODO ADD THE REMAINING CRITERIA
                            });
                        } else {
                            CompanyStaffCardDto card = staffCardService.findOne(requestCard.getCard().getId());
                            selectedBatchTypes.forEach(type -> {
                                if (type.equals(EPrintBatchType.STAFF_NATIONALITY)) {
                                    groupingByCriteriaList.add(String.valueOf(requestCard.getCard().getNationalityCode()));
                                }
                                if (type.equals(EPrintBatchType.COMPANY)) {
                                    groupingByCriteriaList.add(String.valueOf(card.getCompanyRitualSeason().getCompany().getCode()));
                                }
                                //TODO ADD THE REMAINING CRITERIA
                            });
                        }

                        return groupingByCriteriaList;
                    }));
            // Create print request batches
            groupedRequestCards.forEach((key, value) -> {
                PrintRequestBatchDto printRequestBatch = PrintRequestBatchDto.builder()
                        // Save batching criteria as comma-separated string
                        .batchTypeCodes(selectedBatchTypes.stream().map(EPrintBatchType::name).collect(Collectors.joining(",")))
                        // Save batching values as comma-separated string
                        .batchTypeValues(String.join(",", key))
                        .printRequestBatchCards(value.stream().map(requestCard -> PrintRequestBatchCardDto.builder().card(requestCard.getCard()).build()).collect(Collectors.toList())).build();
                printRequestBatch.setSequenceNumber(printRequestBatchRepository.maxSequenceNumber() + 1);
                printRequest.getPrintRequestBatches().add(printRequestBatch);
            });
        } else {
            // No batching criteria selected save all printing cards as one batch
            PrintRequestBatchDto printRequestBatch = PrintRequestBatchDto.builder().printRequestBatchCards(printRequest.getPrintRequestCards().stream().map(requestCard -> PrintRequestBatchCardDto.builder().card(requestCard.getCard()).build()).collect(Collectors.toList())).build();
            printRequest.getPrintRequestBatches().add(printRequestBatch);
        }
        // Return nested object
        return printRequest;
    }

    public PrintRequestDto confirm(PrintRequestDto printRequest, String target) {

        printRequest.getPrintRequestCards().forEach(requestCard -> {
            requestCard.setPrintRequest(printRequest);
            requestCard.setCardId(requestCard.getCard().getId());
        });
        printRequest.getPrintRequestBatches().forEach(requestBatch -> {
            requestBatch.setPrintRequest(printRequest);
            requestBatch.getPrintRequestBatchCards().forEach(batchCard -> {
                batchCard.setCardId(batchCard.getCard().getId());
                batchCard.setPrintRequestBatch(requestBatch);
            });
        });
        printRequest.setStatusCode(EPrintRequestStatus.CONFIRMED.name());
        printRequest.setTarget(target);
        printRequest.setConfirmationDate(new Date());
        return super.save(printRequest);
    }
}
