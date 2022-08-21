package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.shj.admin.portal.orm.entity.ApplicantRitualPackageVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackage;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantPackageService extends GenericService<JpaApplicantPackage, ApplicantPackageDto, Long> {

    private final ApplicantPackageRepository applicantPackageRepository;
    private final RitualPackageService ritualPackageService;
    private final PackageHousingService packageHousingService;
    private final ApplicantPackageCateringService applicantPackageCateringService;

    //TODO not used
    @Transactional(readOnly = true)
    public ApplicantPackageDto findApplicantPackageByUinAndReferenceNumber(Long applicantUin, String packageReferenceNumber) {
        log.info("Start findApplicantPackageByUinAndReferenceNumber ::: applicantUin:{} ", applicantUin);
        Optional<JpaApplicantPackage> applicantPackageOptional = applicantPackageRepository.findByApplicantUinAndRitualPackageReferenceNumber(applicantUin, packageReferenceNumber);
        if (applicantPackageOptional.isPresent()) {
            JpaApplicantPackage applicantPackage = applicantPackageOptional.get();
            applicantPackage.getApplicantPackageTransportations().size();
            CycleAvoidingMappingContext mappingContext = new CycleAvoidingMappingContext();
            log.info("Finish findApplicantPackageByUinAndReferenceNumber ::: applicantUin:{} ", applicantUin);
            return getMapper().fromEntity(applicantPackage, mappingContext);
        }
        log.info("Finish findApplicantPackageByUinAndReferenceNumber ::: not found with applicantUin:{} ", applicantUin);
        return null;
    }

    public List<ApplicantRitualPackageVo> findApplicantRitualPackageByUin(long applicantUin) {
        log.info("Start findApplicantRitualPackageByUin ::: applicantUin:{} ", applicantUin);
        List<ApplicantRitualPackageVo> applicantRitualPackageByUin = applicantPackageRepository.findApplicantRitualPackageByUin(applicantUin);
        log.info("Finish findApplicantRitualPackageByUin ::: applicantRitualPackageListSize:{} ", applicantRitualPackageByUin.size());
        return applicantRitualPackageByUin;

    }

    public ApplicantRitualPackageVo findLatestApplicantRitualPackage(long applicantUin) {
        log.info("Start findLatestApplicantRitualPackage ::: applicantUin:{} ", applicantUin);
        List<ApplicantRitualPackageVo> applicantRitualSeasons = findApplicantRitualPackageByUin(applicantUin);
        if (applicantRitualSeasons.size() > 0) {
            log.info("Finish findLatestApplicantRitualPackage ::: ApplicantPackageId:{} ", applicantRitualSeasons.get(0).getApplicantPackageId());
            return applicantRitualSeasons.get(0);
        }
        log.info("Finish findLatestApplicantRitualPackage ::: not found with applicantUin:{} ", applicantUin);
        return null;
    }

    public ApplicantPackageDto findByIdAndApplicantUin(Long id, Long applicantUin){
        log.info("Start findByIdAndApplicantUin ::: id:{}, applicantUin:{} ", id, applicantUin);
        Optional<JpaApplicantPackage>  applicantPackage = applicantPackageRepository.findByIdAndApplicantUin(id,applicantUin);
        if(applicantPackage.isPresent()){
            log.info("Finish findByIdAndApplicantUin ::: applicantPackageId:{}",applicantPackage.get().getId());
            return getMapper().fromEntity(applicantPackage.get(),mappingContext);
        }
        log.info("Finish findByIdAndApplicantUin ::: not found with id:{}, applicantUin:{} ", id, applicantUin);
        return  null;
    }

    /**
     * Find the latest applicant package id based on applicant UIN
     * @param applicantUin
     * @return
     */
    public Long findLatestIdByApplicantUIN(String applicantUin) {
        log.info("Start findLatestIdByApplicantUIN ::: applicantUin:{} ", applicantUin);
        try {
            Page<Long> applicantPackageIdPage = applicantPackageRepository.findLastIdByApplicantUin(Long.parseLong(applicantUin), PageRequest.of(0, 1));
            if (applicantPackageIdPage == null) {
                log.info("Finish findLatestIdByApplicantUIN ::: not found with applicantUin:{} ", applicantUin);
                return null;
            }
            log.info("Finish findLatestIdByApplicantUIN ::: applicantPackageIdPage content:{} ", applicantPackageIdPage.getContent().get(0));
            return applicantPackageIdPage.getContent().get(0);
        } catch (Exception e) {
            log.error("Finish findLatestIdByApplicantUIN ::: not found with applicantUin:{} and throw Exception: {}",applicantUin, e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param uin
     * @param applicantPackageId
     * @return
     */
    public List<ApplicantPackageCateringDto> findPackageCateringFromRitualPackage(long uin, long applicantPackageId) {
        log.info("Start findPackageCateringFromRitualPackage ::: applicantUin:{}, applicantPackageId:{} ", uin, applicantPackageId);
        List<ApplicantPackageCateringDto> applicantPackageCateringDtos = new ArrayList<>();
        ApplicantPackageDto applicantPackage = findOne(applicantPackageId);
        List<PackageHousingDto> packageHousingDtos = packageHousingService.findByRitualPackageId(applicantPackage.getRitualPackage().getId());

        Optional<PackageHousingDto> packageHousingDto = packageHousingDtos.stream()
                .filter(p -> LocalDateTime.now().isAfter(DateUtils.convertToLocalDate(p.getValidityStart()))
                        && LocalDateTime.now().isBefore(DateUtils.convertToLocalDate(p.getValidityEnd()))).findFirst();
        if (!packageHousingDto.isPresent()) {
            log.info("Finish findPackageCateringFromRitualPackage ::: not found  with applicantPackageCateringDtosListSize: {}", applicantPackageCateringDtos.size());
            return applicantPackageCateringDtos;
        }

        //today's catering
        applicantPackageCateringDtos = applicantPackageCateringService.findAllByApplicantPackageApplicantUinAndPackageCateringPackageHousingId(uin, packageHousingDto.get().getId());
        log.info("Finish findPackageCateringFromRitualPackage ::: with applicantPackageCateringDtosListSize: {}", applicantPackageCateringDtos.size());

        return applicantPackageCateringDtos.stream().sorted(Comparator.comparing(o -> o.getPackageCatering().getMealTime())).collect(Collectors.toList());
    }

    /**
     * Create applicant package with its details; transportation, housing and catering.
     *
     * @param packageReferenceNumber
     * @param applicantUin
     * @return saved applicant package
     */
    public ApplicantPackageDto createApplicantPackage(String packageReferenceNumber, Long applicantUin, String busNumber, String seatNumber) {
        log.info("Start createApplicantPackage ::: packageReferenceNumber: {},  applicantUin: {},  busNumber: {},  seatNumber: {}", packageReferenceNumber,  applicantUin,  busNumber,  seatNumber);
        List<ApplicantPackageHousingDto> applicantPackageHousings;
        List<ApplicantPackageCateringDto> applicantPackageCaterings = new ArrayList<>();
        List<ApplicantPackageTransportationDto> applicantPackageTransportations = new ArrayList<>();

        RitualPackageDto ritualPackage = ritualPackageService.findRitualPackageByReferenceNumber(packageReferenceNumber);
        if (ritualPackage == null) {
            log.info("Finish createApplicantPackage ::: ritualPackage not found with packageReferenceNumber: {},  applicantUin: {},  busNumber: {},  seatNumber: {}", packageReferenceNumber,  applicantUin,  busNumber,  seatNumber);
            return null;
        }

        // initiate applicant package
        ApplicantPackageDto applicantPackage = ApplicantPackageDto.builder().applicantUin(applicantUin)
                .ritualPackage(RitualPackageDto.builder().id(ritualPackage.getId()).build())
                .startDate(ritualPackage.getStartDate()).endDate(ritualPackage.getEndDate()).build();

        // create applicant package transportation
        if (CollectionUtils.isNotEmpty(ritualPackage.getPackageTransportations())) {
            applicantPackageTransportations = ritualPackage.getPackageTransportations().stream().map(pt ->
                    ApplicantPackageTransportationDto.builder().packageTransportation(PackageTransportationDto.builder().id(pt.getId()).build())
                            .applicantPackage(applicantPackage).seatNumber(seatNumber).vehicleNumber(busNumber).build()
            ).collect(Collectors.toList());
        }
        applicantPackage.setApplicantPackageTransportations(applicantPackageTransportations);

        // create applicant package housings
        if (CollectionUtils.isNotEmpty(ritualPackage.getPackageHousings())) {
            applicantPackageHousings = ritualPackage.getPackageHousings().stream().map(ph ->
                    ApplicantPackageHousingDto.builder().packageHousing(PackageHousingDto.builder().id(ph.getId()).build()).applicantPackage(applicantPackage).build()
            ).collect(Collectors.toList());
            applicantPackage.setApplicantPackageHousings(applicantPackageHousings);

            // create applicant package catering
            ritualPackage.getPackageHousings().forEach(ph -> {
                if (CollectionUtils.isNotEmpty(ph.getPackageCatering())) {
                    applicantPackageCaterings.addAll(ph.getPackageCatering().stream().map(pc ->
                            ApplicantPackageCateringDto.builder().packageCatering(PackageCateringDto.builder().id(pc.getId()).build()).applicantPackage(applicantPackage).build()
                    ).collect(Collectors.toList()));
                }
            });
            applicantPackage.setApplicantPackageCaterings(applicantPackageCaterings);
        }
        ApplicantPackageDto savedApplicantPackage = save(applicantPackage);
        log.info("Finish createApplicantPackage ::: save applicantPackage  savedApplicantPackage StartDate: {}, EndDate: {}", savedApplicantPackage.getStartDate(), savedApplicantPackage.getEndDate());
        return savedApplicantPackage;
    }

    public ApplicantPackageDto findJpaApplicantPackageByApplicantUin(String applicantUin) {
        log.info("Start findJpaApplicantPackageByApplicantUin ::: applicantUin: {}", applicantUin);
        Optional<JpaApplicantPackage> applicantPackage = applicantPackageRepository.findJpaApplicantPackageByApplicantUinAndApplicantRitualApplicantDeletedFalse(Long.parseLong(applicantUin));
        if (applicantPackage.isPresent()) {
            log.info("Finish findJpaApplicantPackageByApplicantUin ::: StartDate: {}, EndDate:{}", applicantPackage.get().getStartDate(), applicantPackage.get().getEndDate());
            return getMapper().fromEntity(applicantPackage.get(), mappingContext);
        }
        log.info("Finish findJpaApplicantPackageByApplicantUin ::: not found with applicantUin: {}", applicantUin);
        return null;
    }

    public ApplicantPackageDto findJpaApplicantPackageByApplicantId(long applicantId) {
        log.info("Start findJpaApplicantPackageByApplicantId ::: applicantId: {}", applicantId);
        Optional<JpaApplicantPackage> applicantPackage = applicantPackageRepository.findTopByApplicantRitualApplicantIdAndApplicantRitualApplicantDeletedFalseOrderByCreationDateDesc(applicantId);
        if (applicantPackage.isPresent()) {
            log.info("Finish findJpaApplicantPackageByApplicantId ::: StartDate: {}, EndDate:{}", applicantPackage.get().getStartDate(), applicantPackage.get().getEndDate());
            return getMapper().fromEntity(applicantPackage.get(), mappingContext);
        }
        log.info("Finish findJpaApplicantPackageByApplicantId ::: not found with applicantId: {}", applicantId);
        return null;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Long findIdByApplicantRitualId(Long applicantRitualId) {
        log.info("Start findIdByApplicantRitualId for {} applicant ritual id.", applicantRitualId);
        Optional<Long> applicantPackageId = applicantPackageRepository.findIdByApplicantRitualId(applicantRitualId);
        if (applicantPackageId.isPresent()) {
            log.info("Finish findIdByApplicantRitualId for {} applicant ritual id and found {} applicant package id.", applicantRitualId, applicantPackageId.get());
            return applicantPackageId.get();
        }
        log.info("Finish findIdByApplicantRitualId for {} applicant ritual id and not applicant package is found.", applicantRitualId);
        return null;
    }
}
