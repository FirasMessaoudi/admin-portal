package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaPackageHousing;
import com.elm.shj.admin.portal.orm.entity.JpaPackageTransportation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Package Transportation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface PackageTransportationRepository extends JpaRepository<JpaPackageTransportation, Long> {


    List<JpaPackageTransportation> findByRitualPackageId(long id);

    Optional<JpaPackageTransportation> findTopByRitualPackageIdAndTypeCode(long id, String typeCode);

    List<JpaPackageTransportation> findAllByRitualPackageIdAndTypeCode(long id, String typeCode);
}
