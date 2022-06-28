/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageHousing;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackageTransportation;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageTransportationRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service handling applicant package transportation
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantPackageTransportationService extends GenericService<JpaApplicantPackageTransportation, ApplicantPackageTransportationDto, Long> {

    private final ApplicantPackageTransportationRepository applicantPackageTransportationRepository;
    private final ApplicantService applicantService;
    private final ApplicantPackageService applicantPackageService;
    private final PackageTransportationService packageTransportationService;
    private static final String GROUP_APPLICANT_VEHICLE_NUMBER_MULTI= "multi";

    public List<ApplicantPackageTransportationDto> findApplicantPackageTransportationByUinAndApplicantPackageId(long applicantUin, long companyRitualSeasonId) {
        log.info("Start findApplicantPackageTransportationByUinAndApplicantPackageId ::: applicantUin:{}, companyRitualSeasonId:{} ", applicantUin,  companyRitualSeasonId);
        List<ApplicantPackageTransportationDto> applicantPackageTransportationDtos = getMapper().fromEntityList(applicantPackageTransportationRepository.findAllByApplicantPackageApplicantUinAndApplicantPackageId(applicantUin, companyRitualSeasonId), mappingContext);
        log.info("Finish findApplicantPackageTransportationByUinAndApplicantPackageId ::: applicantPackageTransportationDtosListSize", applicantPackageTransportationDtos.size());
        return  applicantPackageTransportationDtos;
    }

    public ApplicantPackageTransportationDto findByApplicantPackageIdAndTransportationId(long applicantPackageId, long packageTransportationId) {
        log.debug("Start findByApplicantPackageIdAndTransportationId applicantPackageId:{} , packageTransportationId:{}", applicantPackageId, packageTransportationId);
        Optional<JpaApplicantPackageTransportation> jpaApplicantPackageTransportation = applicantPackageTransportationRepository.findTopByApplicantPackageIdAndPackageTransportationId(applicantPackageId, packageTransportationId);
        log.debug("Finish findByApplicantPackageIdAndTransportationId applicantPackageId:{} , packageTransportationId:{}", applicantPackageId, packageTransportationId);
        return jpaApplicantPackageTransportation.isPresent() ? getMapper().fromEntity(jpaApplicantPackageTransportation.get(), mappingContext) : null;
    }

    @Transactional
    public Boolean updateApplicantTransportation(UpdateApplicantTransportationDto updateApplicantTransportationDto){
        log.info("updateApplicantTransportationDto .. {}", updateApplicantTransportationDto);
        ApplicantDto applicantDto = applicantService.findByUin(updateApplicantTransportationDto.getApplicantUin()).orElse(null);
        if(applicantDto == null)
            return false;

        // get applicant package for applicant
        ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantDto.getId());

        List<PackageTransportationDto> packageTransportationList = packageTransportationService.findAllByRitualPackageIdAndTypeCode(applicantPackageDto.getRitualPackage().getId(), ETransportationType.BUS.name());

        if (packageTransportationList.isEmpty()) {
            log.info("package transportation not found .. {}", applicantDto.getId());
            log.info("creating a new package transporation for applicant .. {}", applicantDto.getId());
            PackageTransportationDto packageTransportation = PackageTransportationDto.builder()
                    .ritualPackage(applicantPackageDto.getRitualPackage())
                    .typeCode(ETransportationType.BUS.name())
                    .validityStart(new GregorianCalendar(2022, Calendar.JULY, 6).getTime())
                    .validityEnd(new GregorianCalendar(2022, Calendar.JULY, 16).getTime())
                    .build();
            packageTransportation = packageTransportationService.save(packageTransportation);

            log.info("creating a new applicant package transporation for applicant .. {}", applicantDto.getId());
            ApplicantPackageTransportationDto applicantPackageTransportation = ApplicantPackageTransportationDto.builder()
                    .applicantPackage(applicantPackageDto)
                    .packageTransportation(packageTransportation)
                    .vehicleNumber(updateApplicantTransportationDto.getVehicleNumber())
                    .build();
            save(applicantPackageTransportation);
        } else {
            log.info(" package transporation found for applicant .. {}", applicantDto.getId());
            packageTransportationList.forEach(packageTransportationDto ->{
                ApplicantPackageTransportationDto applicantPackageTransportationDto = findByApplicantPackageIdAndTransportationId(applicantPackageDto.getId(), packageTransportationDto.getId());
                if (applicantPackageTransportationDto != null) {
                    applicantPackageTransportationDto.setVehicleNumber(updateApplicantTransportationDto.getVehicleNumber());
                    applicantPackageTransportationDto.setUpdateDate(new Date());
                    save(applicantPackageTransportationDto);

                } else {
                    log.info(" applicant package transporation not found for applicant package {} and transportation .. {}", applicantPackageDto.getId(), packageTransportationDto.getId());
                    ApplicantPackageTransportationDto applicantPackageTransportation = ApplicantPackageTransportationDto.builder()
                            .applicantPackage(applicantPackageDto)
                            .packageTransportation(packageTransportationDto)
                            .vehicleNumber(updateApplicantTransportationDto.getVehicleNumber())
                            .updateDate(new Date())
                            .build();
                    save(applicantPackageTransportation);
                }
            });
        }

        return true;
    }

    @Transactional
    public Boolean updateGroupApplicantTranportation(UpdateApplicantTransportationDto updateApplicantTransportationDto){
        log.info("groupId .. {}", updateApplicantTransportationDto.getGroupId());
        List<Long> applicantIdlIst = applicantService.findApplicantByGroupId(updateApplicantTransportationDto.getGroupId());
        log.info("applicantIdlIst ... {}" + applicantIdlIst) ;

        if(applicantIdlIst.isEmpty()){
            return false;
        }
        applicantIdlIst.forEach(applicantId -> {
            ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantId);

            List<PackageTransportationDto> packageTransportationList = packageTransportationService.findAllByRitualPackageIdAndTypeCode(applicantPackageDto.getRitualPackage().getId(), ETransportationType.BUS.name());

            if (packageTransportationList.isEmpty()) {
                log.info("package transportation not found .. {}", applicantId);
                log.info("creating a new package transporation for applicant .. {}", applicantId);
                PackageTransportationDto packageTransportation = PackageTransportationDto.builder()
                        .ritualPackage(applicantPackageDto.getRitualPackage())
                        .typeCode(ETransportationType.BUS.name())
                        .validityStart(new GregorianCalendar(2022, Calendar.JULY, 6).getTime())
                        .validityEnd(new GregorianCalendar(2022, Calendar.JULY, 16).getTime())
                        .build();
                packageTransportation = packageTransportationService.save(packageTransportation);

                log.info("creating a new applicant package transporation for applicant .. {}", applicantId);
                ApplicantPackageTransportationDto applicantPackageTransportation = ApplicantPackageTransportationDto.builder()
                        .applicantPackage(applicantPackageDto)
                        .packageTransportation(packageTransportation)
                        .vehicleNumber(updateApplicantTransportationDto.getVehicleNumber())
                        .build();
                save(applicantPackageTransportation);
            } else {
                packageTransportationList.forEach(packageTransportationDto ->{
                    ApplicantPackageTransportationDto applicantPackageTransportationDto = findByApplicantPackageIdAndTransportationId(applicantPackageDto.getId(), packageTransportationDto.getId());
                    if (applicantPackageTransportationDto != null) {
                        applicantPackageTransportationDto.setVehicleNumber(updateApplicantTransportationDto.getVehicleNumber());
                        applicantPackageTransportationDto.setUpdateDate(new Date());
                        save(applicantPackageTransportationDto);

                    } else {
                        log.info(" applicant package transporation not found for applicant package {} and transportation .. {}", applicantPackageDto.getId(), packageTransportationDto.getId());
                        ApplicantPackageTransportationDto applicantPackageTransportation = ApplicantPackageTransportationDto.builder()
                                .applicantPackage(applicantPackageDto)
                                .packageTransportation(packageTransportationDto)
                                .vehicleNumber(updateApplicantTransportationDto.getVehicleNumber())
                                .updateDate(new Date())
                                .build();
                        save(applicantPackageTransportation);
                    }
                });
            }
        });
        return true;
    }

    public String findGroupApplicantVebicleNumber(Long groupId){
        log.info("groupId .. {}", groupId);
        List<Long> applicantIdlIst = applicantService.findApplicantByGroupId(groupId);
        log.info("applicantIdlIst ... {}" + applicantIdlIst);

        List<ApplicantPackageTransportationDto> applicantPackageTransportationList = new ArrayList<>();

        applicantIdlIst.forEach(applicantId -> {
            ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantId);
            log.info("applicant package not found for applicant ... {}" + applicantId);

            PackageTransportationDto packageTransportation = packageTransportationService.findByRitualPackageIdAndTypeCode(applicantPackageDto.getRitualPackage().getId(), ETransportationType.BUS.name());

            if (packageTransportation != null) {
                log.info("packageTransportation  found for applicant ritual package... {}" + applicantPackageDto.getRitualPackage().getId());
                ApplicantPackageTransportationDto applicantPackageTransportation = findByApplicantPackageIdAndTransportationId(applicantPackageDto.getId(), packageTransportation.getId());
                applicantPackageTransportationList.add(applicantPackageTransportation);
            }
        });
        StringBuilder vehicleNumber = new StringBuilder();
        if(!applicantPackageTransportationList.isEmpty()){
            if(applicantPackageTransportationList.stream().map(ApplicantPackageTransportationDto::getVehicleNumber).distinct().count() <= 1){
                vehicleNumber.append(applicantPackageTransportationList.get(0).getVehicleNumber());
            } else {
                vehicleNumber.append(GROUP_APPLICANT_VEHICLE_NUMBER_MULTI);
            }
        }
        return vehicleNumber.toString();
    }

    public String findApplicantVehicleNumberInfo(String applicantUin){
        log.info("updateApplicantHousingCampDto .. {}", applicantUin);
        ApplicantDto applicantDto = applicantService.findByUin(applicantUin).orElse(null);
        ApplicantCampDetailDto applicantCampDetail = new ApplicantCampDetailDto();
        if(applicantDto == null)
            return null;

        ApplicantPackageDto applicantPackageDto = applicantPackageService.findJpaApplicantPackageByApplicantId(applicantDto.getId());
        log.info("applicant package not found for applicant ... {}" + applicantDto.getId());

        PackageTransportationDto packageTransportation = packageTransportationService.findByRitualPackageIdAndTypeCode(applicantPackageDto.getRitualPackage().getId(), ETransportationType.BUS.name());
        StringBuilder vehicleNumber = new StringBuilder();
        if (packageTransportation != null) {
            log.info("packageTransportation  found for applicant ritual package... {}" + applicantPackageDto.getRitualPackage().getId());
            ApplicantPackageTransportationDto applicantPackageTransportation = findByApplicantPackageIdAndTransportationId(applicantPackageDto.getId(), packageTransportation.getId());
            if(applicantPackageTransportation != null){
                vehicleNumber.append(applicantPackageTransportation.getVehicleNumber());
            }
        }
        return vehicleNumber.toString();
    }

}
