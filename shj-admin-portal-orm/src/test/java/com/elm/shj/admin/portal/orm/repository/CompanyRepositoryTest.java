package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompany;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for {@link CompanyRepository}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class CompanyRepositoryTest extends AbstractJpaTest {

    private final static long EXIST_USER_UIN = 59737700000059L;
    private final static long FAKE_USER_UIN = 1111111111L;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void test_findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsApplicantGroupsGroupApplicantListsApplicantUin_found() {

        JpaCompany company = companyRepository.findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsRitualPackagesApplicantPackagesApplicantUin(1, EXIST_USER_UIN);

        assertNotNull(company);
        assertEquals("elm", company.getLabelEn());
    }

    @Test
    public void test_findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsApplicantGroupsGroupApplicantListsApplicantUin_notFound() {

        JpaCompany company = companyRepository.findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsRitualPackagesApplicantPackagesApplicantUin(1, FAKE_USER_UIN);

        assertNull(company);
    }
}
