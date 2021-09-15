/*
 *  Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for {@link CompanyRitualStepRepository}
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@RunWith(SpringRunner.class)
public class CompanyRitualStepRepositoryTest extends AbstractJpaTest {
    private final static String EXIST_USER_UIN = "59737700000059";
    private final static String FAKE_USER_UIN = "1111111111";
    private final static long COMPANY_RITUAL_SEASON_ID = 1;

    @Autowired
    private  CompanyRitualStepRepository companyRitualStepRepository;

    @Test
    public void test_find_by_uin_and_company_ritual_season_success(){
        List<JpaCompanyRitualStep> companyRitualSteps = companyRitualStepRepository.findByApplicantGroupGroupApplicantListsApplicantUinAndApplicantGroupCompanyRitualSeasonIdOrderByStepIndexAsc(EXIST_USER_UIN,COMPANY_RITUAL_SEASON_ID);
        assertFalse(companyRitualSteps.isEmpty());

    }
    @Test
    public void test_find_by_uin_and_company_ritual_season_fail(){
        List<JpaCompanyRitualStep> companyRitualSteps = companyRitualStepRepository.findByApplicantGroupGroupApplicantListsApplicantUinAndApplicantGroupCompanyRitualSeasonIdOrderByStepIndexAsc(FAKE_USER_UIN,COMPANY_RITUAL_SEASON_ID);
        assertTrue(companyRitualSteps.isEmpty());
    }
    @Test
    public void test_find_all(){
        List<JpaCompanyRitualStep> companyRitualSteps = companyRitualStepRepository.findAll();
        assertEquals(1,companyRitualSteps.size());
    }

}
