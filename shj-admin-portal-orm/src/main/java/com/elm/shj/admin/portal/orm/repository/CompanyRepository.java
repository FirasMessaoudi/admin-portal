/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompany;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for company data.
 *
 * @author salzoubi
 * @since 1.1.0
 **/
public interface CompanyRepository extends JpaRepository<JpaCompany, Long> {

    JpaCompany findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsRitualPackagesApplicantPackagesApplicantUin(long companyRitualSeasonsId, long applicantUin);
}
