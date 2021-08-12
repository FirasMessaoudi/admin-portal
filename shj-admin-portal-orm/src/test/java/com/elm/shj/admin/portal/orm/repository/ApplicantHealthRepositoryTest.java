/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Testing class for {@link ApplicantHealthRepository}
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public class ApplicantHealthRepositoryTest extends AbstractJpaTest {

    private final static String EXISTING_APPLICANT_UIN = "59737700000059";
    private final static String FAKE_APPLICANT_UIN = "11111111111111";

    @Autowired
    private ApplicantHealthRepository applicantHealthRepository;

    @Test
    public void test_find_by_uin_success() {
    }

    @Test
    public void test_find_by_uin_does_not_exist() {
    }

}
