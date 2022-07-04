package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffTitleLookup;
import com.elm.shj.admin.portal.orm.repository.CompanyRepository;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffTitleLookupRepository;
import com.elm.shj.admin.portal.services.dto.CompanyStaffTitleLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service handling company staff title lookup
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffTitleLookupService extends GenericService<JpaCompanyStaffTitleLookup, CompanyStaffTitleLookupDto, Long> {

    private final CompanyStaffTitleLookupRepository companyStaffTitleLookupRepository;
    /**
     * Checks if a company staff job title  exists by its code
     *
     * @param jobTitleCode the code of the company staff title to look for
     * @return if the company staff title is found
     */
    public boolean existsByCode(String jobTitleCode) {
        log.info("start existsByCode in CompanyStaffTitleLookupService with jobTitleCode: {}", jobTitleCode);
        boolean jobTitleExits = companyStaffTitleLookupRepository.existsByCode(jobTitleCode);
        log.info("job title code exits :{}", jobTitleExits);
        log.info("finish existsByCode in CompanyStaffTitleLookupService with jobTitleCode: {}", jobTitleCode);
        return jobTitleExits;
    }
}
