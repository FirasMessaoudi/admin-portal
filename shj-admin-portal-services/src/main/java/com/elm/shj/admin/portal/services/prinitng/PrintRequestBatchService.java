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

    public List<PrintRequestBatchDto> findPrintRequestBatches(long printRequestId){
        return mapList(printRequestBatchRepository.findPrintRequestBatches(printRequestId));
    }

    public List<PrintRequestBatchDto> findStaffPrintRequestBatches(long printRequestId){
        return mapList(printRequestBatchRepository.findStaffPrintRequestBatches(printRequestId));
    }

    public void updatePrintRequestBatchCards(String printRequestReferenceNumber, int batchSequenceNumber, Map<String,String> cardsReferenceNumberMap) {
        Optional<JpaPrintRequestBatch> batch = printRequestBatchRepository.findBySequenceNumberAndPrintRequestReferenceNumber(batchSequenceNumber,printRequestReferenceNumber);
        if (!batch.isPresent()) {
            throw new IllegalArgumentException("Print request Batch not found");
        }
        PrintRequestBatchDto printRequestBatchDto = getMapper().fromEntity(batch.get(), mappingContext);
        String target = printRequestBatchDto.getPrintRequest().getTarget();
        if (target.equalsIgnoreCase(EPrintingRequestTarget.APPLICANT.name())) {
            List<ApplicantCardDto> applicantCardList = applicantCardService.findApplicantCardsByPrintRequestBatchIdAndDigitalIds(batch.get().getId(), cardsReferenceNumberMap.keySet());
            applicantCardList.forEach(c->{
                c.setStatusCode(ECardStatus.PRINTED.name());
                c.setReferenceNumber(cardsReferenceNumberMap.get(c.getApplicantRitual().getApplicant().getDigitalIds().get(0).getUin()));
                c.setUpdateDate(new Date());
            });
            applicantCardService.saveAll(applicantCardList);
        }
        if (target.equalsIgnoreCase(EPrintingRequestTarget.STAFF.name())) {
            List<CompanyStaffCardDto> staffCardList = companyStaffCardService.findStaffCardsByPrintRequestBatchIdAndDigitalIds(batch.get().getId(), cardsReferenceNumberMap.keySet());
            staffCardList.forEach(c->{
                c.setStatusCode(ECardStatus.PRINTED.name());
                c.setReferenceNumber(cardsReferenceNumberMap.get(c.getCompanyStaffDigitalId().getSuin()));
                c.setUpdateDate(new Date());
            });
            companyStaffCardService.saveAll(staffCardList);
        }
    }
}
