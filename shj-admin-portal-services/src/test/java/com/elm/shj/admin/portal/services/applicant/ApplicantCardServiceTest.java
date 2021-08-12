package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.ApplicantCardDetails;
import com.elm.shj.admin.portal.orm.repository.ApplicantCardRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantRitualRepository;
import com.elm.shj.admin.portal.services.card.ApplicantCardService;
import com.elm.shj.admin.portal.services.ritual.ApplicantRitualService;
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


    @Test
    public void test_find_applicant_card_details_by_uin_success() {
        ApplicantCardDetails card = new ApplicantCardDetails();
        card.setFullNameEn("Abdelghany Abdelaziz Abdelaziz Elsayed");
        Mockito.when(applicantCardRepository.findCardDetailsByUin(EXIST_USER_UIN)).thenReturn(Optional.of(card));
        Optional<ApplicantCardDetails> returnedCard = applicantCardService.findCardDetailsByUin(EXIST_USER_UIN);
        Assert.assertTrue(returnedCard.isPresent());
        Assert.assertEquals(returnedCard.get().getFullNameEn(), card.getFullNameEn());
    }

    @Test
    public void test_find_applicant_card_details_by_uin_not_found() {
        Mockito.when(applicantCardRepository.findCardDetailsByUin(FAKE_USER_UIN)).thenReturn(Optional.empty());
        Optional<ApplicantCardDetails> returnedCard = applicantCardService.findCardDetailsByUin(FAKE_USER_UIN);
        Assert.assertEquals(returnedCard, Optional.empty());
    }
}
