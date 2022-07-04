package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeasonLite;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualSeasonLiteRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service handling CompanyRitualSeason
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRitualSeasonLiteService extends GenericService<JpaCompanyRitualSeasonLite, CompanyRitualSeasonLiteDto, Long> {

    private final CompanyRitualSeasonLiteRepository companyRitualSeasonRepository;


    public CompanyRitualSeasonLiteDto getLatestCompanyRitualSeasonByApplicantUin(long applicantUin) {
        log.info("Start getLatestCompanyRitualSeasonByApplicantUin with applicantUin: {}", applicantUin);
        JpaCompanyRitualSeasonLite companyRitualSeason = companyRitualSeasonRepository.findTopByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(applicantUin);
        log.info("Finish getLatestCompanyRitualSeasonByApplicantUin with applicantUin: {}", applicantUin);
        return getMapper().fromEntity(companyRitualSeason, mappingContext);
    }

    public List<CompanyRitualSeasonLiteDto> getListCompanyRitualSeasonByApplicantUin(long applicantUin) {
        log.info("Start getListCompanyRitualSeasonByApplicantUin with applicantUin: {}", applicantUin);
        List<JpaCompanyRitualSeasonLite> companyRitualSeasons = companyRitualSeasonRepository.findAllByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(applicantUin);
        log.info("Finish getListCompanyRitualSeasonByApplicantUin with applicantUin: {}", applicantUin);
        return getMapper().fromEntityList(companyRitualSeasons, mappingContext);
    }


}
