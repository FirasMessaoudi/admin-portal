package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStep;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStepLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CompanyRitualStepRepository extends JpaRepository<JpaCompanyRitualStep, Long> {
    List<JpaCompanyRitualStep> findByApplicantGroupIdOrderByStepIndexAsc(long id);
    List<JpaCompanyRitualStep> findByApplicantGroupCompanyRitualSeasonId(long id);


    @Override
    Optional<JpaCompanyRitualStep> findById(Long aLong);

    Optional <JpaCompanyRitualStep> findByStepCodeAndApplicantGroupId(String stepCode, long groupId);

    @Modifying
    @Query("update JpaCompanyRitualStep set time = :time where id= :id")
    int updateGroupRitualStep(@Param("time") Date time, @Param("id") long id);
}
