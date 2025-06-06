/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.validators;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantDigitalId;
import com.elm.shj.admin.portal.orm.repository.ApplicantDigitalIdRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantService;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.company.CompanyRitualSeasonLiteService;
import com.elm.shj.admin.portal.services.company.CompanyStaffService;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdBasicService;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Validator for {@link SameRitual} annotation
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public class SameRitualValidator implements ConstraintValidator<SameRitual, Object> {
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

    @Autowired
    private CompanyStaffDigitalIdBasicService companyStaffDigitalIdBasicService;
    @Autowired
    private CompanyStaffCardService companyStaffCardService;

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
        Long applicantId = applicantService.findIdByBasicInfo(applicantBasicInfoDto);
        Optional<JpaApplicantDigitalId> applicantDigitalId = applicantDigitalIdRepository.findByApplicantIdAndStatusCode(applicantId, EDigitalIdStatus.VALID.name());
        if (applicantDigitalId.isPresent()) {
            CompanyRitualSeasonLiteDto latestCompanyRitualSeason = companyRitualSeasonLiteService.getLatestCompanyRitualSeasonByApplicantUin(Long.parseLong(applicantDigitalId.get().getUin()));
            if (latestCompanyRitualSeason != null) {
                CompanyStaffDto groupLeader = companyStaffService.findGroupLeaderByBasicInfo(staffApplicantGroupDto.getStaffIdNumber(), staffApplicantGroupDto.getStaffPassportNumber(), staffApplicantGroupDto.getStaffDateOfBirthGregorian(), staffApplicantGroupDto.getStaffDateOfBirthHijri());
                if (groupLeader != null) {
                    CompanyStaffDigitalIdBasicDto companyStaffDigitalIdDto = companyStaffDigitalIdBasicService.findByBasicInfo(groupLeader.getId(), staffApplicantGroupDto.getSeason());
                   if(companyStaffDigitalIdDto!=null){
                       CompanyStaffCardDto companyStaffCardDto = companyStaffCardService.findByDigitalIdAndStatusCodeActive(companyStaffDigitalIdDto.getSuin());
                       if (companyStaffCardDto != null &&
                               companyStaffCardDto.getCompanyRitualSeason().getRitualSeason().getId() == latestCompanyRitualSeason.getRitualSeason().getId()
                       ) {
                           return true;
                       }
                   }

                    return false;
                }
            }
        }

        return false;

    }

}
