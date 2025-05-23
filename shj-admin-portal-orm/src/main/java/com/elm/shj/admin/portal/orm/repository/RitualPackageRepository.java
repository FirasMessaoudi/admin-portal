package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for Ritual Package
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public interface RitualPackageRepository extends JpaRepository<JpaRitualPackage, Long> {

    Optional<JpaRitualPackage> findByReferenceNumber(String referenceNumber);

    @Query("SELECT rp.id FROM JpaRitualPackage rp WHERE rp.referenceNumber = :referenceNumber and rp.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualTypeCode and rp.companyRitualSeason.ritualSeason.seasonYear = :seasonYear")
    Long findIdByReferenceNumberAndRitualTypeAndSeason(@Param("referenceNumber") String referenceNumber, @Param("ritualTypeCode") String ritualTypeCode, @Param("seasonYear") int seasonYear);

    JpaRitualPackage findTopByCompanyRitualSeasonIdOrderByStartDateDescCreationDateDesc(long companyRitualSeason);

    @Query("select rp.referenceNumber from JpaRitualPackage rp where rp.companyRitualSeason.company.code = :companyCode and rp.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualTypeCode and rp.companyRitualSeason.ritualSeason.seasonYear = :seasonYear")
    String findReferenceNumberByRitualSeason(@Param("companyCode") String companyCode, @Param("ritualTypeCode") String ritualTypeCode, @Param("seasonYear") int seasonYear);

    @Query("select rp from JpaRitualPackage rp where rp.referenceNumber = :referenceNumber and rp.companyRitualSeason.ritualSeason.ritualTypeCode = :ritualTypeCode and rp.companyRitualSeason.ritualSeason.seasonYear = :seasonYear")
    Optional<JpaRitualPackage> findByReferenceNumberAndRitual(@Param("referenceNumber") String referenceNumber, @Param("ritualTypeCode") String ritualTypeCode, @Param("seasonYear") int seasonYear);
}
