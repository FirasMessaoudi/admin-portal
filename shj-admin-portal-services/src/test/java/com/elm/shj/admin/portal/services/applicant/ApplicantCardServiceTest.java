/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantCard;
import com.elm.shj.admin.portal.orm.repository.ApplicantCardRepository;
import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.card.UserCardStatusAuditService;
import com.elm.shj.admin.portal.services.dto.*;
import org.junit.jupiter.api.Assertions;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Testing class for service {@link  ApplicantCardService}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class ApplicantCardServiceTest {

    private final static long TEST_USER_ID = 1;
    private static final String TEST_APPLICANT_UIN = "59718700000018";
    private static final long TEST_CARD_ID = 1033;
    private static final String TEST_CARD_STATUS_CODE = "CANCELLED";

    @Mock
    private ApplicantCardRepository applicantCardRepository;
    @InjectMocks
    private ApplicantCardService serviceToTest;
    @Mock
    private MapperRegistry mapperRegistry;
    @Mock
    private UserCardStatusAuditService userCardStatusAuditService;
    @Mock
    private ApplicantCardDtoMapper applicantCardDtoMapper;


    @BeforeEach
    public void setUp() throws IllegalAccessException {
        Mockito.lenient().when(mapperRegistry.mapperOf(ApplicantCardDto.class, JpaApplicantCard.class)).thenReturn(applicantCardDtoMapper);
        Field mapperRegistryField = ReflectionUtils.findField(serviceToTest.getClass(), "mapperRegistry");
        Field repositoryField = ReflectionUtils.findField(serviceToTest.getClass(), "repository");
        ReflectionUtils.makeAccessible(mapperRegistryField);
        ReflectionUtils.makeAccessible(repositoryField);
        mapperRegistryField.set(serviceToTest, mapperRegistry);
        repositoryField.set(serviceToTest, applicantCardRepository);
    }
}
