/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.ApplicantStaffVO;
import com.elm.shj.admin.portal.orm.entity.CompanyStaffVO;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffService extends GenericService<JpaCompanyStaff, CompanyStaffDto, Long> {

    private final CompanyStaffRepository companyStaffRepository;
    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(009665|9665|\\+9665|05|5)([0-9]{8})$";
    public final static Pattern  EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Optional<CompanyStaffLiteDto> findBySuin(String suin) {
        CompanyStaffDto companyStaff = getMapper().fromEntity(companyStaffRepository.findBySuin(suin, EDigitalIdStatus.VALID.name()), mappingContext);
        if (companyStaff != null) {
            CompanyStaffLiteDto companyStaffLite = CompanyStaffLiteDto.builder()
                    .id(companyStaff.getId())
                    .suin(suin)
                    .fullNameAr(companyStaff.getFullNameAr())
                    .fullNameEn(companyStaff.getFullNameEn())
                    .countryCode(companyStaff.getCountryCode())
                    .mobileNumber(companyStaff.getMobileNumber())
                    .email(companyStaff.getEmail())
                    .nationalityCode(companyStaff.getNationalityCode())
                    .photo(companyStaff.getPhoto())
                    .titleCode(companyStaff.getTitleCode())
                    .dateOfBirthGregorian(companyStaff.getDateOfBirthGregorian())
                    .dateOfBirthHijri(companyStaff.getDateOfBirthHijri())
                    .gender(companyStaff.getGender())
                    .build();
            return Optional.of(companyStaffLite);
        } else return Optional.empty();
    }

    public List<CompanyStaffDto> findRelatedEmployeesByApplicantUinAndSeasonId(String uin, long sid) {
        return mapList(companyStaffRepository.findByApplicantGroupsGroupApplicantListsApplicantUinAndApplicantGroupsCompanyRitualSeasonId(uin, sid));
    }

    public Optional<CompanyStaffDto> findGroupLeaderByApplicantUin(String applicantUin, long companyRitualSeasonId) {
        JpaCompanyStaff companyStaff = companyStaffRepository.findByApplicantGroupsGroupApplicantListsApplicantUinAndApplicantGroupsCompanyRitualSeasonIdAndTitleCode(applicantUin, companyRitualSeasonId, ECompanyStaffTitle.GROUP_LEADER.name());
        if (companyStaff != null) {
            return Optional.of(getMapper().fromEntity(companyStaff, mappingContext));
        }
        return Optional.empty();
    }

    /**
     * @param idNumber
     * @param passportNumber
     * @param dateGreg
     * @param dateHijri
     * @return
     */
    public boolean existsByBasicInfo(String idNumber, String passportNumber, Date dateGreg, Long dateHijri) {
        return ((CompanyStaffRepository) getRepository()).existsByBasicInfo(idNumber, dateHijri, passportNumber, dateGreg);
    }
    /**
     * @param idNumber
     * @param passportNumber
     * @param dateGreg
     * @param dateHijri
     * @return
     */
    public boolean existsByBasicInfoAndTitleIsGroupLeader(String idNumber, String passportNumber, Date dateGreg, Long dateHijri) {
        return ((CompanyStaffRepository) getRepository()).existsByBasicInfoAndTitleIsGroupLeader(idNumber, dateHijri, passportNumber, dateGreg, ECompanyStaffTitle.GROUP_LEADER.name());
    }
    /**
     * @param idNumber
     * @param passportNumber
     * @param dateGreg
     * @param dateHijri
     * @return companyStaffDto
     */
    public CompanyStaffDto findByBasicInfo(String idNumber, String passportNumber, Date dateGreg, Long dateHijri) {
        return getMapper().fromEntity(companyStaffRepository.findByBasicInfo(idNumber, dateHijri, passportNumber, dateGreg), mappingContext);
    }
    /**
     * @param idNumber
     * @param passportNumber
     * @param dateGreg
     * @param dateHijri
     * @return companyStaffDto
     */
    public CompanyStaffDto findGroupLeaderByBasicInfo(String idNumber, String passportNumber, Date dateGreg, Long dateHijri) {
        return getMapper().fromEntity(companyStaffRepository.findGroupLeaderByBasicInfo(idNumber, dateHijri, passportNumber, dateGreg, ECompanyStaffTitle.GROUP_LEADER.name()), mappingContext);
    }

    @Transactional
    public int updateCompanyStaff(long staffId, UpdateStaffCmd command) {
        int updatedRowsCount = 0;
        if (command.getMobileNumber().matches(SAUDI_MOBILE_NUMBER_REGEX)) {
            updatedRowsCount += companyStaffRepository.updateCompanyStaffLocalNumber(command.getEmail(), command.getCountryCode(), command.getMobileNumber(), staffId);
        } else {
            updatedRowsCount += companyStaffRepository.updateCompanyStaffIntlNumber(command.getEmail(), command.getCountryCode(), command.getMobileNumber(), staffId);
        }
        return updatedRowsCount;
    }

    public Optional<CompanyStaffDto> findByStaffId(long staffId) {
        JpaCompanyStaff staff = companyStaffRepository.findByIdAndRegisteredTrue(staffId);
        return (staff != null) ? Optional.of(getMapper().fromEntity(staff, mappingContext)) : Optional.empty();
    }

    public boolean validateStaffEmail(String email){
        Matcher matcher = EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public Optional<CompanyStaffVO> findStaffRitualBySuin(String suin) {
        CompanyStaffVO staffRitual = companyStaffRepository.findStaffMainData(suin);
        return staffRitual == null? Optional.empty(): Optional.of(staffRitual);
    }

    public Optional<ApplicantStaffVO> findStaffByIdNumber(String idNumber) {
        ApplicantStaffVO staffByIdNumber = companyStaffRepository.findStaffByIdNumber(idNumber, EDigitalIdStatus.VALID.name(), ECardStatus.CANCELLED.name(), ECardStatus.SUSPENDED.name());
        return staffByIdNumber == null? Optional.empty(): Optional.of(staffByIdNumber);
    }

    public Optional<ApplicantStaffVO> findStaffBySuin(String suin) {
        ApplicantStaffVO staffByIdNumber = companyStaffRepository.findStaffBySuin(suin, EDigitalIdStatus.VALID.name(), ECardStatus.CANCELLED.name(), ECardStatus.SUSPENDED.name());
        return staffByIdNumber == null? Optional.empty(): Optional.of(staffByIdNumber);
    }
}
