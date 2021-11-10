/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaUserCardStatusAudit;
import com.elm.shj.admin.portal.services.dto.ApplicantCardDto;
import com.elm.shj.admin.portal.services.dto.UserCardStatusAuditDto;
import com.elm.shj.admin.portal.services.dto.UserDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service handling applicant card
 *
 * @author Ahnmed Ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserCardStatusAuditService extends GenericService<JpaUserCardStatusAudit, UserCardStatusAuditDto, Long> {

    public UserCardStatusAuditDto saveUserCardStatusAudit(ApplicantCardDto card, Optional<Long> userId) {
        UserDto user = new UserDto();
        user.setId(userId.get());
        UserCardStatusAuditDto userCardStatusAuditDto = UserCardStatusAuditDto.builder()
                .card(card)
                .statusCode(card.getStatusCode())
                .user(user)
                .uin(card.getApplicantRitual().getApplicant().getDigitalIds().get(0).getUin())
                .build();
        return save(userCardStatusAuditDto);
    }

    public List<UserCardStatusAuditDto> saveUserCardStatusAudit(List<ApplicantCardDto> cards, Optional<Long> userId) {
        List<UserCardStatusAuditDto> auditedList = new ArrayList<>();
        cards.parallelStream().forEach(card -> {
            auditedList.add(saveUserCardStatusAudit(card, userId));
        });
        return auditedList;
    }

}
