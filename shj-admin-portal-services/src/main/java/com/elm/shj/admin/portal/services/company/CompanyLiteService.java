package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompany;
import com.elm.shj.admin.portal.orm.repository.CompanyRepository;
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
public class CompanyLiteService extends GenericService<JpaCompany, CompanyLiteDto, Long> {

    private final CompanyRepository companyRepository;

    public CompanyLiteDto findCompanyByCompanyRitualSeasonsIdAndApplicantUin(long companyRitualSeasonsId, long applicantUin) {
        return getMapper().fromEntity(companyRepository.findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsRitualPackagesApplicantPackagesApplicantUin(companyRitualSeasonsId, applicantUin), mappingContext);
    }

    /**
     * Checks if a company exists by its code
     *
     * @param companyCode the code of the country to look for
     * @return if the company is found
     */
    public boolean existsByCode(String companyCode) {
        return ((CompanyRepository) getRepository()).existsByCode(companyCode);
    }

}
