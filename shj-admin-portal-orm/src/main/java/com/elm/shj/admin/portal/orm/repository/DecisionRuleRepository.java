/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaDecisionRule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for DecisionRule Table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface DecisionRuleRepository extends JpaRepository<JpaDecisionRule, Long> {
}
