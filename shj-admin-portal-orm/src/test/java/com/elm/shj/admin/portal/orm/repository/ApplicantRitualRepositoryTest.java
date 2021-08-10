package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ApplicantRitualRepositoryTest extends AbstractJpaTest {

    private final static String EXIST_USER_UIN = "59737700000059";
    private final static String FAKE_USER_UIN = "1111111111";

    @Autowired
    private ApplicantRitualRepository applicantRitualRepository;

    @Test
    public void test_find_applicant_ritual_hijriSeasons_by_uin_success() {
        List<Integer> seasonsList = applicantRitualRepository.findApplicantRitualHijriSeasonsByUin(EXIST_USER_UIN);

        Assert.assertNotNull(seasonsList);
        Assert.assertEquals(2, seasonsList.size());
        Assert.assertEquals(Optional.of(1443), Optional.of(seasonsList.get(0)));
        Assert.assertEquals(Optional.of(1442), Optional.of(seasonsList.get(1)));
    }

    @Test
    public void test_find_applicant_ritual_hijriSeasons_by_uin_notFound() {
        List<Integer> seasonsList = applicantRitualRepository.findApplicantRitualHijriSeasonsByUin(FAKE_USER_UIN);

        Assert.assertNotNull(seasonsList);
        Assert.assertEquals(0, seasonsList.size());
    }

    @Test
    public void test_find_applicant_ritual_by_uin_and_season_success() {
        List<JpaApplicantRitual> applicantRituals = applicantRitualRepository.findApplicantRitualByUinAndSeason(EXIST_USER_UIN, 1442);

        Assert.assertNotNull(applicantRituals);
        Assert.assertEquals(1, applicantRituals.size());
        Assert.assertEquals("INTERNAL_UMRAH", applicantRituals.get(0).getTypeCode());

    }

    @Test
    public void test_find_applicant_ritual_by_uin_and_season_notFound() {
        List<JpaApplicantRitual> applicantRituals = applicantRitualRepository.findApplicantRitualByUinAndSeason(EXIST_USER_UIN, 1441);

        Assert.assertNotNull(applicantRituals);
        Assert.assertEquals(0, applicantRituals.size());

    }
}
