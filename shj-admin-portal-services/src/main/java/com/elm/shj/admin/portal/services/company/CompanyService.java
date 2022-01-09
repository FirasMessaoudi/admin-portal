package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompany;
import com.elm.shj.admin.portal.orm.repository.CompanyRepository;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffRepository;
import com.elm.shj.admin.portal.services.dto.CompanyLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling company lite
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyService extends GenericService<JpaCompany, CompanyLiteDto, Long> {

    private final CompanyRepository companyRepository;
    private final CompanyStaffRepository companyStaffRepository;

    public CompanyLiteDto findCompanyByCompanyRitualSeasonsIdAndApplicantUin(long companyRitualSeasonsId, long applicantUin) {
        return getMapper().fromEntity(companyRepository.findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsRitualPackagesApplicantPackagesApplicantUin(companyRitualSeasonsId, applicantUin), mappingContext);
    }

    public CompanyLiteDto findCompanyByStaffSuin(String companyStaffSuin) {
        return getMapper().fromEntity(companyRepository.findByCompanyRitualSeasonsCompanyStaffCardsCompanyStaffDigitalIdSuin(companyStaffSuin), mappingContext);
    }


}
