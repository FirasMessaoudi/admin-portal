/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantDigitalId;
import com.elm.shj.admin.portal.orm.repository.ApplicantDigitalIdRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonLiteService;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Validator for {@link SameSeason} annotation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class SameSeasonValidator implements ConstraintValidator<SameSeason, Object> {
    @Autowired
    private CompanyRitualSeasonLiteService companyRitualSeasonLiteService;
    @Autowired
    private CompanyStaffService companyStaffService;
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private ApplicantDigitalIdRepository applicantDigitalIdRepository;

    @Autowired
    private CompanyStaffDigitalIdService companyStaffDigitalIdService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        StaffApplicantGroupDto staffApplicantGroupDto = (StaffApplicantGroupDto) value;
        ApplicantBasicInfoDto applicantBasicInfoDto = new ApplicantBasicInfoDto();
        applicantBasicInfoDto.setIdNumber(staffApplicantGroupDto.getIdNumber());
        applicantBasicInfoDto.setPassportNumber(staffApplicantGroupDto.getPassportNumber());
        applicantBasicInfoDto.setDateOfBirthGregorian(staffApplicantGroupDto.getDateOfBirthGregorian());
        applicantBasicInfoDto.setDateOfBirthHijri(staffApplicantGroupDto.getDateOfBirthHijri());
        ApplicantDto applicantDto = applicantService.findByBasicInfo(applicantBasicInfoDto);
        Optional<JpaApplicantDigitalId> applicantDigitalId = applicantDigitalIdRepository.findByApplicantIdAndStatusCode(applicantDto.getId(), EDigitalIdStatus.VALID.name());
        if (applicantDigitalId.isPresent()) {
            CompanyRitualSeasonLiteDto latestCompanyRitualSeason = companyRitualSeasonLiteService.getLatestCompanyRitualSeasonByApplicantUin(Long.parseLong(applicantDigitalId.get().getUin()));
            if (latestCompanyRitualSeason != null) {
                CompanyStaffDto groupLeader = companyStaffService.findGroupLeaderByBasicInfo(staffApplicantGroupDto.getStaffIdNumber(), staffApplicantGroupDto.getStaffPassportNumber(), staffApplicantGroupDto.getStaffDateOfBirthGregorian(), staffApplicantGroupDto.getStaffDateOfBirthHijri());
                if (groupLeader != null) {
                    CompanyStaffDigitalIdDto companyStaffDigitalIdDto = companyStaffDigitalIdService.findByBasicInfo(groupLeader.getId(), staffApplicantGroupDto.getSeason());
                    if (companyStaffDigitalIdDto != null && latestCompanyRitualSeason.getRitualSeason().getSeasonYear() == companyStaffDigitalIdDto.getSeasonYear()) {
                        return true;
                    }
                    return false;
                }
            }
        }

        return false;

    }

}
