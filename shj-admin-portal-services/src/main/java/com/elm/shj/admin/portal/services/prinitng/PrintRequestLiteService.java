/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.prinitng;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestLite;
import com.elm.shj.admin.portal.orm.entity.PrintRequestFilterVo;
import com.elm.shj.admin.portal.orm.repository.PrintRequestBatchRepository;
import com.elm.shj.admin.portal.orm.repository.PrintRequestCardRepository;
import com.elm.shj.admin.portal.orm.repository.PrintRequestLiteRepository;
import com.elm.shj.admin.portal.services.dto.EPrintingRequestType;
import com.elm.shj.admin.portal.services.dto.PrintRequestLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service handling print request lite
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrintRequestLiteService extends GenericService<JpaPrintRequestLite, PrintRequestLiteDto, Long> {

    private final PrintRequestLiteRepository printRequestLiteRepository;
    private final PrintRequestCardRepository printRequestCardRepository;
    private final PrintRequestBatchRepository printRequestBatchRepository;

    /**
     * Find the lite version of all print requests.
     *
     * @param pageable the current page information
     * @return the list of print requests
     */
    public Page<PrintRequestLiteDto> findAll(Pageable pageable) {
        Page<PrintRequestLiteDto> litePrintRequests = mapPage(printRequestLiteRepository.findAll(withApplicantRequestType(), pageable));
        litePrintRequests.forEach(p -> {
            p.setCardsCount(printRequestCardRepository.countAllByPrintRequestId(p.getId()));
            p.setBatchesCount(printRequestBatchRepository.countAllByPrintRequestId(p.getId()));
        });
        return litePrintRequests;
    }

    /**
     * Find paginated print requests based on filter.
     *
     * @param pageable requested page of result.
     * @param filterVo filter value object
     * @return
     */
    public Page<PrintRequestLiteDto> findByFilter(PrintRequestFilterVo filterVo, Pageable pageable) {
        // at the time being, filter has only status code and description.
        Page<PrintRequestLiteDto> litePrintRequests =
                mapPage(printRequestLiteRepository.findAll(withPrintRequestFilter(filterVo.getStatusCode(), filterVo.getDescription()), pageable));
        litePrintRequests.forEach(p -> {
            p.setCardsCount(printRequestCardRepository.countAllByPrintRequestId(p.getId()));
            p.setBatchesCount(printRequestBatchRepository.countAllByPrintRequestId(p.getId()));
        });
        return litePrintRequests;
    }

    private Specification<JpaPrintRequestLite> withPrintRequestFilter(final String statusCode, final String description) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();

            if (statusCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("statusCode"), statusCode));
            }
            predicates.add(criteriaBuilder.equal(root.get("typeCode"), EPrintingRequestType.APPLICANT_REQUEST.name()));

            if (description != null && description.length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + description.trim() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<JpaPrintRequestLite> withApplicantRequestType() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("typeCode"), EPrintingRequestType.APPLICANT_REQUEST.name()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
