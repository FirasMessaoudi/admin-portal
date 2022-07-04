package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyLite;
import com.elm.shj.admin.portal.orm.repository.CompanyLiteRepository;
import com.elm.shj.admin.portal.services.dto.CompanyLiteDto;
import com.elm.shj.admin.portal.services.dto.ECompanyType;
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
public class CompanyLiteService extends GenericService<JpaCompanyLite, CompanyLiteDto, Long> {
    //TODO this service should be removed and move methods to CompanyService, no need for it

    private final CompanyLiteRepository companyLiteRepository;

    /**
     * Checks if a company exists by its code
     *
     * @param companyCode the code of the country to look for
     * @return if the company is found
     */
    public boolean existsByCode(String companyCode) {
        return companyLiteRepository.existsByCode(companyCode);
    }

    public List<CompanyLiteDto> findEstablishmentCompanies() {
        log.info("Start findEstablishmentCompanies");
        List<CompanyLiteDto> companyLiteDtoList = mapList(companyLiteRepository.findByTypeCode(ECompanyType.ESTABLISHMENT.name()));
        log.info("Finish findEstablishmentCompanies");
        return companyLiteDtoList;
    }

    public List<CompanyLiteDto> findServiceGroupCompanies(String establishmentCompanyCode) {
        log.info("Start findServiceGroupCompanies with establishmentCompanyCode: {}", establishmentCompanyCode);
        int refCode =   Integer.parseInt( establishmentCompanyCode.split("_")[0]);
        List<CompanyLiteDto> companyLiteDtoList = mapList(companyLiteRepository.findByTypeCodeAndEstablishmentRefCode(ECompanyType.SERVICE_GROUP.name(),refCode));
        log.info("Finish findServiceGroupCompanies with establishmentCompanyCode: {}", establishmentCompanyCode);
        return companyLiteDtoList;
    }

    public List<CompanyLiteDto> findInternalHajjCompanies() {
        log.info("Start findInternalHajjCompanies");
        List<CompanyLiteDto> companyLiteDtoList = mapList(companyLiteRepository.findByTypeCode(ECompanyType.INTERNAL_HAJ_COMPANY.name()));
        log.info("Finish findInternalHajjCompanies");
        return companyLiteDtoList;
    }

}
