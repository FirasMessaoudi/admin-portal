/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.print.request;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequest;
import com.elm.shj.admin.portal.orm.entity.PrintRequestFilterVo;
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

    /**
     * Find paginated print requests based on filter excluding incomplete requests (status: NEW).
     *
     * @param pageable requested page of result.
     * @param filterVo filter value object
     * @return
     */
    public Page<PrintRequestDto> findByFilter(PrintRequestFilterVo filterVo, Pageable pageable) {
        // at the time being, filter has only status code.
        return mapPage(printRequestRepository.findByStatusCodeAndStatusCodeNot(filterVo.getStatusCode(),
                EPrintRequestStatus.NEW.name(), pageable));
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
                .withinRange('0', '9')
                .filteredBy(t -> t >= '0' && t <= '9')
                .build();
        return generator.generate(REQUEST_REF_NUMBER_LENGTH - 6) + REF_NUMBER_FORMAT.format(new Date());
    }

    public PrintRequestDto processBatching(long requestId, List<EPrintBatchType> selectedBatchTypes) {
        // Retrieve the print request
        PrintRequestDto printRequest = findOne(requestId);

        if (selectedBatchTypes.size() > 0) {
            // Group printing cards by selected batch types
            Map<List<String>, List<PrintRequestCardDto>> groupedRequestCards = printRequest.getPrintRequestCards()
                    .stream().collect(Collectors.groupingBy(requestCard -> {
                        List<String> groupingByCriteriaList = new ArrayList<>();
                        selectedBatchTypes.forEach(type -> {
                            if (type.equals(EPrintBatchType.NATIONALITY)) {
                                groupingByCriteriaList.add(requestCard.getCard().getApplicantRitual().getApplicant().getNationalityCode());
                            }
                            //TODO ADD THE REMAINING CRITERIA
                        });
                        return groupingByCriteriaList;
                    }));

            // Create print request batches
            groupedRequestCards.forEach((key, value) -> {
                PrintRequestBatchDto printRequestBatch = PrintRequestBatchDto.builder()
                        // Save batching criteria as comma-separated string
                        .batchTypeCodes(selectedBatchTypes.stream().map(EPrintBatchType::name).collect(Collectors.joining(",")))
                        // Save batching values as comma-separated string
                        .batchTypeValues(String.join(",", key))
                        .printRequestBatchCards(value.stream().map(
                                requestCard -> PrintRequestBatchCardDto.builder().card(requestCard.getCard()).build()).collect(Collectors.toList()))
                        .build();
                printRequest.getPrintRequestBatches().add(printRequestBatch);
            });
        } else {
            // No batching criteria selected save all printing cards as one batch
            PrintRequestBatchDto printRequestBatch = PrintRequestBatchDto.builder()
                    .printRequestBatchCards(printRequest.getPrintRequestCards().stream().map(requestCard -> PrintRequestBatchCardDto.builder().card(requestCard.getCard()).build()).collect(Collectors.toList()))
                    .build();
            printRequest.getPrintRequestBatches().add(printRequestBatch);
        }
        // Return nested object
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
        printRequest.setConfirmationDate(new Date());
        return super.save(printRequest);
    }
}

