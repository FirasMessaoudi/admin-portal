/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicantCardRepositoryTest extends AbstractJpaTest {

    private final static String EXIST_USER_UIN = "50208700000027";
    private final static String FAKE_USER_UIN = "123456987";


    @Autowired
    private ApplicantCardRepository applicantCardRepository;

}
