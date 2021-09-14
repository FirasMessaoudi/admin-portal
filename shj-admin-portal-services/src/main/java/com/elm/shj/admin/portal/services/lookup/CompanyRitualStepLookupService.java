/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStepLookup;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling company ritual step lookup
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
public class CompanyRitualStepLookupService extends GenericService<JpaCompanyRitualStepLookup, CompanyRitualStepLookupDto,Long> {
}
