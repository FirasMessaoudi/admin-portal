package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ApplicantRitualRepositoryTest extends AbstractJpaTest {

    private final static String EXIST_USER_UIN = "59737700000059";
    private final static String EXIST_USER_UIN2 = "50208700000027";
    private final static String FAKE_USER_UIN = "1111111111";
    private final static long EXIST_RITUAL_ID = 36;
    @Autowired
    private ApplicantRitualRepository applicantRitualRepository;



    @Test
    public void test_find_details_By_uin_success() {
        JpaApplicantRitual card = new JpaApplicantRitual();
        JpaApplicantRitual actualCardDetails = applicantRitualRepository.findCardDetailsByUinAndRitualId(EXIST_USER_UIN2, EXIST_RITUAL_ID);
        assertEquals(actualCardDetails.getId(), card.getId());
    }

    @Test
    public void test_find_details_By_uin_fail() {
        JpaApplicantRitual card = applicantRitualRepository.findCardDetailsByUinAndRitualId(FAKE_USER_UIN, EXIST_RITUAL_ID);
        assertNull(card);
    }
}
