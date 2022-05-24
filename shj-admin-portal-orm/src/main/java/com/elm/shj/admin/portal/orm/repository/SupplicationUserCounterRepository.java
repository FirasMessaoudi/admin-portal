/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaSupplicationUserCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for supplication user counter data.
 *
 * @author r.chebbi
 * @since 1.1.0
 **/
public interface SupplicationUserCounterRepository extends JpaRepository<JpaSupplicationUserCounter,Long> {
    List<JpaSupplicationUserCounter> findAllByDigitalId(String digitalId);

    Optional<JpaSupplicationUserCounter> findByCodeAndDigitalId(String code,String digitalId);

    @Modifying
    @Query("update JpaSupplicationUserCounter supp set supp.supplicationLastCount = 0 where supp.id=:id ")
    int resetSupplicationCounter(@Param("id") long id);

    @Modifying
    @Query("update JpaSupplicationUserCounter supp set supp.supplicationLastCount =:last,supp.supplicationTotalCount=:total where supp.id=:id ")
    int updateSupplicationCounter(@Param("id") long id, @Param("total") int total, @Param("last") int last);


}
