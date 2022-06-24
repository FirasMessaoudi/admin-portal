package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualSeasonRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findTopByCompanyCodeAndRitualSeasonRitualTypeCodeAndRitualSeasonSeasonYearAndRitualSeasonActiveTrueOrderBySeasonStartDesc(companyCode, typeCode, seasonYear);
        if (companyRitualSeason.isPresent())
            return getMapper().fromEntity(companyRitualSeason.get(), mappingContext);
        else
            return null;
    }
    public Optional<Long> findLatestCompanyRitualSeasonIdBySuin(String suin) {
        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findTopByCompanyStaffCardsCompanyStaffDigitalIdSuin(suin);
        if (companyRitualSeason.isPresent())
            return Optional.of( getMapper().fromEntity(companyRitualSeason.get(), mappingContext).getId());
        else
            return Optional.empty();
    }

    public CompanyRitualSeasonDto getCompanyRitualSeason(String companyCode, String typeCode, int seasonYear) {
        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findByCompanyCodeAndRitualTypeAndSeasonYear(companyCode, typeCode, seasonYear);
        if (companyRitualSeason.isPresent())
            return getMapper().fromEntity(companyRitualSeason.get(), mappingContext);
        else
            return null;
    }

    public Long getCompanyRitualSeasonId(String companyCode, String typeCode, int seasonYear) {
        Long companyRitualSeasonId = companyRitualSeasonRepository.findIdByCompanyCodeAndRitualTypeAndSeasonYear(companyCode, typeCode, seasonYear);
        log.info("companyRitualSeasonId .. {}", companyRitualSeasonId);
        return companyRitualSeasonId;
    }



    public List<CompanyRitualSeasonDto> findByCompanyId(Long companyId) {
        return mapList(companyRitualSeasonRepository.findByCompanyId(companyId));
    }

    public List<String> findByCompanyCode(String companyCode) {
        List<CompanyRitualSeasonDto> companyRitualSeasons = mapList(companyRitualSeasonRepository.findCompanyRitualByCompanyCode(companyCode));
        return companyRitualSeasons.stream().map(crs -> crs.getRitualSeason().getRitualTypeCode()).collect(Collectors.toList());
    }

}
