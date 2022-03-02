/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing class for {@link ChatContactRepository}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class ChatContactRepositoryTest extends AbstractJpaTest {

    private final static String TEST_APPLICANT_UIN = "123";
    private final static String TEST_CONTACT_UIN = "123";

    private final static String TEST_ANOTHER_APPLICANT_UIN = "111";
    private final static String TEST_ANOTHER_CONTACT_UIN = "222";

    private final static long TEST_APPLICANT_RITUAL_ID = 24;

    @Autowired
    private ChatContactRepository chatContactRepository;


    @Test
    public void test_delete_applicant_chat_contact_success() {

        int numOfAffectedRows = chatContactRepository.markDeleted(TEST_ANOTHER_APPLICANT_UIN, TEST_ANOTHER_CONTACT_UIN);
        entityManager.clear();
        assertTrue(numOfAffectedRows > 0);
    }


    @Test
    public void test_delete_applicant_chat_contact_fail() {

        int numOfAffectedRows = chatContactRepository.markDeleted(TEST_APPLICANT_UIN, TEST_CONTACT_UIN);
        entityManager.clear();
        assertFalse(numOfAffectedRows > 0);
    }


}
