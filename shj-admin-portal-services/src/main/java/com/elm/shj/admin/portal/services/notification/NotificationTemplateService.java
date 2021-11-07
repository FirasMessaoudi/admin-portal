/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import com.elm.shj.admin.portal.orm.repository.NotificationTemplateRepository;
import com.elm.shj.admin.portal.services.dto.NotificationSearchCriteriaDto;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service handling  Notification Template
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationTemplateService extends GenericService<JpaNotificationTemplate, NotificationTemplateDto, Long> {

    private final NotificationTemplateRepository notificationTemplateRepository;

    /**
     * Finds Notification Template By Name
     *
     * @param nameCode the nameCode of the notification template  to find
     * @return the found Notification Template or empty structure
     */
    public Optional<NotificationTemplateDto> findEnabledNotificationTemplateByNameCode(String nameCode) {
        JpaNotificationTemplate jpaNotificationTemplate = notificationTemplateRepository.findByNameCodeAndEnabledTrue(nameCode);
        return (jpaNotificationTemplate != null) ? Optional.of(getMapper().fromEntity(jpaNotificationTemplate, mappingContext)) : Optional.empty();
    }

    /**
     * update Notification Template
     *
     * @param notificationTemplate the notification Template to be updated
     * @return the updated Notification Template
     */
    public NotificationTemplateDto updateNotificationTemplate(NotificationTemplateDto notificationTemplate) {

        notificationTemplate.getNotificationTemplateContents().parallelStream().forEach(content -> {
            content.setNotificationTemplate(notificationTemplate);
        });
        NotificationTemplateDto savedNotificationTemplate = findOne(notificationTemplate.getId());
        savedNotificationTemplate.setEnabled(notificationTemplate.isEnabled());
        savedNotificationTemplate.setNotificationTemplateContents(notificationTemplate.getNotificationTemplateContents());

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
            root.fetch("notificationTemplateContents");

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
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), notificationSearchCriteria.getCreationDateEnd()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public NotificationTemplateDto create(NotificationTemplateDto notificationTemplate) {
        notificationTemplate.getNotificationTemplateContents().parallelStream().forEach(content -> content.setNotificationTemplate(notificationTemplate));
        notificationTemplate.setNotificationTemplateContents(notificationTemplate.getNotificationTemplateContents());
        return save(notificationTemplate);
    }
}
