/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import com.elm.shj.admin.portal.orm.repository.NotificationTemplateRepository;
import com.elm.shj.admin.portal.services.dto.ENotificationTemplateType;
import com.elm.shj.admin.portal.services.dto.NotificationSearchCriteriaDto;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateCategorizingDto;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service handling Notification Template
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationTemplateService extends GenericService<JpaNotificationTemplate, NotificationTemplateDto, Long> {

    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationTemplateContentService notificationTemplateContentService;

    /**
     * Finds Notification Template By Name
     *
     * @param nameCode the nameCode of the notification template  to find
     * @return the found Notification Template or empty structure
     */
    public Optional<NotificationTemplateDto> findEnabledNotificationTemplateByNameCode(String nameCode) {
        log.info("start findEnabledNotificationTemplateByNameCode with nameCode: {}", nameCode);
        JpaNotificationTemplate jpaNotificationTemplate = notificationTemplateRepository.findByNameCodeAndEnabledTrue(nameCode);
        log.info("end findEnabledNotificationTemplateByNameCode with nameCode: {}", nameCode);
        return (jpaNotificationTemplate != null) ? Optional.of(getMapper().fromEntity(jpaNotificationTemplate, mappingContext)) : Optional.empty();
    }

    /**
     * update Notification Template
     *
     * @param notificationTemplate the notification Template to be updated
     * @return the updated Notification Template
     */
    public NotificationTemplateDto updateNotificationTemplate(NotificationTemplateDto notificationTemplate) {
        log.info("start updateNotificationTemplate");
        notificationTemplate.getNotificationTemplateContents().stream().forEach(content -> {
            content.setNotificationTemplate(notificationTemplate);
        });
        NotificationTemplateDto savedNotificationTemplate = findOne(notificationTemplate.getId());
        savedNotificationTemplate.setEnabled(notificationTemplate.isEnabled());
        savedNotificationTemplate.setNotificationTemplateContents(notificationTemplate.getNotificationTemplateContents());
        log.info("end updateNotificationTemplate");
        return save(savedNotificationTemplate);
    }


    /**
     * Find paginated notification template based on filter.
     *
     * @param pageable                   requested page of result.
     * @param notificationSearchCriteria filter value object
     * @return
     */
    public Page<NotificationTemplateDto> findByFilter(NotificationSearchCriteriaDto notificationSearchCriteria, String typeCode, Pageable pageable) {
        return mapPage(notificationTemplateRepository.findAll(withNotificationFilter(notificationSearchCriteria, typeCode), pageable));
    }

    private Specification<JpaNotificationTemplate> withNotificationFilter(final NotificationSearchCriteriaDto notificationSearchCriteria, String typeCode) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("typeCode"), typeCode));

            if (notificationSearchCriteria.getNotificationTitle() != null && notificationSearchCriteria.getNotificationTitle().trim().length() > 0) {
                Join<Object, Object> joinParent = root.join("notificationTemplateContents");
                Path<String> expression = joinParent.get("title");
                predicates.add(criteriaBuilder.like(expression, "%" + notificationSearchCriteria.getNotificationTitle().trim() + "%"));
            }

            if (notificationSearchCriteria.getNotificationBody() != null && notificationSearchCriteria.getNotificationBody().trim().length() > 0) {
                Join<Object, Object> joinParent = root.join("notificationTemplateContents");
                Path<String> expression = joinParent.get("body");
                predicates.add(criteriaBuilder.like(expression, "%" + notificationSearchCriteria.getNotificationBody().trim() + "%"));
            }

            if (notificationSearchCriteria.getNotificationName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nameCode"), notificationSearchCriteria.getNotificationName()));
            }

            if (notificationSearchCriteria.getDescription() != null && notificationSearchCriteria.getDescription().trim().length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + notificationSearchCriteria.getDescription().trim() + "%"));
            }

            if (notificationSearchCriteria.getNotificationCategory() != null) {
                predicates.add(criteriaBuilder.equal(root.get("categoryCode"), notificationSearchCriteria.getNotificationCategory()));
            }

            if (notificationSearchCriteria.getNotificationType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("actionRequired"), notificationSearchCriteria.getNotificationType()));
            }

            if (notificationSearchCriteria.getSeverity() != null) {
                predicates.add(criteriaBuilder.equal(root.get("important"), notificationSearchCriteria.getSeverity()));
            }

            if (notificationSearchCriteria.getCreationDateStart() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), notificationSearchCriteria.getCreationDateStart()));
            }

            if (notificationSearchCriteria.getCreationDateEnd() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), atEndOfDay(notificationSearchCriteria.getCreationDateEnd())));
            }

            if (notificationSearchCriteria.getSendingDateStart() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sendingDate"), notificationSearchCriteria.getSendingDateStart()));
            }

            if (notificationSearchCriteria.getSendingDateEnd() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sendingDate"), atEndOfDay(notificationSearchCriteria.getSendingDateEnd())));
            }

            if (notificationSearchCriteria.getCompanyCode() == null) {
                predicates.add(criteriaBuilder.isNull(root.get("companyCode")));
            }
            if (notificationSearchCriteria.getCompanyCode() != null) {
                predicates.add(criteriaBuilder.equal(root.get("companyCode"), notificationSearchCriteria.getCompanyCode()));
            }
            criteriaQuery.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
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

    @Transactional
    public NotificationTemplateDto create(NotificationTemplateDto notificationTemplate) {
        log.info("start create Notification");
        notificationTemplate.setTypeCode(ENotificationTemplateType.USER_DEFINED.name());
        notificationTemplate.setProcessed(false);
        notificationTemplate.getNotificationTemplateContents().stream().forEach(content -> content.setNotificationTemplate(notificationTemplate));
        if (notificationTemplate.getNotificationTemplateCategorizing() != null) {
            notificationTemplate.getNotificationTemplateCategorizing().setNotificationTemplate(notificationTemplate);
        }
        notificationTemplate.setNotificationTemplateContents(notificationTemplate.getNotificationTemplateContents());
        log.info("end create Notification");
        return save(notificationTemplate);
    }
    @Modifying
    @Transactional
    public List<NotificationTemplateDto> findUnprocessedUserDefinedNotifications(String typeCode, Date date, Boolean isProcessed, boolean enabled,int batchSize) {
        log.info("start findUnprocessedUserDefinedNotifications with typeCode: {} and isProcessed: {} and enabled: {} and batchSize: {}", typeCode, isProcessed, enabled, batchSize);
        List<JpaNotificationTemplate> pendingNotificationTemplate = notificationTemplateRepository
                .findByTypeCodeAndSendingDateBeforeAndProcessedAndEnabled(typeCode,date,isProcessed,enabled, PageRequest.of(0,batchSize)).getContent();
        log.info("end findUnprocessedUserDefinedNotifications");
        return mapList(pendingNotificationTemplate);
    }

    public NotificationTemplateDto updateUserDefined(NotificationTemplateDto notificationTemplate) {
        log.info("start updateUserDefined notification template");
        NotificationTemplateDto databaseNotificationTemplate = findOne(notificationTemplate.getId());

        // sets form fields to database notification template instance
        databaseNotificationTemplate.setSendingDate(notificationTemplate.getSendingDate());
        databaseNotificationTemplate.setEnabled(notificationTemplate.isEnabled());
        databaseNotificationTemplate.setTypeCode(notificationTemplate.getTypeCode());
        databaseNotificationTemplate.setCategoryCode(notificationTemplate.getCategoryCode());
        databaseNotificationTemplate.setImportant(notificationTemplate.isImportant());
        databaseNotificationTemplate.setUserSpecific(notificationTemplate.isUserSpecific());
        databaseNotificationTemplate.setDescription(notificationTemplate.getDescription());
        databaseNotificationTemplate.setStatusCode(notificationTemplate.getStatusCode());
        databaseNotificationTemplate.setForceSending(notificationTemplate.isForceSending());

        notificationTemplate.getNotificationTemplateContents().stream().filter(c -> c.getTitle().length() == 0 && c.getBody().length() == 0)
                .filter(c -> databaseNotificationTemplate.getNotificationTemplateContents().stream().anyMatch(databaseContent -> databaseContent.getId() == c.getId()))
                .forEach(c -> {
                    notificationTemplateContentService.deleteById(c.getId());
                    notificationTemplate.getNotificationTemplateContents().remove(c);
                });

        //Update notification contents
        notificationTemplate.getNotificationTemplateContents().forEach(content -> content.setNotificationTemplate(databaseNotificationTemplate));
        databaseNotificationTemplate.setNotificationTemplateContents(notificationTemplate.getNotificationTemplateContents());

        if (notificationTemplate.getNotificationTemplateCategorizing() != null) {
            NotificationTemplateCategorizingDto categorizing;
            if (notificationTemplate.getNotificationTemplateCategorizing().getSelectedApplicants() != null) {
                categorizing = new NotificationTemplateCategorizingDto();
                categorizing.setSelectedApplicants(notificationTemplate.getNotificationTemplateCategorizing().getSelectedApplicants());
            } else {
                categorizing = notificationTemplate.getNotificationTemplateCategorizing();
                categorizing.setSelectedApplicants(null);
            }
            if(notificationTemplate.getNotificationTemplateCategorizing().getSelectedGroupId() != null) {
                categorizing.setSelectedGroupId(notificationTemplate.getNotificationTemplateCategorizing().getSelectedGroupId());
            }
            categorizing.setId(databaseNotificationTemplate.getNotificationTemplateCategorizing().getId());
            categorizing.setCreationDate(databaseNotificationTemplate.getNotificationTemplateCategorizing().getCreationDate());
            categorizing.setNotificationTemplate(databaseNotificationTemplate);
            databaseNotificationTemplate.setNotificationTemplateCategorizing(categorizing);
        }
        log.info("end updateUserDefined notification template");
        return save(databaseNotificationTemplate);
    }

    public NotificationTemplateDto updateDescription(NotificationTemplateDto notificationTemplate) {
        log.info("start updateDescription for notification template");
        NotificationTemplateDto databaseNotificationTemplate = findOne(notificationTemplate.getId());
        databaseNotificationTemplate.setDescription(notificationTemplate.getDescription());
        log.info("end updateDescription for notification template");
        return save(databaseNotificationTemplate);
    }

    @Modifying
    public NotificationTemplateDto updatedProcessed(NotificationTemplateDto notificationTemplateDto) {
        log.info("start updatedProcessed for notification template");
        var notificationTemplate = findOne(notificationTemplateDto.getId());
        notificationTemplate.setProcessed(true);
        log.info("end updatedProcessed for notification template");
        return save(notificationTemplate);
    }
}
