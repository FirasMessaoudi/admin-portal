package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompany;
import com.elm.shj.admin.portal.orm.repository.CompanyRepository;
import com.elm.shj.admin.portal.services.dto.CompanyLiteDto;
import com.elm.shj.admin.portal.services.dto.ECompanyType;
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
        log.info("Start findCompanyByCompanyRitualSeasonsIdAndApplicantUin with companyRitualSeasonsId: {}, applicantUin: {}", companyRitualSeasonsId, applicantUin);
        CompanyLiteDto companyLiteDto = getMapper().fromEntity(companyRepository.findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsRitualPackagesApplicantPackagesApplicantUin(companyRitualSeasonsId, applicantUin), mappingContext);
        log.info("Finish findCompanyByCompanyRitualSeasonsIdAndApplicantUin with companyRitualSeasonsId: {}, applicantUin: {}", companyRitualSeasonsId, applicantUin);
        return companyLiteDto;
    }

    public List<CompanyLiteDto> findCompaniesBySeasonAndRitualType(int currentHijriYear) {
        log.info("Start findCompaniesBySeasonAndRitualType");
        List<CompanyLiteDto> companyLiteDtoList = getMapper().fromEntityList(companyRepository.findCompaniesBySeasonAndRitualType(currentHijriYear, List.of(ERitualType.INTERNAL_HAJJ.name(), ERitualType.EXTERNAL_HAJJ.name(), ERitualType.COURTESY_HAJJ.name())), mappingContext);
        log.info("Finish findCompaniesBySeasonAndRitualType");
        return companyLiteDtoList;
    }

    public List<CompanyLiteDto> findCompaniesBySeason(int currentHijriYear) {
        log.info("Start findCompaniesBySeason with currentHijriYear: {}", currentHijriYear);
        List<CompanyLiteDto> companyLiteDtoList = getMapper().fromEntityList(companyRepository.findCompaniesBySeason(currentHijriYear), mappingContext);
        log.info("Finish findCompaniesBySeason with currentHijriYear: {}", currentHijriYear);
        return companyLiteDtoList;
    }


    public boolean existsByBasicInfo(String companyRefCode, Integer companyTypeCode) {
        log.info("Start existsByBasicInfo with companyRefCode: {}, companyTypeCode: {}", companyRefCode, companyTypeCode);
        boolean isCompanyExists = companyRepository.existsByCode(companyRefCode + "_" + ECompanyType.fromId(companyTypeCode).name());
        log.info("Finish existsByBasicInfo with companyRefCode: {}, companyTypeCode: {}", companyRefCode, companyTypeCode);
        return isCompanyExists;
    }

    public CompanyLiteDto findByBasicInfo(String companyRefCode, Integer companyTypeCode) {
        log.info("Start existsByBasicInfo with companyRefCode: {}, companyTypeCode: {}", companyRefCode, companyTypeCode);
        CompanyLiteDto companyLiteDto = getMapper().fromEntity(companyRepository.findByCode(companyRefCode + "_" + ECompanyType.fromId(companyTypeCode).name()), mappingContext);
        log.info("Start existsByBasicInfo with companyRefCode: {}, companyTypeCode: {}", companyRefCode, companyTypeCode);
        return companyLiteDto;
    }

    public String findUidByCode(String companyCode){
        log.info("Start findUidByCode with companyCode: {}", companyCode);
        String companyUid = companyRepository.findUidByCode(companyCode);
        log.info("Finish findUidByCode with companyCode: {}", companyCode);
        return companyUid;
    }
}
