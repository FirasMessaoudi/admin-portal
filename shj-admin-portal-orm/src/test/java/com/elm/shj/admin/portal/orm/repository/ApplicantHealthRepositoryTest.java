/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantHealth;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing class for {@link ApplicantHealthRepository}
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public class ApplicantHealthRepositoryTest extends AbstractJpaTest {

    private final static String EXISTING_APPLICANT_UIN = "90208700000011";
    private final static Long EXISTING_APPLICANT_RITUAL_ID = 18111L;
    private final static String FAKE_APPLICANT_UIN = "11111111111111";
    private final static Long FAKE_APPLICANT_RITUAL_ID = 11111L;

    @Autowired
    private ApplicantHealthRepository applicantHealthRepository;

    @Test
    public void test_find_applicant_by_uin_and_ritual_id_success() {
        JpaApplicantHealth applicantHealth = applicantHealthRepository.findByUinAndRitualId(EXISTING_APPLICANT_UIN, EXISTING_APPLICANT_RITUAL_ID);
        assertNotNull(applicantHealth);
        assertEquals("A+", applicantHealth.getBloodType());
        assertEquals(true, applicantHealth.getHasSpecialNeeds());
        assertEquals("1234567891234567891234567891234567891234567891236", applicantHealth.getInsurancePolicyNumber());
    }

    @Test
    public void test_find_by_uin_does_not_exist() {
    }

}
