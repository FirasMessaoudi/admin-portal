/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantChatContact;
import com.elm.shj.admin.portal.orm.repository.ApplicantChatContactRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantChatContactDto;
import com.elm.shj.admin.portal.services.dto.ApplicantChatContactLiteDto;
import com.elm.shj.admin.portal.services.dto.ApplicantLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final ApplicantLiteService applicantLiteService;

    /**
     * List all chat contacts of a specific applicant.
     *
     * @param applicantUin the UIN of the applicant
     * @return the list of chat contacts
     */
    public List<ApplicantChatContactLiteDto> listApplicantChatContacts(String applicantUin, Boolean systemDefined) {
        List<ApplicantChatContactDto> contacts;
        if (systemDefined == null) {
            contacts = mapList(applicantChatContactRepository.findAllByApplicantUin(applicantUin));
        } else {
            contacts = mapList(applicantChatContactRepository.findAllByApplicantUinAndSystemDefined(applicantUin, systemDefined));
        }
        return contacts.parallelStream().map(c -> {
            Optional<ApplicantLiteDto> applicantLite = applicantLiteService.findByUin(c.getContactUin());
            return ApplicantChatContactLiteDto.builder()
                    .id(c.getId())
                    .applicantUin(c.getApplicantUin())
                    .contactUin(c.getContactUin())
                    .contactFullNameAr(applicantLite.map(ApplicantLiteDto::getFullNameAr).orElse(null))
                    .contactFullNameEn(applicantLite.map(ApplicantLiteDto::getFullNameEn).orElse(null))
                    .typeId(c.getType().getId())
                    .alias(c.getAlias())
                    .systemDefined(c.getSystemDefined())
                    .staffTitleCode(c.getStaffTitleCode())
                    .mobileNumber(c.getMobileNumber())
                    .deleted(c.getDeleted())
                    .creationDate(c.getCreationDate())
                    .updateDate(c.getUpdateDate())
                    .build();
        }).collect(Collectors.toList());
    }

    @Transactional
    public long deleteApplicantChatContact(String applicantUin) {
        return applicantChatContactRepository.deleteByApplicantUinAndSystemDefinedFalse(applicantUin);
    }

}
