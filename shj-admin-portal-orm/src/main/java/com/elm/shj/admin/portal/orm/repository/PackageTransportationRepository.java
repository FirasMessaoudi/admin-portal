package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantMainData;
import com.elm.shj.admin.portal.orm.entity.JpaPackageTransportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Package Transportation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface PackageTransportationRepository extends JpaRepository<JpaPackageTransportation, Long> {


}
