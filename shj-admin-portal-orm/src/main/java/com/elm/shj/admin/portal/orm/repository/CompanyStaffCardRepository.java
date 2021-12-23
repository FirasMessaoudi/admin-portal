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
    List<JpaCompanyStaffCard> findAllByCompanyStaffDigitalIdSuin(String suin);

    JpaCompanyStaffCard findByCompanyStaffDigitalIdSuinAndStatusCode(String suin, String statusCode);
    //check those two
    List<JpaCompanyStaffCard> findAllByCompanyStaffDigitalIdSuinAndStatusCodeInAndCompanyRitualSeasonCompanyCodeAndCompanyRitualSeasonRitualSeasonRitualTypeCode(String suin, List<String> statusCodes, String companyCode, String ritualTypeCode);

    List<JpaCompanyStaffCard> findAllByCompanyStaffDigitalIdSuinAndStatusCodeInAndCompanyRitualSeasonCompanyCodeNotOrCompanyRitualSeasonRitualSeasonRitualTypeCodeNot(String suin, List<String> statusCodes, String companyCode, String ritualTypeCode);


}
