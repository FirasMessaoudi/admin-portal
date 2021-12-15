/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.entity.JpaApplicant;
import com.elm.shj.admin.portal.orm.repository.ApplicantChatContactRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantContactRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantDto;
import com.elm.shj.admin.portal.services.dto.ApplicantDtoMapper;
import com.elm.shj.admin.portal.services.dto.UpdateApplicantCmd;
import com.elm.shj.admin.portal.services.generic.GenericService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Testing class for service {@link ApplicantChatContactService}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class ApplicantChatContactServiceTest {



    private final static String TEST_APPLICANT_UIN = "111";
    private final static String TEST_CONTACT_UIN = "222";
    @Mock
    private MapperRegistry mapperRegistry;
    @Mock
    private ApplicantDtoMapper applicantDtoMapper;

    @Mock
    private ApplicantChatContactRepository applicantChatContactRepository;

    @InjectMocks
    private ApplicantChatContactService applicantChatContactService;

    @Mock
    private GenericService genericService;
    @Mock
    private CycleAvoidingMappingContext mappingContext;

    @Test
    public void test_delete_applicant_chat_contact_success() {
        applicantChatContactService.deleteApplicantChatContact(TEST_APPLICANT_UIN, TEST_CONTACT_UIN);
        verify(applicantChatContactRepository, times(1)).markDeleted(  anyString(),  anyString());
    }


}
