package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantCardDetails;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantCardRepositoryTest extends AbstractJpaTest {

    private final static String EXIST_USER_UIN = "50208700000027";
    private final static String FAKE_USER_UIN = "123456987";


    @Autowired
    private ApplicantCardRepository applicantCardRepository;

    @Test
    public void test_find_details_By_uin_success() {
        Optional<ApplicantCardDetails> card = applicantCardRepository.findCardDetailsByUin(EXIST_USER_UIN);
        assertTrue(card.isPresent());
    }

    @Test
    public void test_find_details_By_uin_fail() {
        Optional<ApplicantCardDetails> card = applicantCardRepository.findCardDetailsByUin(FAKE_USER_UIN);
        assertFalse(card.isPresent());

    }
}
