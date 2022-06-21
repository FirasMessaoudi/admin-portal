package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeasonBasic;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualSeasonBasicRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonBasicDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service handling CompanyRitualSeason
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRitualSeasonBasicService extends GenericService<JpaCompanyRitualSeasonBasic, CompanyRitualSeasonBasicDto, Long> {

    private final CompanyRitualSeasonBasicRepository companyRitualSeasonBasicRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public CompanyRitualSeasonBasicDto getLatestCompanyRitualSeasonByRitualSeason(String companyCode, String typeCode, int seasonYear) {
        log.info("Start getLatestCompanyRitualSeasonByRitualSeason with {} company code and {} type code and {} season year.", companyCode, typeCode, seasonYear);
        Optional<JpaCompanyRitualSeasonBasic> companyRitualSeason = companyRitualSeasonBasicRepository.findTopByCompanyCodeAndRitualSeasonRitualTypeCodeAndRitualSeasonSeasonYearAndRitualSeasonActiveTrueOrderBySeasonStartDesc(companyCode, typeCode, seasonYear);
        if (companyRitualSeason.isPresent()) {
            log.info("Finish getLatestCompanyRitualSeasonByRitualSeason, found {} company ritual season id.", companyRitualSeason.get().getId());
            return getMapper().fromEntity(companyRitualSeason.get(), mappingContext);
        }
        return null;
    }

//    public Optional<Long> findLatestCompanyRitualSeasonIdBySuin(String suin) {
//        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findTopByCompanyStaffCardsCompanyStaffDigitalIdSuin(suin);
//        if (companyRitualSeason.isPresent())
//            return Optional.of( getMapper().fromEntity(companyRitualSeason.get(), mappingContext).getId());
//        else
//            return Optional.empty();
//    }
//
//    public CompanyRitualSeasonDto getCompanyRitualSeason(String companyCode, String typeCode, int seasonYear) {
//        Optional<JpaCompanyRitualSeason> companyRitualSeason = companyRitualSeasonRepository.findByCompanyCodeAndRitualTypeAndSeasonYear(companyCode, typeCode, seasonYear);
//        if (companyRitualSeason.isPresent())
//            return getMapper().fromEntity(companyRitualSeason.get(), mappingContext);
//        else
//            return null;
//    }
//
//    public List<CompanyRitualSeasonDto> findByCompanyId(Long companyId) {
//        return mapList(companyRitualSeasonRepository.findByCompanyId(companyId));
//    }
}
