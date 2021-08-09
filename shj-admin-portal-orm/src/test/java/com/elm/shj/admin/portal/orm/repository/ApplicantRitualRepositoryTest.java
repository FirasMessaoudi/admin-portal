package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ApplicantRitualRepositoryTest extends AbstractJpaTest {

    private final static String UIN_USER_EXIST = "59737700000059";
    private final static String NIN_USER_FAKE = "1111111111";

    @Autowired
    private ApplicantRitualRepository applicantRitualRepository;

    @Test
    public void test_find_applicant_ritual_hijriSeasons_by_uin_success() {
        List<Integer> seasonsList = applicantRitualRepository.findApplicantRitualHijriSeasonsByUin(UIN_USER_EXIST);

        Assert.assertNotNull(seasonsList);
        Assert.assertEquals(2, seasonsList.size());
        Assert.assertEquals(Optional.of(1443), Optional.of(seasonsList.get(0)));
        Assert.assertEquals(Optional.of(1442), Optional.of(seasonsList.get(1)));
    }

    @Test
    public void test_find_applicant_ritual_hijriSeasons_by_uin_fail() {
        List<Integer> seasonsList = applicantRitualRepository.findApplicantRitualHijriSeasonsByUin(NIN_USER_FAKE);

        Assert.assertNotNull(seasonsList);
        Assert.assertEquals(0, seasonsList.size());
    }
}
