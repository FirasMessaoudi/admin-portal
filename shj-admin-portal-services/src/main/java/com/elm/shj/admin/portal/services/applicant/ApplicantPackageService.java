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
import org.springframework.stereotype.Service;
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

    @Transactional(readOnly = true)
    public ApplicantPackageDto findApplicantPackageByUinAndReferenceNumber(Long applicantUin, String packageReferenceNumber) {
        Optional<JpaApplicantPackage> applicantPackageOptional = applicantPackageRepository.findByApplicantUinAndRitualPackageReferenceNumber(applicantUin, packageReferenceNumber);
        if (applicantPackageOptional.isPresent()) {
            JpaApplicantPackage applicantPackage = applicantPackageOptional.get();
            applicantPackage.getApplicantPackageTransportations().size();
            CycleAvoidingMappingContext mappingContext = new CycleAvoidingMappingContext();
            return getMapper().fromEntity(applicantPackage, mappingContext);
        }
        return null;
    }

    public List<ApplicantRitualPackageVo> findApplicantRitualPackageByUin(long applicantUin) {
        return applicantPackageRepository.findApplicantRitualPackageByUin(applicantUin);

    }

    public ApplicantRitualPackageVo findLatestApplicantRitualPackage(long applicantUin) {
        List<ApplicantRitualPackageVo> applicantRitualSeasons = findApplicantRitualPackageByUin(applicantUin);
        if (applicantRitualSeasons.size() > 0) {
            return applicantRitualSeasons.get(0);
        }
        return null;
    }

    public ApplicantPackageDto findByIdAndApplicantUin(Long id, Long applicantUin){
        Optional<JpaApplicantPackage>  applicantPackage = applicantPackageRepository.findByIdAndApplicantUin(id,applicantUin);
        if(applicantPackage.isPresent()){
            return getMapper().fromEntity(applicantPackage.get(),mappingContext);
        }
        return  null;
    }

    /**
     * Find the latest applicant package id based on applicant UIN and package reference number.
     *
     * @param applicantUin
     * @param referenceNumber
     * @return
     */
    public Long findCurrentApplicantPackage(long applicantUin, String referenceNumber) {
        return applicantPackageRepository.findIdByApplicantUinAndRitualPackageReferenceNumber(applicantUin, referenceNumber);
    }

    /**
     *
     * @param uin
     * @param applicantPackageId
     * @return
     */
    public List<ApplicantPackageCateringDto> findPackageCateringFromRitualPackage(long uin, long applicantPackageId) {
        List<ApplicantPackageCateringDto> applicantPackageCateringDtos = new ArrayList<>();
        ApplicantPackageDto applicantPackage = findOne(applicantPackageId);
        List<PackageHousingDto> packageHousingDtos = packageHousingService.findByRitualPackageId(applicantPackage.getRitualPackage().getId());

        Optional<PackageHousingDto> packageHousingDto = packageHousingDtos.stream()
                .filter(p -> LocalDateTime.now().isAfter(DateUtils.convertToLocalDate(p.getValidityStart()))
                        && LocalDateTime.now().isBefore(DateUtils.convertToLocalDate(p.getValidityEnd()))).findFirst();
        if (!packageHousingDto.isPresent()) {
            return applicantPackageCateringDtos;
        }

        //today's catering
        applicantPackageCateringDtos = applicantPackageCateringService.findAllByApplicantPackageApplicantUinAndPackageCateringPackageHousingId(uin, packageHousingDto.get().getId());

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

        List<ApplicantPackageHousingDto> applicantPackageHousings;
        List<ApplicantPackageCateringDto> applicantPackageCaterings = new ArrayList<>();
        List<ApplicantPackageTransportationDto> applicantPackageTransportations = new ArrayList<>();

        RitualPackageDto ritualPackage = ritualPackageService.findRitualPackageByReferenceNumber(packageReferenceNumber);

        // initiate applicant package
        ApplicantPackageDto applicantPackage = ApplicantPackageDto.builder().applicantUin(applicantUin)
                .ritualPackage(RitualPackageDto.builder().id(ritualPackage.getId()).build()).build();

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
        return save(applicantPackage);
    }

}
