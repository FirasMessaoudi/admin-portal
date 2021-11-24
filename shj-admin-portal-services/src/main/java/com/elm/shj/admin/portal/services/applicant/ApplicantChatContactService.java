/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantChatContact;
import com.elm.shj.admin.portal.orm.repository.ApplicantChatContactRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantChatContactDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling applicant chat contacts
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantChatContactService extends GenericService<JpaApplicantChatContact, ApplicantChatContactDto, Long> {

    private final ApplicantChatContactRepository applicantChatContactRepository;

    @Transactional
    public long deleteApplicantChatContact(String uin) {
        return applicantChatContactRepository.deleteByUinAndSystemDefinedFalse(uin);
    }

}
