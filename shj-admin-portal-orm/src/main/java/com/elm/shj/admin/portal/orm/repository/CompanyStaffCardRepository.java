/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Company Staff Card Table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyStaffCardRepository extends JpaRepository<JpaCompanyStaffCard, Long> {
    List<JpaCompanyStaffCard> findAllByCompanyStaffSuin(String suin);

    //check those two
    List<JpaCompanyStaffCard> findAllByCompanyStaffSuinAndStatusCodeInAndCompanyRitualSeasonCompanyCodeAndCompanyRitualSeasonRitualSeasonRitualTypeCode(String suin, List<String> statusCodes, String companyCode, String ritualTypeCode);

    List<JpaCompanyStaffCard> findAllByCompanyStaffSuinAndStatusCodeInAndCompanyRitualSeasonCompanyCodeNotOrCompanyRitualSeasonRitualSeasonRitualTypeCodeNot(String suin, List<String> statusCodes, String companyCode, String ritualTypeCode);


}
