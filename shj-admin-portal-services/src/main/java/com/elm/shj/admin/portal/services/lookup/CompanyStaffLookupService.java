package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffTitleLookup;
import com.elm.shj.admin.portal.services.dto.CompanyStaffTitleLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Service handling company staff title lookup
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@Service
@Slf4j
public class CompanyStaffLookupService extends GenericService<JpaCompanyStaffTitleLookup, CompanyStaffTitleLookupDto, Long> {
}
