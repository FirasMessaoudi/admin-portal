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

    Optional<JpaGroupApplicantList> findTopByApplicantUinOrderByCreationDateDesc(String uin);

    List<JpaGroupApplicantList> findByApplicantGroupGroupLeaderDigitalIdsSuin(String suin);

    @Query(value = "SELECT NEW com.elm.shj.admin.portal.orm.entity.ApplicantVo(a.fullNameAr, a.fullNameEn, adi.uin, a.photo, l.latitude , l.longitude,a.idNumber,a.passportNumber) From JpaGroupApplicantList g " +
            "JOIN g.applicantGroup ag JOIN ag.groupLeader gl JOIN gl.digitalIds di JOIN JpaApplicantDigitalId adi ON adi.uin = g.applicantUin JOIN JpaApplicant a ON adi.applicantId = a.id LEFT JOIN JpaUserLocation l ON l.userId = adi.uin WHERE di.suin = :suin " +
            "AND l.gpsTime = (SELECT MAX(ul.gpsTime) FROM JpaUserLocation ul WHERE ul.userId  = adi.uin)")
    List<ApplicantVo> findApplicantDetailsWithLocationByGroupeLeaderSuin(@Param("suin") String suin);

}
