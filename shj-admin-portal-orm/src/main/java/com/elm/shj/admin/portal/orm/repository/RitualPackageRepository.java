package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPackageHousing;
import com.elm.shj.admin.portal.orm.entity.JpaRitualPackage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Ritual Package
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface RitualPackageRepository extends JpaRepository<JpaRitualPackage, Long> {


}
