package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for {@link CompanyRitualSeasonRepository}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class CompanyRitualSeasonRepositoryTest extends AbstractJpaTest {

    private final static String EXIST_USER_UIN = "59737700000059";
    private final static String FAKE_USER_UIN = "1111111111";

    @Autowired
    private CompanyRitualSeasonRepository companyRitualSeasonRepository;

    @Test
    public void test_find_top_by_applicantGroups_groupApplicantLists_applicantUin_order_by_seasonStartDesc_found() {

        JpaCompanyRitualSeason companyRitualSeason = companyRitualSeasonRepository.findTopByApplicantGroupsGroupApplicantListsApplicantUinOrderBySeasonStartDesc(EXIST_USER_UIN);

        assertNotNull(companyRitualSeason);
        assertEquals(14430210, companyRitualSeason.getSeasonStart());
        assertNotNull(companyRitualSeason.getRitualSeason());
        assertEquals("INTERNAL_HAJJ", companyRitualSeason.getRitualSeason().getRitualTypeCode());

    }

    @Test
    public void test_find_top_by_applicantGroups_groupApplicantLists_applicantUin_order_by_seasonStartDesc_notFound() {

        JpaCompanyRitualSeason companyRitualSeason = companyRitualSeasonRepository.findTopByApplicantGroupsGroupApplicantListsApplicantUinOrderBySeasonStartDesc(FAKE_USER_UIN);

        assertNull(companyRitualSeason);

    }

}
