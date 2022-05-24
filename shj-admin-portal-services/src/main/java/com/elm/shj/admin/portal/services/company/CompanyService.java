package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompany;
import com.elm.shj.admin.portal.orm.repository.CompanyRepository;
import com.elm.shj.admin.portal.services.dto.CompanyLiteDto;
import com.elm.shj.admin.portal.services.dto.ERitualType;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CompanyLiteDto findCompanyByCompanyRitualSeasonsIdAndApplicantUin(long companyRitualSeasonsId, long applicantUin) {
        return getMapper().fromEntity(companyRepository.findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsRitualPackagesApplicantPackagesApplicantUin(companyRitualSeasonsId, applicantUin), mappingContext);
    }

    public List<CompanyLiteDto> findCompaniesBySeasonAndRitualType(int currentHijriYear) {
        return getMapper().fromEntityList(companyRepository.findCompaniesBySeasonAndRitualType(currentHijriYear, List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())), mappingContext);
    }

    public List<CompanyLiteDto> findCompaniesBySeason(int currentHijriYear) {
        return getMapper().fromEntityList(companyRepository.findCompaniesBySeason(currentHijriYear), mappingContext);
    }


}
