/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.dto.CompanyRitualStepDto;
import com.elm.shj.admin.portal.services.ritual.CompanyRitualStepService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Main controller for company ritual steps
 *
 * @author firas_messaoudi
 * @since 1.1.0
 */
@RestController
@RequestMapping(Navigation.API_APPLICANTS)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRitualStepController {
    private final CompanyRitualStepService companyRitualStepService;

    /**
     * List of company ritual steps
     * @param uin
     *
     * @return list of company ritual steps
     */
    @GetMapping("/findRitualStepsByUin/{uin}")
    List<CompanyRitualStepDto> findByApplicantUin(@PathVariable String uin){
        log.info("returning list of company ritual steps.");
        return companyRitualStepService.findByApplicantUin(uin);
    }
}

