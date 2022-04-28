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

    @Query("SELECT rp.id FROM JpaRitualPackage rp WHERE rp.referenceNumber = :referenceNumber")
    Long findIdByReferenceNumber(@Param("referenceNumber") String referenceNumber);

     JpaRitualPackage findTopByCompanyRitualSeasonIdOrderByStartDateDescCreationDateDesc(long companyRitualSeason);
     Optional<JpaRitualPackage> findByCompanyRitualSeasonCompanyStaffCardsCompanyStaffDigitalIdSuin(String digitalId);
}
