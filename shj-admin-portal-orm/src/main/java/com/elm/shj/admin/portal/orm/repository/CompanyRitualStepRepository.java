package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRitualStepRepository extends JpaRepository<JpaCompanyRitualStep, Long> {
    List<JpaCompanyRitualStep> findByApplicantGroupGroupApplicantListsApplicantUinAndApplicantGroupCompanyRitualSeasonIdOrderByStepIndexAsc(String applicantUin, long id);
}
