package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackageBasicWithDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for Ritual Package Basic with Details
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
public interface RitualPackageBasicWithDetailsRepository extends JpaRepository<JpaRitualPackageBasicWithDetails, Long> {

    Optional<JpaRitualPackageBasicWithDetails> findByReferenceNumber(String referenceNumber);

    @Query("SELECT rp FROM JpaRitualPackageBasicWithDetails rp WHERE rp.referenceNumber = :referenceNumber and rp.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualTypeCode and rp.companyRitualSeason.ritualSeason.seasonYear = :seasonYear")
    Optional<JpaRitualPackageBasicWithDetails> findIdByReferenceNumberAndRitualTypeAndSeason(@Param("referenceNumber") String referenceNumber, @Param("ritualTypeCode") String ritualTypeCode, @Param("seasonYear") int seasonYear);
}
