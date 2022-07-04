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
        log.info("Start getLatestCompanyRitualSeasonByRitualSeason with companyCode: {}, typeCode: {}, seasonYear: {}", companyCode, typeCode, seasonYear);
        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findTopByCompanyCodeAndRitualSeasonRitualTypeCodeAndRitualSeasonSeasonYearAndRitualSeasonActiveTrueOrderBySeasonStartDesc(companyCode, typeCode, seasonYear);
        if (companyRitualSeason.isPresent()) {
            log.info("Finish getLatestCompanyRitualSeasonByRitualSeason with companyCode: {}, typeCode: {}, seasonYear: {}", companyCode, typeCode, seasonYear);
            return getMapper().fromEntity(companyRitualSeason.get(), mappingContext);
        } else {
            log.info("Finish getLatestCompanyRitualSeasonByRitualSeason not found with companyCode: {}, typeCode: {}, seasonYear: {}", companyCode, typeCode, seasonYear);
            return null;
        }
    }
    public Optional<Long> findLatestCompanyRitualSeasonIdBySuin(String suin) {
        log.info("Start findLatestCompanyRitualSeasonIdBySuin with suin: {}", suin);
        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findTopByCompanyStaffCardsCompanyStaffDigitalIdSuin(suin);
        if (companyRitualSeason.isPresent()) {
            log.info("Finish findLatestCompanyRitualSeasonIdBySuin with suin: {}", suin);
            return Optional.of( getMapper().fromEntity(companyRitualSeason.get(), mappingContext).getId());
        } else {
            log.info("Finish findLatestCompanyRitualSeasonIdBySuin not found with suin: {}", suin);
            return Optional.empty();
        }
    }

    public Optional<CompanyRitualSeasonDto> findLatestCompanyRitualSeasonBySuin(String suin) {
        log.info("Start findLatestCompanyRitualSeasonBySuin with suin: {}", suin);
        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findTopByCompanyStaffCardsCompanyStaffDigitalIdSuin(suin);
        if (companyRitualSeason.isPresent()) {
            log.info("Finish findLatestCompanyRitualSeasonBySuin with suin: {}", suin);
            return Optional.of(getMapper().fromEntity(companyRitualSeason.get(), mappingContext));
        } else {
            log.info("Finish findLatestCompanyRitualSeasonBySuin not found with suin: {}", suin);
            return Optional.empty();
        }
    }

    public CompanyRitualSeasonDto getCompanyRitualSeason(String companyCode, String typeCode, int seasonYear) {
        log.info("Start getCompanyRitualSeason with companyCode: {}, typeCode: {}, seasonYear: {}", companyCode, typeCode, seasonYear);
        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findByCompanyCodeAndRitualTypeAndSeasonYear(companyCode, typeCode, seasonYear);
        if (companyRitualSeason.isPresent()) {
            log.info("Finish getCompanyRitualSeason with companyCode: {}, typeCode: {}, seasonYear: {}", companyCode, typeCode, seasonYear);
            return getMapper().fromEntity(companyRitualSeason.get(), mappingContext);
        } else {
            log.info("Finish getCompanyRitualSeason not found with companyCode: {}, typeCode: {}, seasonYear: {}", companyCode, typeCode, seasonYear);
            return null;
        }
    }

    public Long getCompanyRitualSeasonId(String companyCode, String typeCode, int seasonYear) {
        log.info("Start getCompanyRitualSeasonId with companyCode: {}, typeCode: {}, seasonYear: {}", companyCode, typeCode, seasonYear);
        Long companyRitualSeasonId = companyRitualSeasonRepository.findIdByCompanyCodeAndRitualTypeAndSeasonYear(companyCode, typeCode, seasonYear);
        log.info("Finish getCompanyRitualSeasonId with companyCode: {}, typeCode: {}, seasonYear: {}", companyCode, typeCode, seasonYear);
        return companyRitualSeasonId;
    }



    public List<CompanyRitualSeasonDto> findByCompanyId(Long companyId) {
        log.info("Start findByCompanyId with companyId: {}", companyId);
        List<CompanyRitualSeasonDto> companyRitualSeasonDtoList = mapList(companyRitualSeasonRepository.findByCompanyId(companyId));
        log.info("Finish findByCompanyId with companyId: {}", companyId);
        return companyRitualSeasonDtoList;
    }

    public List<String> findByCompanyCode(String companyCode) {
        log.info("Start findByCompanyCode with companyCode: {}", companyCode);
        List<CompanyRitualSeasonDto> companyRitualSeasons = mapList(companyRitualSeasonRepository.findCompanyRitualByCompanyCode(companyCode));
        log.info("Finish findByCompanyCode with companyCode: {}", companyCode);
        return companyRitualSeasons.stream().map(crs -> crs.getRitualSeason().getRitualTypeCode()).collect(Collectors.toList());
    }

}
