package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ApplicantMainDataRepositoryTest extends AbstractJpaTest {

    private final static String UIN_USER_EXIST = "59737700000059";
    private final static String NIN_USER_FAKE = "1111111111";


    @Autowired
    private ApplicantMainDataRepository applicantMainDataRepository;

    @Test
    public void test_find_By_uin_success() {
        JpaApplicantMainData jpaApplicantMainData = applicantMainDataRepository.findByDigitalIdsUinAndDeletedFalse(UIN_USER_EXIST);
        assertNotNull(jpaApplicantMainData);
    }

    @Test
    public void test_find_By_uin_fail() {
        JpaApplicantMainData jpaApplicantMainData = applicantMainDataRepository.findByDigitalIdsUinAndDeletedFalse(NIN_USER_FAKE);
        assertNull(jpaApplicantMainData);
    }
}
