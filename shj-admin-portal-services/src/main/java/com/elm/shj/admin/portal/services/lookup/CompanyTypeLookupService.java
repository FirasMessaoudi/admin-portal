package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyTypeLookup;
import com.elm.shj.admin.portal.orm.repository.CompanyTypeLookupRepository;
import com.elm.shj.admin.portal.services.dto.CompanyTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling company type lookup
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyTypeLookupService extends GenericService<JpaCompanyTypeLookup, CompanyTypeLookupDto, Long> {
    private final CompanyTypeLookupRepository companyTypeLookupRepository;

    public boolean existsByCode(String code) {
        return companyTypeLookupRepository.existsByCode(code);
    }
}
