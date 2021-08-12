package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.orm.test.AbstractJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class ApplicantRitualRepositoryTest extends AbstractJpaTest {

    private final static String EXIST_USER_UIN = "59737700000059";
    private final static String EXIST_USER_UIN2 = "50208700000027";
    private final static String FAKE_USER_UIN = "1111111111";
    private final static long EXIST_RITUAL_ID = 36;
    @Autowired
    private ApplicantRitualRepository applicantRitualRepository;

    @Test
    public void test_find_applicant_ritual_hijriSeasons_by_uin_success() {
        List<Integer> seasonsList = applicantRitualRepository.findApplicantRitualHijriSeasonsByUin(EXIST_USER_UIN);

        assertNotNull(seasonsList);
        assertEquals(2, seasonsList.size());
        assertEquals(Optional.of(1443), Optional.of(seasonsList.get(0)));
        assertEquals(Optional.of(1442), Optional.of(seasonsList.get(1)));
    }

    @Test
    public void test_find_applicant_ritual_hijriSeasons_by_uin_notFound() {
        List<Integer> seasonsList = applicantRitualRepository.findApplicantRitualHijriSeasonsByUin(FAKE_USER_UIN);

        assertNotNull(seasonsList);
        assertEquals(0, seasonsList.size());
    }

    @Test
    public void test_find_applicant_ritual_by_uin_and_season_success() {
        List<JpaApplicantRitual> applicantRituals = applicantRitualRepository.findApplicantRitualByUinAndSeason(EXIST_USER_UIN, 1442);

        assertNotNull(applicantRituals);
        assertEquals(1, applicantRituals.size());
        assertEquals("INTERNAL_UMRAH", applicantRituals.get(0).getTypeCode());

    }

    @Test
    public void test_find_applicant_ritual_by_uin_and_season_notFound() {
        List<JpaApplicantRitual> applicantRituals = applicantRitualRepository.findApplicantRitualByUinAndSeason(EXIST_USER_UIN, 1441);

        assertNotNull(applicantRituals);
        assertEquals(0, applicantRituals.size());

    }


    @Test
    public void test_find_details_By_uin_success() {
        JpaApplicantRitual card = new JpaApplicantRitual();
        card.setUnitCode("2");
        JpaApplicantRitual actualCardDetails = applicantRitualRepository.findCardDetailsByUinAndRitualId(EXIST_USER_UIN2, EXIST_RITUAL_ID);
        assertEquals(actualCardDetails.getUnitCode(), card.getUnitCode());
    }

    @Test
    public void test_find_details_By_uin_fail() {
        JpaApplicantRitual card = applicantRitualRepository.findCardDetailsByUinAndRitualId(FAKE_USER_UIN, EXIST_RITUAL_ID);
        assertNull(card);
    }
}
