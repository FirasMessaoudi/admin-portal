package com.elm.shj.admin.portal.orm.repository;


import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageTransportation;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing class for {@link ApplicantPackageTransportationRepository}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class ApplicantPackageTransportationRepositoryTest extends AbstractJpaTest {

    private final static long EXIST_USER_UIN = 59737700000059L;
    private final static long FAKE_USER_UIN = 1111111111L;

    @Autowired
    private ApplicantPackageTransportationRepository applicantPackageTransportationRepository;

    @Test
    public void test_find_all_by_ApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanySeasonPackagesCompanyRitualSeasonId_found() {

        List<JpaApplicantPackageTransportation> applicantPackageTransportations = applicantPackageTransportationRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanyRitualSeasonId(EXIST_USER_UIN, 1);

        assertNotNull(applicantPackageTransportations);
        assertEquals(1, applicantPackageTransportations.size());

    }

    @Test
    public void test_find_all_by_ApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanySeasonPackagesCompanyRitualSeasonId_notFound() {

        List<JpaApplicantPackageTransportation> applicantPackageTransportations = applicantPackageTransportationRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageRitualPackageCompanyRitualSeasonId(FAKE_USER_UIN, 1);

        assertNotNull(applicantPackageTransportations);
        assertEquals(0, applicantPackageTransportations.size());

    }
}
