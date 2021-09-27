/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPrintRequestLite;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Specification for print request request lite.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public class PrintRequestLiteSpecification {
    public static Specification<JpaPrintRequestLite> withFilterAware(final String statusCode, final String description) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();

            if (statusCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("statusCode"), statusCode));
            }

            if (description != null && description.length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + description.trim() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
