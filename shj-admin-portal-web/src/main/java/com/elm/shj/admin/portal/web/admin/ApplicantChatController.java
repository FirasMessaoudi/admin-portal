/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.applicant.ApplicantChatContactService;
import com.elm.shj.admin.portal.services.dto.ApplicantChatContactLiteDto;
import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Main controller for applicant chat pages
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@RestController
@RequestMapping(Navigation.API_APPLICANT_CHAT)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantChatController {

    private final ApplicantChatContactService applicantChatContactService;

    /**
     * finds chat contacts by applicant UIN
     *
     * @param uin the UIN of the applicant
     * @return the found chat contacts or empty list
     */
    @GetMapping("/contacts/{uin}")
    @PreAuthorize("hasAuthority('" + AuthorityConstants.USER_MANAGEMENT + "')") //TODO Change this
    public List<ApplicantChatContactLiteDto> listApplicantChatContacts(@PathVariable("uin") String uin,
                                                                       @RequestParam(required = false) Boolean systemDefined,
                                                                       Authentication authentication) {
        log.debug("Find chat contacts by applicant uin {}", uin);
        return applicantChatContactService.listApplicantChatContacts(uin, systemDefined);
    }

}
