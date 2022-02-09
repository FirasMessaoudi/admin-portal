/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for CompanyStaffDigitalId Table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
public interface CompanyStaffDigitalIdRepository extends JpaRepository<JpaCompanyStaffDigitalId, Long> {

    @Query(value = "select c from JpaCompanyStaffDigitalId c where " +
            "(c.companyStaff.id = :staffId and c.seasonYear = :seasonYear and c.statusCode=:statusCode )"
    )
    Optional<JpaCompanyStaffDigitalId> findByBasicInfo(@Param("staffId") long staffId, @Param("seasonYear") int seasonYear, @Param("statusCode") String statusCode);


    @Query("select substring(sd.suin,6, 6) from JpaCompanyStaffDigitalId sd where sd.suin like :suin% order by substring(sd.suin, 6, 6) desc")
    List<String> fetchSuinBySuinLike(@Param("suin") String suin);

    List<JpaCompanyStaffDigitalId> findBySuinIsNull();

    Optional<JpaCompanyStaffDigitalId> findBySuinAndSeasonYearAndStatusCode(String suin, int seasonYear, String statusCode);

    List<JpaCompanyStaffDigitalId> findByStatusCodeAndCompanyStaffCardsCompanyRitualSeasonSeasonEndLessThan(String status, long hijriDate);

    @Query("select c.statusCode from JpaCompanyStaffDigitalId  c where c.suin = :suin")
    String findStaffSuinStatusCode(@Param("suin") String suin);

    ;
}
