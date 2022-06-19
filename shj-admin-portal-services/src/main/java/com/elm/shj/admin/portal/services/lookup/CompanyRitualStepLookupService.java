/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.GroupRitualStepVo;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStepLookup;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualStepLookupRepository;
import com.elm.shj.admin.portal.services.company.CompanyRitualStepService;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepDto;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public List<GroupRitualStepVo> findCompanyRitualStepsByGroupId(long groupId) {
        List<GroupRitualStepVo> companyRitualSteps = repository.findCompanyRitualStepsByGroupId(groupId);

        companyRitualSteps.forEach(s-> {
            s.setStepHijriDate(DateUtils.toHijri(s.getStepDateTime()));
            s.setStepTime(s.getStepDateTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime());
        });

        return companyRitualSteps;
    }

    public CompanyRitualStepLookupDto findCompanyRitualStep(String stepCode) {
        Optional<JpaCompanyRitualStepLookup> companyRitualStep = repository.findTopByCodeAndLang(stepCode, "en");
        return companyRitualStep.isPresent() ? getMapper().fromEntity(companyRitualStep.get(), mappingContext):null;
    }

}
