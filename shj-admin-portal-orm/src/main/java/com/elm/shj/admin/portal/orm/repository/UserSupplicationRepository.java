/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaUserSupplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository for user supplication data.
 *
 * @author r.chebbi
 * @since 1.1.0
 **/
public interface UserSupplicationRepository extends JpaRepository<JpaUserSupplication,Long> {
    List<JpaUserSupplication> findAllByDigitalIdAndDeleted(String digitalId, boolean delete);

    @Modifying
    @Query("update JpaUserSupplication  supp set supp.deleted =true where supp.id=:id ")
    int disableUserSupplication(@Param("id") long id);

}
