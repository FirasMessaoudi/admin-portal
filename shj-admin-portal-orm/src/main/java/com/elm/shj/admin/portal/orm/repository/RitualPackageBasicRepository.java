package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackageBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for Ritual Package Basic
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
public interface RitualPackageBasicRepository extends JpaRepository<JpaRitualPackageBasic, Long> {

    Optional<JpaRitualPackageBasic> findByReferenceNumber(String referenceNumber);

    @Query("SELECT rp.id FROM JpaRitualPackage rp WHERE rp.referenceNumber = :referenceNumber and rp.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualTypeCode and rp.companyRitualSeason.ritualSeason.seasonYear = :seasonYear")
    Long findIdByReferenceNumberAndRitualTypeAndSeason(@Param("referenceNumber") String referenceNumber, @Param("ritualTypeCode") String ritualTypeCode, @Param("seasonYear") int seasonYear);

}
