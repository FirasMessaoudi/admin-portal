/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.DecisionRuleDto;
import com.elm.shj.admin.portal.services.rule.RuleService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;

/**
 * Main controller for rule management page
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_RULES)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RuleManagementController {

    private final RuleService ruleService;

    /**
     * List all rules.
     *
     * @return the list of rules
     */
    @GetMapping("/list")
    @RolesAllowed({AuthorityConstants.ROLE_MANAGEMENT})
    public Page<DecisionRuleDto> listRules(Pageable pageable) {
        log.info("list all rules.");
        return ruleService.findAll(pageable);
    }

    /**
     * finds a rule by its ID
     *
     * @param ruleId the rule id to find
     * @return the found rule or <code>null</code>
     */
    @GetMapping("/find/{ruleId}")
    @RolesAllowed(AuthorityConstants.ROLE_MANAGEMENT)
    public DecisionRuleDto findRole(@PathVariable long ruleId) {
        log.debug("Handler for {}", "Find Rule");
        return ruleService.findOne(ruleId);
    }

    /**
     * Creates or Updates and existing rule
     *
     * @param rule the rule to update
     * @return the created or updated role
     */
    @PostMapping("/save-or-update")
    @RolesAllowed(AuthorityConstants.EDIT_ROLE)
    public ResponseEntity<DecisionRuleDto> saveOrUpdate(@RequestBody @Validated DecisionRuleDto rule) {
        log.debug("Handler for {}", "Save Or Update Rule");
        rule.setUpdateDate(new Date());
        return ResponseEntity.ok(ruleService.save(rule));
    }

}
