/*
 *  Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantContact;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing class for {@link UserRepository}
 *
 * @author Aymen Dhaoui
 * @since 1.0.0
 */
public class ApplicantContactRepositoryTest extends AbstractJpaTest {

    private final static String TEST_UIN = "50208700000027";
    private final static long TEST_APPLICANT_ID = 1;
    private final static long TEST_APPLICANT_CONTACT_ID = 1;
    private final static long TEST_RITUAL_ID = 1;
    private final static String TEST_LOCAL_MOBILE = "0555359285";
    private final static String TEST_INTL_MOBILE = "00201154785699";
    private final static String TEST_EMAIL = "app@elm.sa";
    private final static String TEST_COUNTRY_CODE = "SA";

    @Autowired
    private ApplicantContactRepository applicantContactRepository;
    @Autowired
    private ApplicantRepository applicantRepository;

    @Test
    public void test_update_contact_intl_number_success() {

        applicantContactRepository.updateContactIntlNumber(TEST_EMAIL, TEST_INTL_MOBILE, TEST_APPLICANT_ID);
        entityManager.clear();
        Optional<JpaApplicantContact> jpaApplicantContact = applicantContactRepository.findById(TEST_APPLICANT_CONTACT_ID);
        assertEquals(jpaApplicantContact.get().getIntlMobileNumber(), TEST_INTL_MOBILE);
    }

    @Test
    public void test_update_contact_local_number_success() {
        applicantContactRepository.updateContactLocalNumber(TEST_EMAIL, TEST_LOCAL_MOBILE, TEST_APPLICANT_ID);
        entityManager.clear();
        Optional<JpaApplicantContact> jpaApplicantContact = applicantContactRepository.findById(TEST_APPLICANT_CONTACT_ID);
        assertEquals(jpaApplicantContact.get().getLocalMobileNumber(), TEST_LOCAL_MOBILE);
    }


}
