package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyLite;
import com.elm.shj.admin.portal.orm.repository.CompanyLiteRepository;
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
public class CompanyLiteService extends GenericService<JpaCompanyLite, CompanyLiteDto, Long> {

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

}
