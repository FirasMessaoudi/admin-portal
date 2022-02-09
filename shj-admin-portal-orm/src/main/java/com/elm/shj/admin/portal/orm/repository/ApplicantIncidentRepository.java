/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import com.elm.shj.admin.portal.orm.entity.LocationVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for applicant incident table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
public interface ApplicantIncidentRepository extends JpaRepository<JpaApplicantIncident, Long>, JpaSpecificationExecutor<JpaApplicantIncident> {
    List<JpaApplicantIncident> findByApplicantRitualId(long applicantRitualId);

    @Modifying
    @Query("update JpaApplicantIncident incident set incident.statusCode = :status, " +
            "incident.resolutionComment = :resolutionComment, incident.updateDate = current_timestamp where incident.id =:incidentId")
    void update(@Param("incidentId") long incidentId, @Param("resolutionComment") String resolutionComment,@Param("status") String status);


    @Query("SELECT COUNT(a) FROM JpaApplicantIncident a where a.statusCode = 'UNDER_PROCESSING'")
    long countAllUnResolvedIncidents();

    @Query("SELECT COUNT(a) FROM JpaApplicantIncident a where a.statusCode IN ('RESOLVED', 'CLOSED')")
    long countAllResolvedIncidents();

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo(c.labelAr, 0, COUNT(ai),'') " +
            "FROM JpaApplicantIncident ai JOIN ai.applicantRitual ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.company c WHERE  c.labelAr is NOT NULL GROUP BY c.labelAr ORDER BY COUNT(c.labelAr) DESC")
    Page<CountVo> findCompaniesWithMaxIncidents(Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo(c.labelAr, 0, COUNT(ai),'') " +
            "FROM JpaApplicantIncident ai JOIN ai.applicantRitual ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.company c WHERE  c.labelAr is NOT NULL GROUP BY c.labelAr ORDER BY COUNT(c.labelAr)")
    Page<CountVo> findCompaniesWithMinIncidents(Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.LocationVo(i.locationLat, i.locationLng) " +
            "FROM JpaApplicantIncident i JOIN i.applicantRitual ar JOIN ar.applicantPackage ap " +
            "JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs  where rs.seasonYear = :seasonYear " +
            "and rs.ritualTypeCode IN (:ritualTypeCodeList)")
    List<LocationVo> getIncidentsLocationsBySeasonAndRitualType(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);


}
