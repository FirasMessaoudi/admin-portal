/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.GroupRitualStepVo;
import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualStepLookup;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualStepLookupRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualStepLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service handling company ritual step lookup
 *
 * @author firas messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRitualStepLookupService extends GenericService<JpaCompanyRitualStepLookup, CompanyRitualStepLookupDto, Long> {
    private final CompanyRitualStepLookupRepository repository;

    public List<JpaCompanyRitualStepLookup> findAllWithDescription() {
        return repository.findAllWithDescription();
    }

    public List<CompanyRitualStepLookupDto> findAllWithLang() {
        return mapList(repository.findAllByLang("en"));
    }


    public List<GroupRitualStepVo> findCompanyRitualStepsByGroupId(long groupId) {
        log.info("start findCompanyRitualStepsByGroupId with group Id: {}", groupId);
        log.info("query for findCompanyRitualStepsByGroupId with group Id: {}", groupId);
        List<GroupRitualStepVo> companyRitualSteps = repository.findCompanyRitualStepsByGroupId(groupId);
        log.info("company ritual steps found with : {}", companyRitualSteps);
        companyRitualSteps.forEach(s -> {
            s.setStepHijriDate(DateUtils.toHijri(s.getStepDateTime()));
            s.setStepTime(s.getStepDateTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime());
        });
        log.info("end findCompanyRitualStepsByGroupId with group Id: {}", groupId);
        return companyRitualSteps;
    }

    public CompanyRitualStepLookupDto findCompanyRitualStep(String stepCode) {
        log.info("start findCompanyRitualStep with step code: {}", stepCode);
        log.info("query for  findTopByCodeAndLang with step code: {}", stepCode);
        Optional<JpaCompanyRitualStepLookup> companyRitualStep = repository.findTopByCodeAndLang(stepCode, "en");
        log.info("companyRitualStep found : {}", companyRitualStep.isPresent());
        return companyRitualStep.isPresent() ? getMapper().fromEntity(companyRitualStep.get(), mappingContext):null;
    }

}
