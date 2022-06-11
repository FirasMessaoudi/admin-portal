/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.card;

import com.elm.shj.admin.portal.orm.entity.JpaUserCardStatusAudit;
import com.elm.shj.admin.portal.services.dto.ApplicantCardBasicDto;
import com.elm.shj.admin.portal.services.dto.ApplicantCardDto;
import com.elm.shj.admin.portal.services.dto.UserCardStatusAuditDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling card audit logs
 *
 * @author Ahnmed Ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserCardStatusAuditService extends GenericService<JpaUserCardStatusAudit, UserCardStatusAuditDto, Long> {

    public void saveUserCardStatusAudit(ApplicantCardDto card, long userId) {
        save(UserCardStatusAuditDto.builder().cardId(card.getId()).statusCode(card.getStatusCode()).userId(userId)
                .uin(card.getApplicantRitual().getApplicant().getDigitalIds().get(0).getUin()).build());
    }

    public void saveUserBasicCardStatusAudit(ApplicantCardBasicDto card, long userId) {
        save(UserCardStatusAuditDto.builder().cardId(card.getId()).statusCode(card.getStatusCode()).userId(userId)
                .uin(card.getApplicantRitual().getApplicant().getDigitalIds().get(0).getUin()).build());
    }
}
