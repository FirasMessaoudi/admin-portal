/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;


import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing class for {@link ApplicantPackageHousingRepository}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class ApplicantPackageHousingRepositoryTest extends AbstractJpaTest {

    private final static long EXIST_USER_UIN = 59737700000059L;
    private final static long FAKE_USER_UIN = 1111111111L;

    @Autowired
    private ApplicantPackageHousingRepository applicantPackageHousingRepository;

    @Test
    public void test_find_all_by_ApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanySeasonPackagesCompanyRitualSeasonId_found() {

        List<JpaApplicantPackageHousing> applicantPackageHousings = applicantPackageHousingRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanyRitualSeasonId(EXIST_USER_UIN, 1);

        assertNotNull(applicantPackageHousings);
        assertEquals(1, applicantPackageHousings.size());

    }

    @Test
    public void test_find_all_by_ApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanySeasonPackagesCompanyRitualSeasonId_notFound() {

        List<JpaApplicantPackageHousing> applicantPackageHousings = applicantPackageHousingRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanyRitualSeasonId(FAKE_USER_UIN, 1);

        assertNotNull(applicantPackageHousings);
        assertEquals(0, applicantPackageHousings.size());

    }
}
