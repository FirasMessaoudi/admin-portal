/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantIncident;
import com.elm.shj.admin.portal.orm.entity.LocalizedCountVo;
import com.elm.shj.admin.portal.orm.entity.LocationVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
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
    void update(@Param("incidentId") long incidentId, @Param("resolutionComment") String resolutionComment, @Param("status") String status);

    @Modifying
    @Query("update JpaApplicantIncident incident set incident.statusCode = :status, " +
            "incident.resolutionComment = :resolutionComment, incident.crmStatusUpdated = true, incident.updateDate = current_timestamp where incident.id =:incidentId")
    void updateByCrm(@Param("incidentId") long incidentId, @Param("resolutionComment") String resolutionComment, @Param("status") String status);


    @Query("SELECT COUNT(ai) FROM JpaApplicantIncident ai JOIN ai.applicantRitual ar " +
            "JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs " +
            "where ai.statusCode = 'UNDER_PROCESSING' AND rs.seasonYear= :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList)")
    long countAllUnResolvedIncidents(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT COUNT(ai) FROM JpaApplicantIncident ai JOIN ai.applicantRitual ar " +
            "JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs " +
            "where ai.statusCode IN ('RESOLVED', 'CLOSED') AND rs.seasonYear= :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList)")
    long countAllResolvedIncidents(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.LocalizedCountVo(c.labelAr, c.labelEn, COUNT(ai)) " +
            "FROM JpaApplicantIncident ai JOIN ai.applicantRitual ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.company c JOIN crs.ritualSeason rs " +
            "WHERE c.labelAr is NOT NULL AND rs.seasonYear= :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) GROUP BY c.labelAr, c.labelEn " +
            "ORDER BY COUNT(c.labelAr) DESC")
    Page<LocalizedCountVo> findCompaniesWithMaxIncidents(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList, Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.LocalizedCountVo(c.labelAr, c.labelEn, COUNT(ai)) " +
            "FROM JpaApplicantIncident ai JOIN ai.applicantRitual ar JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.company c JOIN crs.ritualSeason rs " +
            "WHERE c.labelAr is NOT NULL AND rs.seasonYear= :seasonYear AND rs.ritualTypeCode IN (:ritualTypeCodeList) GROUP BY c.labelAr, c.labelEn " +
            "ORDER BY COUNT(c.labelAr)")
    Page<LocalizedCountVo> findCompaniesWithMinIncidents(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList, Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.LocationVo(i.locationLat, i.locationLng) " +
            "FROM JpaApplicantIncident i JOIN i.applicantRitual ar JOIN ar.applicantPackage ap " +
            "JOIN ap.ritualPackage rp JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs where rs.seasonYear = :seasonYear " +
            "and rs.ritualTypeCode IN (:ritualTypeCodeList)")
    List<LocationVo> getIncidentsLocationsBySeasonAndRitualType(@Param("seasonYear") int seasonYear, @Param("ritualTypeCodeList") List<String> ritualTypeCodeList);

    @Query("SELECT ai FROM JpaApplicantIncident ai JOIN ai.applicantRitual ar " +
            "JOIN ar.applicantPackage ap JOIN ap.ritualPackage rp " +
            "JOIN rp.companyRitualSeason crs JOIN crs.ritualSeason rs " +
            "where rs.seasonYear= :seasonYear AND ai.areaCode IS NOT NULL")
    List<JpaApplicantIncident> findAllByCurrentSeason(@Param("seasonYear") int seasonYear);

    @Query("SELECT new com.elm.shj.admin.portal.orm.entity.ApplicantComplaintVo(c.id,c.referenceNumber,c.typeCode,c.statusCode, c.city, c.resolutionComment,c.crmStatusUpdated,c.crmTicketNumber, c.description,c.locationLat, c.locationLng,c.mobileNumber,c.creationDate, att.id,a.fullNameAr,a.fullNameEn,a.fullNameOrigin, a.idNumber, a.passportNumber,a.dateOfBirthHijri, a.dateOfBirthGregorian,a.gender,a.nationalityCode,ac.email,ac.localMobileNumber,ac.intlMobileNumber,ac.countryCode, di.uin) " +
            "FROM JpaApplicantIncident c JOIN c.applicantRitual ar JOIN ar.applicant a JOIN  a.digitalIds di JOIN a.contacts ac LEFT JOIN c.incidentAttachments att " +
            "WHERE c.crmTicketNumber is null OR c.crmStatusUpdated = false")
    List<ApplicantComplaintVo> findAllCrm();

}
