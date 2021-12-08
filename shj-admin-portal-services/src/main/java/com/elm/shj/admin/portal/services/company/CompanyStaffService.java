/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaff;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffRepository;
import com.elm.shj.admin.portal.services.dto.CompanyStaffDto;
import com.elm.shj.admin.portal.services.dto.CompanyStaffLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffService extends GenericService<JpaCompanyStaff, CompanyStaffDto, Long> {

    private final CompanyStaffRepository companyStaffRepository;

    public CompanyStaffLiteDto findBySuin(String suin) {
        CompanyStaffDto companyStaff = getMapper().fromEntity(companyStaffRepository.findBySuin(suin), mappingContext);
        if (companyStaff == null) return null;
        return CompanyStaffLiteDto.builder().fullNameAr(companyStaff.getFullNameAr()).fullNameEn(companyStaff.getFullNameEn())
                .mobileNumber(companyStaff.getMobileNumber()).email(companyStaff.getEmail()).nationalityCode(companyStaff.getNationalityCode())
                .photo(companyStaff.getPhoto()).titleCode(companyStaff.getTitleCode()).build();
    }

    public List<CompanyStaffDto> findRelatedEmployeesByApplicantUinAndSeasonId(String uin, long sid) {
        return mapList(companyStaffRepository.findByApplicantGroupsGroupApplicantListsApplicantUinAndCompanyRitualSeasonId(uin, sid));
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
     * @return companyStaffDto
     */
    public CompanyStaffDto findByBasicInfo(String idNumber, String passportNumber, Date dateGreg, Long dateHijri) {
        return getMapper().fromEntity(companyStaffRepository.findByBasicInfo(idNumber, dateHijri, passportNumber, dateGreg), mappingContext);
    }

}
