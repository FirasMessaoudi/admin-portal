package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPermitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicantPermitCardRepository extends JpaRepository<JpaApplicantPermitCard, Long> {

}
