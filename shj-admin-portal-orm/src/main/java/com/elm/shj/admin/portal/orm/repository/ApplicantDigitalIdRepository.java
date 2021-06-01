/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantDigitalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for ApplicantDigitalId Table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface ApplicantDigitalIdRepository extends JpaRepository<JpaApplicantDigitalId, Long> {

    @Query("select substring(d.uin, 7, 13) from JpaApplicantDigitalId d where d.uin like :uin order by substring(d.uin, 7, 13) desc")
    String fetchUinByUinLike(@Param("uin") String uin);

}
