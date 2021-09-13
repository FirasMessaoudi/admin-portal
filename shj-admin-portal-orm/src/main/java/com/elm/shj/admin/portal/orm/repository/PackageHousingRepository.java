package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPackageHousing;
import com.elm.shj.admin.portal.orm.entity.JpaPackageTransportation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Package Housing
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface PackageHousingRepository extends JpaRepository<JpaPackageHousing, Long> {


}
