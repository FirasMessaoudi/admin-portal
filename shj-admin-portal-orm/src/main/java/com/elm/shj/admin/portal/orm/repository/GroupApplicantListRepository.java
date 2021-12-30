package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaGroupApplicantList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupApplicantListRepository extends JpaRepository<JpaGroupApplicantList, Long> {

    Optional<JpaGroupApplicantList> findByApplicantUinAndApplicantGroupReferenceNumber(String applicantUin, String applicantGroupReferenceNumber);

}
