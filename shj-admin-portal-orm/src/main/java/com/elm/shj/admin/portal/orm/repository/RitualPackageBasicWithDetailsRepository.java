package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackageBasicWithDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Ritual Package Basic with Details
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
public interface RitualPackageBasicWithDetailsRepository extends JpaRepository<JpaRitualPackageBasicWithDetails, Long> {

    Optional<JpaRitualPackageBasicWithDetails> findByReferenceNumber(String referenceNumber);
}
