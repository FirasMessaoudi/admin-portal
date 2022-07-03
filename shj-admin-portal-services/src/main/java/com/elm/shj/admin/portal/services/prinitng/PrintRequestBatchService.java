/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.prinitng;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestBatch;
import com.elm.shj.admin.portal.orm.repository.PrintRequestBatchRepository;
import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service handling print request
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrintRequestBatchService extends GenericService<JpaPrintRequestBatch, PrintRequestBatchDto, Long> {
    private final PrintRequestBatchRepository printRequestBatchRepository;
    private final ApplicantCardService applicantCardService;
    private final CompanyStaffCardService companyStaffCardService;
    private final PrintRequestService printRequestService;

    @Value("${activate.printed.card}")
    private boolean activatePrintedCard;


    public List<PrintRequestBatchDto> findPrintRequestBatches(long printRequestId) {
        log.info("find print request batches for applicant with printRequestId : {}", printRequestId);
        return mapList(printRequestBatchRepository.findPrintRequestBatches(printRequestId));
    }

    public List<PrintRequestBatchDto> findStaffPrintRequestBatches(long printRequestId) {
        log.info("find print request batches for staff with printRequestId : {}", printRequestId);
        return mapList(printRequestBatchRepository.findStaffPrintRequestBatches(printRequestId));
    }

    public void updatePrintRequestBatchCards(String printRequestReferenceNumber, int batchSequenceNumber, Map<String, String> cardsReferenceNumberMap) throws PrintRequestBatchException {
        log.info("start updatePrintRequestBatchCards");
        Optional<JpaPrintRequestBatch> batch = printRequestBatchRepository.findBySequenceNumberAndPrintRequestReferenceNumber(batchSequenceNumber, printRequestReferenceNumber);
        if (!batch.isPresent()) {
            throw new PrintRequestBatchException("Print request Batch not found");
        }
        PrintRequestBatchDto printRequestBatchDto = getMapper().fromEntity(batch.get(), mappingContext);
        String target = printRequestBatchDto.getPrintRequest().getTarget();
        if (target.equalsIgnoreCase(EPrintingRequestTarget.APPLICANT.name())) {
            List<ApplicantCardDto> applicantCardList = applicantCardService.findApplicantCardsByPrintRequestBatchIdAndDigitalIds(batch.get().getId(), cardsReferenceNumberMap.keySet());
            if (applicantCardList.isEmpty()) {
                throw new PrintRequestBatchException("Applicant cards not found");
            }
            applicantCardList.forEach(c -> {
                String cardRefNumber = cardsReferenceNumberMap.get(c.getApplicantRitual().getApplicant().getDigitalIds().get(0).getUin());
                if(activatePrintedCard){
                    if (!c.getStatusCode().equalsIgnoreCase(ECardStatus.ACTIVE.name())
                            && (c.getReferenceNumber() == null
                            || !c.getReferenceNumber().equalsIgnoreCase(cardRefNumber))) {
                        c.setStatusCode(ECardStatus.ACTIVE.name());
                        c.setReferenceNumber(cardRefNumber);
                        c.setUpdateDate(new Date());
                    }
                } else {
                    if (c.getStatusCode().equalsIgnoreCase(ECardStatus.PRINTED.name()) == false
                            && (c.getReferenceNumber() == null
                            || c.getReferenceNumber().equalsIgnoreCase(cardRefNumber) == false)) {
                        c.setStatusCode(ECardStatus.PRINTED.name());
                        c.setReferenceNumber(cardRefNumber);
                        c.setUpdateDate(new Date());
                    }
                }
            });
            applicantCardService.saveAll(applicantCardList);

        }
        if (target.equalsIgnoreCase(EPrintingRequestTarget.STAFF.name())) {
            List<CompanyStaffCardDto> staffCardList = companyStaffCardService.findStaffCardsByPrintRequestBatchIdAndDigitalIds(batch.get().getId(), cardsReferenceNumberMap.keySet());
            if (staffCardList.isEmpty()) {
                throw new PrintRequestBatchException("Staff cards not found");
            }
            staffCardList.forEach(c -> {
                String cardRefNumber = cardsReferenceNumberMap.get(c.getCompanyStaffDigitalId().getSuin());
                if(activatePrintedCard){
                    if (!c.getStatusCode().equalsIgnoreCase(ECardStatus.ACTIVE.name())
                            && (c.getReferenceNumber() == null
                            || !c.getReferenceNumber().equalsIgnoreCase(cardRefNumber))) {
                        c.setStatusCode(ECardStatus.ACTIVE.name());
                        c.setReferenceNumber(cardRefNumber);
                        c.setUpdateDate(new Date());
                    }
                } else {
                    if (c.getStatusCode().equalsIgnoreCase(ECardStatus.PRINTED.name()) == false
                            && (c.getReferenceNumber() == null
                            || c.getReferenceNumber().equalsIgnoreCase(cardRefNumber) == false)) {
                        c.setUpdateDate(new Date());
                        c.setStatusCode(ECardStatus.PRINTED.name());
                        c.setReferenceNumber(cardRefNumber);
                    }
                }
            });
            log.info("end updatePrintRequestBatchCards");
            companyStaffCardService.saveAll(staffCardList);
        }
    }


}
