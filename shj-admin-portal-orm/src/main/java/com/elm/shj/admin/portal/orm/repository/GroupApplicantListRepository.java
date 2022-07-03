package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.ApplicantVo;
import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupApplicantListRepository extends JpaRepository<JpaGroupApplicantList, Long> {

    Optional<JpaGroupApplicantList> findByApplicantUinAndApplicantGroupReferenceNumber(String applicantUin, String applicantGroupReferenceNumber);

    Optional<JpaGroupApplicantList> findTopByApplicantUinAndApplicantGroupIdOrderByCreationDateDesc(String applicantUin, long id);

    Optional<JpaGroupApplicantList> findTopByApplicantUinOrderByCreationDateDesc(String uin);

    List<JpaGroupApplicantList> findByApplicantGroupGroupLeaderDigitalIdsSuin(String suin);

    long countByApplicantGroupId(long id);


    @Query(value = "SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantVo(a.fullNameAr, a.fullNameEn, adi.uin, a.photo,a.idNumber,a.passportNumber) From JpaGroupApplicantList g " +
            "JOIN g.applicantGroup ag JOIN ag.groupLeader gl JOIN gl.digitalIds di JOIN JpaApplicantDigitalId adi ON adi.uin = g.applicantUin JOIN JpaApplicant a ON adi.applicantId = a.id WHERE di.suin = :suin "
    )
    List<ApplicantVo> findApplicantDetailsWithoutLocationByGroupeLeaderSuin(@Param("suin") String suin);

    @Query(value = "SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantVo(adi.uin, l.latitude , l.longitude) From JpaGroupApplicantList g " +
            "JOIN g.applicantGroup ag JOIN ag.groupLeader gl JOIN gl.digitalIds di JOIN JpaApplicantDigitalId adi ON adi.uin = g.applicantUin JOIN JpaApplicant a ON adi.applicantId = a.id LEFT JOIN JpaUserLocation l ON l.userId = adi.uin WHERE di.suin = :suin " +
            "AND ( l IS NULL or l.gpsTime = (SELECT MAX(ul.gpsTime) FROM JpaUserLocation ul WHERE ul.userId  = adi.uin))")
    List<ApplicantVo> findApplicantLocationsByGroupeLeaderSuin(@Param("suin") String suin);

    @Query("select distinct (g.applicantGroup.companyRitualSeason.company.establishmentRefCode) from JpaGroupApplicantList g where g.applicantUin = :uin ")
    Integer findApplicantEstablishment(@Param("uin") String uin);

}
