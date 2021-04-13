/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.rule;

import com.elm.shj.admin.portal.orm.entity.JpaDecisionRule;
import com.elm.shj.admin.portal.services.dto.DecisionRuleDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling decision rules
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Service
public class RuleService extends GenericService<JpaDecisionRule, DecisionRuleDto, Long> {

    /**
     * Find all rules.
     *
     * @param pageable the current page information
     * @return the list of rules
     */
    public Page<DecisionRuleDto> findAll(Pageable pageable) {
        return mapPage(getRepository().findAll(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public DecisionRuleDto save(DecisionRuleDto dto) {
        return super.save(dto);
    }
}
