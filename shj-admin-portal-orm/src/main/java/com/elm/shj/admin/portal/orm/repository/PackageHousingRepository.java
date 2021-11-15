package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPackageHousing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository for Package Housing
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface PackageHousingRepository extends JpaRepository<JpaPackageHousing, Long> {

    @Query("SELECT j FROM JpaPackageHousing j WHERE j.typeCode = 'CAMP'")
    List<JpaPackageHousing> findAllCamps();
}
