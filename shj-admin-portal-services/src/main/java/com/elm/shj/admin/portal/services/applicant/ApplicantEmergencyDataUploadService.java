/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantEmergencyDataUpload;
import com.elm.shj.admin.portal.orm.repository.ApplicantEmergencyDataUploadRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantBasicInfoDto;
import com.elm.shj.admin.portal.services.dto.ApplicantEmergencyDataUploadDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling applicant emergency data upload
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantEmergencyDataUploadService extends GenericService<JpaApplicantEmergencyDataUpload, ApplicantEmergencyDataUploadDto, Long> {

    private final ApplicantEmergencyDataUploadRepository applicantEmergencyDataUploadRepository;

    /**
     * Find record based on applicant basic info and package code.
     *
     * @param applicantBasicInfo
     * @param packageCode
     * @return
     */
    public ApplicantEmergencyDataUploadDto findByBasicInfoAndPackageCode(ApplicantBasicInfoDto applicantBasicInfo, String packageCode) {
        log.info("ApplicantEmergencyDataUploadService ::: Start findByBasicInfoAndPackageCode ::: applicantBasicInfoRowNum: {}, packageCode: {}", applicantBasicInfo == null ? null : applicantBasicInfo.getRowNum(), packageCode);
        ApplicantEmergencyDataUploadDto applicantEmergencyDataUploadDto = getMapper().fromEntity(applicantEmergencyDataUploadRepository.findByBasicInfo(applicantBasicInfo.getIdNumber(), applicantBasicInfo.getDateOfBirthHijri(),
                applicantBasicInfo.getPassportNumber(), applicantBasicInfo.getDateOfBirthGregorian(), packageCode), mappingContext);
        log.info("ApplicantEmergencyDataUploadService ::: Finish findByBasicInfoAndPackageCode ::: applicantEmergencyDataUploadDtoIdNumber: {}", applicantBasicInfo == null ? null : applicantEmergencyDataUploadDto == null?null: applicantEmergencyDataUploadDto.getIdNumber());
        return applicantEmergencyDataUploadDto;
    }
}
