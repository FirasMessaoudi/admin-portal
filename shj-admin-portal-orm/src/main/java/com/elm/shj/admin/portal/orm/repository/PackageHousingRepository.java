package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.CountVo;
import com.elm.shj.admin.portal.orm.entity.JpaPackageHousing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT h FROM JpaPackageHousing h JOIN h.ritualPackage r JOIN r.companyRitualSeason c JOIN r.applicantPackages a" +
            " WHERE c.id = :companyRitualSeasonsId AND a.applicantUin =:uin AND CURRENT_TIMESTAMP BETWEEN h.validityStart AND h.validityEnd")
    JpaPackageHousing findPackageHousing(@Param("companyRitualSeasonsId") long companyRitualSeasonsId, @Param("uin") long uin);

    List<JpaPackageHousing> findByRitualPackageId(long id);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo(ph.locationNameAr, 0, COUNT(ph.locationNameAr), '') " +
            "FROM JpaApplicantPackage ap JOIN ap.ritualPackage rp JOIN rp.packageHousings ph " +
            "WHERE ph.typeCode = 'CAMP' " +
            "GROUP BY ph.locationNameAr ORDER BY COUNT(ph.locationNameAr) DESC")
    Page<CountVo> findCampsWithMaxApplicantsByHijriSeason(@Param("hijriSeason") int hijriSeason, Pageable pageable);

    @Query("SELECT NEW com.elm.shj.admin.portal.orm.entity.CountVo(ph.locationNameAr, 0, COUNT(ph.locationNameAr), '') " +
            "FROM JpaApplicantPackage ap JOIN ap.ritualPackage rp JOIN rp.packageHousings ph " +
            "WHERE ph.typeCode = 'CAMP' " +
            "GROUP BY ph.locationNameAr ORDER BY COUNT(ph.locationNameAr)")
    Page<CountVo> findCampsWithMinApplicantsByHijriSeason(@Param("hijriSeason") int hijriSeason, Pageable pageable);

}
