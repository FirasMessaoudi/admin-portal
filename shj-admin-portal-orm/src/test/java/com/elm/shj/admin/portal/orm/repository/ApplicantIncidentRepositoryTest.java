/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * Testing class for {@link ApplicantIncidentRepository}
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class ApplicantIncidentRepositoryTest extends AbstractJpaTest {

    private final static long EXIST_APPLICANT_RITUAL_ID = 1;
    private final static long FAKE_APPLICANT_RITUAL_ID = 24;


    @Autowired
    private ApplicantIncidentRepository applicantIncidentRepository;

    @Test
    void test_find_by_applicant_ritualId() {
        List<JpaApplicantIncident> incidents = applicantIncidentRepository.findByApplicantRitualId(EXIST_APPLICANT_RITUAL_ID);
        assertNotNull(incidents);
        assertEquals(1,incidents.size());
        assertEquals(EXIST_APPLICANT_RITUAL_ID,incidents.get(0).getApplicantRitual().getId());

    }


}
