package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for {@link CompanyRitualSeasonRepository}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class CompanyRitualSeasonRepositoryTest extends AbstractJpaTest {

    private final static long EXIST_USER_UIN = 59737700000059L;
    private final static long FAKE_USER_UIN = 1111111111L;

    @Autowired
    private CompanyRitualSeasonRepository companyRitualSeasonRepository;

    @Test
    public void test_find_top_by_applicantGroups_groupApplicantLists_applicantUin_order_by_seasonStartDesc_found() {

        JpaCompanyRitualSeason companyRitualSeason = companyRitualSeasonRepository.findTopByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(EXIST_USER_UIN);

        assertNotNull(companyRitualSeason);
        assertEquals(14430210, companyRitualSeason.getSeasonStart());
        assertNotNull(companyRitualSeason.getRitualSeason());
        assertEquals("INTERNAL_HAJJ", companyRitualSeason.getRitualSeason().getRitualTypeCode());

    }

    @Test
    public void test_find_top_by_applicantGroups_groupApplicantLists_applicantUin_order_by_seasonStartDesc_notFound() {

        JpaCompanyRitualSeason companyRitualSeason = companyRitualSeasonRepository.findTopByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(FAKE_USER_UIN);

        assertNull(companyRitualSeason);

    }

    @Test
    public void test_find_all_by_applicantGroups_groupApplicantLists_applicantUin_order_by_seasonStartDesc_found() {

        List<JpaCompanyRitualSeason> companyRitualSeasons = companyRitualSeasonRepository.findAllByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(EXIST_USER_UIN);

        assertNotNull(companyRitualSeasons);
        assertEquals(1, companyRitualSeasons.size());
        assertEquals(14430210, companyRitualSeasons.get(0).getSeasonStart());
        assertNotNull(companyRitualSeasons.get(0).getRitualSeason());
        assertEquals("INTERNAL_HAJJ", companyRitualSeasons.get(0).getRitualSeason().getRitualTypeCode());

    }

    @Test
    public void test_find_all_by_applicantGroups_groupApplicantLists_applicantUin_order_by_seasonStartDesc_notFound() {

        List<JpaCompanyRitualSeason> companyRitualSeasons = companyRitualSeasonRepository.findAllByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(FAKE_USER_UIN);

        assertNotNull(companyRitualSeasons);
        assertEquals(0, companyRitualSeasons.size());

    }

}
