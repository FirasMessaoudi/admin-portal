/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantSupplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository for applicant supplication data.
 *
 * @author r.chebbi
 * @since 1.1.0
 **/
public interface ApplicantSupplicationRepository extends JpaRepository<JpaApplicantSupplication,Long> {
    List<JpaApplicantSupplication> findAllByDigitalIdAndDeleted(String digitalId,boolean delete);

    @Modifying
    @Query("update JpaApplicantSupplication supp set supp.deleted =true where supp.id=:id ")
    int disableSuggestedSupplication(@Param("id") long id);

    @Modifying
    @Query("update JpaApplicantSupplication supp set supp.lastSupplicationNumber = 0 where supp.id=:id ")
    int resetSupplicationLastNumber(@Param("id") long id);

    @Modifying
    @Query("update JpaApplicantSupplication supp set supp.lastSupplicationNumber =:last,supp.totalSupplication=:total where supp.id=:id ")
    int updateSupplicationLastNumber(@Param("id") long id,@Param("total") int total,@Param("last") int last);


}
