/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.prinitng;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.*;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service handling print request lite
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PrintRequestLiteService extends GenericService<JpaPrintRequest, PrintRequestDto, Long> {

    private final PrintRequestLiteRepository printRequestLiteRepository;
    private final PrintRequestRepository printRequestRepository;
    private final PrintRequestCardRepository printRequestCardRepository;
    private final PrintRequestBatchRepository printRequestBatchRepository;
    private final ApplicantCardRepository applicantCardRepository;
    private final CompanyStaffCardRepository companyStaffCardRepository;

    /**
     * Find the lite version of all print requests.
     *
     * @param target the target print request type
     * @return the list of print requests
     */
    public Page<PrintRequestDto> findAll(String target, Pageable pageable) {
        Page<PrintRequestDto> litePrintRequests = mapPage(printRequestRepository.findByTarget(target, pageable));
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
     * @param criteria filter value object
     * @return
     */
    public Page<PrintRequestDto> findByFilter(PrintRequestCriteriaDto criteria, String target, Pageable pageable) {
        Page<PrintRequestDto> litePrintRequests = null;
        Date startDate = new GregorianCalendar(1950, 01, 01).getTime();
        Date endDate = new GregorianCalendar(5000, 01, 01).getTime();
        ;
        if (target.equalsIgnoreCase(EPrintingRequestTarget.APPLICANT.name())) {
            litePrintRequests = mapPage(printRequestRepository.findAll(withApplicantPrintRequestFilter(criteria), pageable));

        } else {
            litePrintRequests = findStaffPrintRequests(criteria, criteria.getFromDate(), criteria.getToDate(), pageable);
            litePrintRequests.forEach(p -> {
                Optional<JpaCompanyStaffCard> companyStaffCard = companyStaffCardRepository.findById(p.getPrintRequestCards().stream().iterator().next().getCardId());
                if(companyStaffCard.isPresent()){
                    p.setSeasonYear(companyStaffCard.get().getCompanyRitualSeason().getRitualSeason().getSeasonYear());
                    p.setRitualTypeCode(companyStaffCard.get().getCompanyRitualSeason().getRitualSeason().getRitualTypeCode());
                }
            });
        }
        litePrintRequests.forEach(p -> {
            p.setCardsCount(printRequestCardRepository.countAllByPrintRequestId(p.getId()));
            p.setBatchesCount(printRequestBatchRepository.countAllByPrintRequestId(p.getId()));
        });
        return litePrintRequests;
    }

    private Page<PrintRequestDto> findStaffPrintRequests(PrintRequestCriteriaDto criteria, Date startDate, Date endDate, Pageable pageable) {
        if (criteria.getFromDate() != null)
            startDate = atStartOfDay(criteria.getFromDate());

        if (criteria.getToDate() != null)
            endDate = atEndOfDay(criteria.getToDate());

        return mapPage(printRequestRepository.findStaffPrintRequestByFilters(criteria.getRitualTypeCode(), criteria.getCompanyCode(), criteria.getSeason(),criteria.getStatusCode(), criteria.getDescription(),
                criteria.getRequestNumber(), criteria.getBatchNumber(), criteria.getCardNumber(), criteria.getIdNumber()
                , startDate, endDate, pageable));
    }

    private Page<PrintRequestDto> findApplicantPrintRequests(PrintRequestCriteriaDto criteria, Date startDate, Date endDate, Pageable pageable) {
        if (criteria.getFromDate() != null)
            startDate = atStartOfDay(criteria.getFromDate());

        if (criteria.getToDate() != null)
            endDate = atEndOfDay(criteria.getToDate());

        return mapPage(printRequestRepository.findByFilters(criteria.getStatusCode(), criteria.getDescription(),
                criteria.getRequestNumber(), criteria.getBatchNumber(), criteria.getCardNumber(), criteria.getIdNumber()
                , startDate, endDate, pageable));
    }

    private Specification<JpaPrintRequest> withApplicantPrintRequestFilter(PrintRequestCriteriaDto criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getStatusCode() != null) {
                predicates.add(criteriaBuilder.equal(root.get("statusCode"), criteria.getStatusCode()));
            }
            predicates.add(criteriaBuilder.equal(root.get("target"), EPrintingRequestTarget.APPLICANT.name()));

            if (criteria.getDescription() != null && criteria.getDescription().length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + criteria.getDescription().trim() + "%"));
            }
            if (criteria.getRequestNumber() != null && criteria.getRequestNumber().trim().length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("referenceNumber"), "%" + criteria.getRequestNumber().trim() + "%"));
            }
            if (criteria.getBatchNumber() > 0) {
                predicates.add(criteriaBuilder.equal(root.join("printRequestBatches").get("sequenceNumber"), criteria.getBatchNumber()));
            }
            if (criteria.getFromDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), atStartOfDay(criteria.getFromDate())));
            }
            if (criteria.getToDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), atEndOfDay(criteria.getToDate())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<JpaPrintRequest> withStaffPrintRequestFilter(final String statusCode, final String description) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();

            if (statusCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("statusCode"), statusCode));
            }
            //predicates.add(criteriaBuilder.equal(root.get("target"), EPrintingRequestTarget.STAFF.name()));

            if (description != null && description.length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + description.trim() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    private Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public List<PrintRequestDto> findPrintRequest(){
        List<PrintRequestDto> litePrintRequests= mapList(printRequestRepository.findPrintRequest(PageRequest.of(0, 1, Sort.Direction.ASC, "creationDate")).getContent());
        return litePrintRequests;
    }

}
