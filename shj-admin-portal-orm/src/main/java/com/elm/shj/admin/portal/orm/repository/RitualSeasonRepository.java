package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualSeason;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for company season package data.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public interface RitualSeasonRepository extends JpaRepository<JpaRitualSeason, Long> {
}
