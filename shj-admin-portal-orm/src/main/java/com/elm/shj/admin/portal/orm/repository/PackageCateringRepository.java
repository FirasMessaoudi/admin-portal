package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPackageCatering;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for company season package data.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public interface PackageCateringRepository extends JpaRepository<JpaPackageCatering, Long> {
}
