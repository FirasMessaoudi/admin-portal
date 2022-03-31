
/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.user;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaUserCardStatusAudit;
import com.elm.shj.admin.portal.orm.repository.UserCardStatusAuditRepository;
import com.elm.shj.admin.portal.services.card.UserCardStatusAuditService;
import com.elm.shj.admin.portal.services.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * Testing class for service {@link  UserCardStatusAuditService}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class UserCardStatusAuditServiceTest {
    private static final long TEST_USER_ID = 1;
    private static final String TEST_APPLICANT_UIN = "59718700000018";
    private static final long TEST_CARD_ID = 1033;
    private static final String TEST_CARD_STATUS_CODE = "CANCELLED";

    @InjectMocks
    private UserCardStatusAuditService serviceToTest;
    @Mock
    private MapperRegistry mapperRegistry;
    @Mock
    private UserCardStatusAuditDtoMapper userCardStatusAuditDtoMapper;
    @Mock
    private UserCardStatusAuditRepository userCardStatusAuditRepository;


    @BeforeEach
    public void setUp() throws IllegalAccessException {
        Mockito.lenient().when(mapperRegistry.mapperOf(UserCardStatusAuditDto.class, JpaUserCardStatusAudit.class)).thenReturn(userCardStatusAuditDtoMapper);
        Field mapperRegistryField = ReflectionUtils.findField(serviceToTest.getClass(), "mapperRegistry");
        Field repositoryField = ReflectionUtils.findField(serviceToTest.getClass(), "repository");
        ReflectionUtils.makeAccessible(mapperRegistryField);
        ReflectionUtils.makeAccessible(repositoryField);
        mapperRegistryField.set(serviceToTest, mapperRegistry);
        repositoryField.set(serviceToTest, userCardStatusAuditRepository);
    }


    @Test
    public void test_save_user_card_status_audit() {
        ApplicantDto applicantDto = new ApplicantDto();
        List listOfDigitalIds = new ArrayList<ApplicantDigitalIdDto>();
        listOfDigitalIds.add(ApplicantDigitalIdDto.builder().uin(TEST_APPLICANT_UIN).build());
        applicantDto.setDigitalIds(listOfDigitalIds);

        ApplicantCardDto card = ApplicantCardDto.builder()
                .id(TEST_CARD_ID)
                .statusCode(TEST_CARD_STATUS_CODE)
                .applicantRitual(ApplicantRitualDto.builder().applicant(applicantDto).build())
                .build();
        UserDto user = new UserDto();
        user.setId(TEST_USER_ID);

        UserCardStatusAuditDto userCardStatusAuditDto = UserCardStatusAuditDto.builder()
                .cardId(card.getId())
                .statusCode(card.getStatusCode())
                .userId(user.getId())
                .uin(card.getApplicantRitual().getApplicant().getDigitalIds().get(0).getUin())
                .build();

        when(serviceToTest.save(userCardStatusAuditDto)).thenReturn(userCardStatusAuditDto);
    }
}
