package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.repository.ApplicantCardRepository;
import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ApplicantCardServiceTest {

    private final static String EXIST_USER_UIN = "50208700000027";
    private final static String FAKE_USER_UIN = "123456987";

    @InjectMocks
    private ApplicantCardService applicantCardService;
    @Mock
    private ApplicantCardRepository applicantCardRepository;



}
