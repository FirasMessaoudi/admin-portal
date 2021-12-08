package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualSeasonRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service handling CompanyRitualSeason
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRitualSeasonService extends GenericService<JpaCompanyRitualSeason, CompanyRitualSeasonDto, Long> {

    private final CompanyRitualSeasonRepository companyRitualSeasonRepository;

    public CompanyRitualSeasonDto getLatestCompanyRitualSeasonByRitualSeason(String companyCode, String typeCode, int seasonYear) {
        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findTopByCompanyCodeAndRitualSeasonRitualTypeCodeAndRitualSeasonSeasonYearOrderBySeasonStartDesc(companyCode, typeCode, seasonYear);
        if (companyRitualSeason.isPresent())
            return getMapper().fromEntity(companyRitualSeason.get(), mappingContext);
        else
            return null;
    }

}
