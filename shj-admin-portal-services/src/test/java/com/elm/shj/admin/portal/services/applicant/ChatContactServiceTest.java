/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.dcc.foundation.commons.core.mapper.MapperRegistry;
import com.elm.shj.admin.portal.orm.repository.ChatContactRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantDtoMapper;
import com.elm.shj.admin.portal.services.generic.GenericService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Testing class for service {@link ChatContactService}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@ExtendWith(MockitoExtension.class)
public class ChatContactServiceTest {


    private final static String TEST_APPLICANT_UIN = "111";
    private final static String TEST_CONTACT_UIN = "222";
    @Mock
    private MapperRegistry mapperRegistry;
    @Mock
    private ApplicantDtoMapper applicantDtoMapper;

    @Mock
    private ChatContactRepository chatContactRepository;

    @InjectMocks
    private ChatContactService chatContactService;

    @Mock
    private GenericService genericService;
    @Mock
    private CycleAvoidingMappingContext mappingContext;

    @Test
    public void test_delete_applicant_chat_contact_success() {
        chatContactService.deleteApplicantChatContact(TEST_APPLICANT_UIN, TEST_CONTACT_UIN);
        verify(chatContactRepository, times(1)).markDeleted(anyString(), anyString());
    }


}
