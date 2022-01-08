package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageCatering;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing class for {@link ApplicantPackageCateringRepository}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class ApplicantPackageCateringRepositoryTest extends AbstractJpaTest {

    private final static long EXIST_USER_UIN = 59737700000059L;
    private final static long FAKE_USER_UIN = 1111111111L;

    @Autowired
    private ApplicantPackageCateringRepository applicantPackageCateringRepository;


    @Test
    public void test_findAllByApplicantPackageApplicantUinAndApplicantPackageId_found() {
        List<JpaApplicantPackageCatering> applicantPackageCaterings = applicantPackageCateringRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageId(EXIST_USER_UIN, 1);

        assertNotNull(applicantPackageCaterings);
        assertEquals(1, applicantPackageCaterings.size());

    }

    @Test
    public void test_findAllByApplicantPackageApplicantUinAndApplicantPackageId_notFound() {
        List<JpaApplicantPackageCatering> applicantPackageCaterings = applicantPackageCateringRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageId(FAKE_USER_UIN, 1);

        assertNotNull(applicantPackageCaterings);
        assertEquals(0, applicantPackageCaterings.size());

    }


}
