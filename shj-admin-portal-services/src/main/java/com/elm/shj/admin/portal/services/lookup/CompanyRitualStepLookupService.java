/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStepLookup;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualStepLookupRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling company ritual step lookup
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRitualStepLookupService extends GenericService<JpaCompanyRitualStepLookup, CompanyRitualStepLookupDto,Long> {
    private final CompanyRitualStepLookupRepository repository;

   public List<JpaCompanyRitualStepLookup> findAllWithDescription(){
        return  repository.findAllWithDescription();
    }

}
