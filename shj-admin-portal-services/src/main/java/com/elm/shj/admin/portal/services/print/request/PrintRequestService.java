/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.print.request;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequest;
import com.elm.shj.admin.portal.orm.repository.PrintRequestRepository;
import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;
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

    private final PrintRequestRepository printRequestRepository;
    private final ApplicantCardService cardService;
    private final PrintRequestCardService printRequestCardService;

    /**
     * Find all print requests.
     *
     * @param pageable the current page information
     * @return the list of print requests
     */
    public Page<PrintRequestDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    /**
     * Find all print requests except those with status NEW.
     *
     * @param pageable the current page information
     * @return the list of print requests
     */
    public Page<PrintRequestDto> findOtherThanNew(Pageable pageable) {
        return mapPage(printRequestRepository.findByStatusCodeNot(EPrintRequestStatus.NEW.name(), pageable));
    }


    @Transactional
    public PrintRequestDto save(List<Long> cardsIds) {
        // create and save the print request
        PrintRequestDto printRequest = PrintRequestDto.builder()
                .referenceNumber(generateReferenceNumber())
                .statusCode(EPrintRequestStatus.NEW.name())
                .build();
        // persist the request
        printRequest = super.save(printRequest);
        log.info("printRequest created successfully with #{}", printRequest.getId());
        List<PrintRequestCardDto> printRequestCards = new ArrayList<>();
        PrintRequestDto finalPrintRequest = printRequest;
        cardsIds.forEach(id -> {
            ApplicantCardDto card = cardService.findOne(id);
            printRequestCards.add(
                    PrintRequestCardDto.builder()
                            .card(card)
                            .printRequest(finalPrintRequest)
                            .build());
        });
        printRequestCardService.saveAll(printRequestCards);
        // return the persisted object
        return printRequest;
    }

    /**
     * Generates a unique identifier for the print request
     *
     * @return a unique identifier for the print request
     */
    public String generateReferenceNumber() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(t -> t >= '0' && t <= '9', t -> t >= 'A' && t <= 'Z')
                .build();
        return generator.generate(REQUEST_REF_NUMBER_LENGTH - 6) + REF_NUMBER_FORMAT.format(new Date());
    }

    public PrintRequestDto processBatching(long requestId, List<String> batchTypes) {
        // Retrieve the print request
        PrintRequestDto printRequest = findOne(requestId);

        //TODO TAKE INTO ACCOUNT SEARCH CRITERIA
        if (batchTypes.contains(EPrintBatchType.NATIONALITY.name())) {
            printRequest.getPrintRequestBatches().clear();

            Map<List<Object>, List<PrintRequestCardDto>> groupedRequestCards = printRequest.getPrintRequestCards()
                    .stream().collect(Collectors.groupingBy(requestCard -> Arrays.asList(requestCard.getCard().getApplicantRitual().getApplicant().getNationalityCode())));

            groupedRequestCards.forEach((key, value) -> {
                PrintRequestBatchDto printRequestBatch = PrintRequestBatchDto.builder()
                        .batchTypes(EPrintBatchType.NATIONALITY.name())
                        .printRequestBatchCards(value.stream().map(
                                requestCard -> PrintRequestBatchCardDto.builder().card(requestCard.getCard()).build()).collect(Collectors.toList()))
                        .build();
                printRequest.getPrintRequestBatches().add(printRequestBatch);
            });
        } else {
            PrintRequestBatchDto printRequestBatch = PrintRequestBatchDto.builder()
                    .printRequestBatchCards(printRequest.getPrintRequestCards().stream().map(requestCard -> PrintRequestBatchCardDto.builder().card(requestCard.getCard()).build()).collect(Collectors.toList()))
                    .build();
            printRequest.getPrintRequestBatches().add(printRequestBatch);
        }
        return printRequest;
    }

    public PrintRequestDto confirm(PrintRequestDto printRequest) {
        printRequest.getPrintRequestBatches().forEach(batch -> {
            batch.setPrintRequest(printRequest);
            batch.getPrintRequestBatchCards().forEach(batchCard -> {
                batchCard.setCard(batchCard.getCard());
                batchCard.setPrintRequestBatch(batch);
            });
        });
        printRequest.setStatusCode(EPrintRequestStatus.CONFIRMED.name());
        return super.save(printRequest);
    }
}

