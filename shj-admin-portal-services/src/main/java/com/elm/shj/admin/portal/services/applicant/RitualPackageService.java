package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackage;
import com.elm.shj.admin.portal.orm.repository.RitualPackageRepository;
import com.elm.shj.admin.portal.services.dto.RitualPackageDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RitualPackageService extends GenericService<JpaRitualPackage, RitualPackageDto, Long> {

    private final RitualPackageRepository ritualPackageRepository;

    public boolean existRitualPackageByReferenceNumber(String referenceNumber) {
        log.info("RitualPackageService ::: Start existRitualPackageByReferenceNumber ::: referenceNumber: {}", referenceNumber);
        Optional<JpaRitualPackage> ritualPackageOptional = ritualPackageRepository.findByReferenceNumber(referenceNumber);
        log.info("RitualPackageService ::: Finish existRitualPackageByReferenceNumber ::: isExist: {}", ritualPackageOptional.isPresent());
        return ritualPackageOptional.isPresent();
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public RitualPackageDto findRitualPackageByReferenceNumber(String referenceNumber) {
        log.info("RitualPackageService ::: Start findRitualPackageByReferenceNumber ::: referenceNumber: {}", referenceNumber);
        Optional<JpaRitualPackage> ritualPackageOptional = ritualPackageRepository.findByReferenceNumber(referenceNumber);
        if (ritualPackageOptional.isPresent()) {
            JpaRitualPackage ritualPackage = ritualPackageOptional.get();

            ritualPackage.getPackageTransportations().size();
            ritualPackage.getPackageHousings().size();
            if (CollectionUtils.isNotEmpty(ritualPackage.getPackageHousings())) {
                ritualPackage.getPackageHousings().forEach(ph -> ph.getPackageCatering().size());
            }
            log.info("RitualPackageService ::: Finish findRitualPackageByReferenceNumber ::: startDate: {}, endDate: {}", ritualPackage.getStartDate(), ritualPackage.getEndDate());
            return getMapper().fromEntity(ritualPackage, mappingContext);
        }
        log.info("RitualPackageService ::: Finish findRitualPackageByReferenceNumber ::: not found and return null");
        return null;
    }

    public RitualPackageDto findRitualPackageByCompanyRitualSeasonId(long companyRitualSeasonId) {
        log.info("RitualPackageService ::: Start findRitualPackageByCompanyRitualSeasonId ::: companyRitualSeasonId: {}", companyRitualSeasonId);
        JpaRitualPackage ritualPackage = ritualPackageRepository.findTopByCompanyRitualSeasonIdOrderByStartDateDescCreationDateDesc(companyRitualSeasonId);
        if (ritualPackage == null) {
            log.info("RitualPackageService ::: Finish findRitualPackageByCompanyRitualSeasonId ::: not found and return null");
            return null;

        } else {
            log.info("RitualPackageService ::: Finish findRitualPackageByCompanyRitualSeasonId :::  startDate: {}, endDate: {}", ritualPackage.getStartDate(), ritualPackage.getEndDate());
           return getMapper().fromEntity(ritualPackage, mappingContext);
        }
    }

    public String findPackageReferenceNumber(String companyCode, String typeCode, int year) {
        log.info("RitualPackageService ::: Start findReferenceNumberByTypeCode ::: typeCode: {}", typeCode);
        String referenceNumber = ritualPackageRepository.findReferenceNumberByRitualSeason(companyCode, typeCode, year);
        log.info("RitualPackageService ::: Finish findReferenceNumberByTypeCode ::: typeCode: {}", typeCode);
        return referenceNumber;
    }

    public RitualPackageDto findByCodeAndRitual(String referenceCode, String typeCode, int year) {
        log.info("RitualPackageService ::: Start findByCodeAndRitual ::: referenceCode: {}", referenceCode);
        Optional<JpaRitualPackage> ritualPackage = ritualPackageRepository.findByReferenceNumberAndRitual(referenceCode, typeCode, year);
        if (ritualPackage.isPresent()) {
            log.info("RitualPackageService ::: Finish findByCodeAndRitual ::: startDate: {}, endDate: {}", ritualPackage.get().getStartDate(), ritualPackage.get().getEndDate());
            return getMapper().fromEntity(ritualPackage.get(), mappingContext);
        }
        log.info("RitualPackageService ::: Finish findByCodeAndRitual ::: not found and return null");
        return null;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Long findIdByReferenceAndRitualTypeAndSeason(String referenceCode, String typeCode, int year) {
        log.info("Start findIdByReferenceAndRitualTypeAndSeason for {} reference number", referenceCode);
        Long ritualPackageId = ritualPackageRepository.findIdByReferenceNumberAndRitualTypeAndSeason(referenceCode, typeCode, year);
        log.info("Finish findIdByReferenceAndRitualTypeAndSeason and found {} ritual package id.", ritualPackageId);
        return ritualPackageId;
    }

}
