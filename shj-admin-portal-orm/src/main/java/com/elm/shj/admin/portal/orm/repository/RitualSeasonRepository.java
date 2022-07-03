package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository for company season package data.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public interface RitualSeasonRepository extends JpaRepository<JpaRitualSeason, Long> {
    @Query("select distinct season.seasonYear from JpaRitualSeason season   ")
    List<Integer> listRitualSeasonYears();

    Optional<JpaRitualSeason> findByRitualTypeCodeAndSeasonYear(String ritualTypeCode, int season);

    boolean existsByRitualTypeCodeAndSeasonYear(String ritualTypeCode, int seasonYear);
}
