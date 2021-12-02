package com.elm.shj.admin.portal.services.applicant;

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
        JpaCompanyRitualSeasonLite companyRitualSeason = companyRitualSeasonRepository.findTopByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(applicantUin);
        return getMapper().fromEntity(companyRitualSeason, mappingContext);
    }

    public List<CompanyRitualSeasonLiteDto> getListCompanyRitualSeasonByApplicantUin(long applicantUin) {
        List<JpaCompanyRitualSeasonLite> companyRitualSeasons = companyRitualSeasonRepository.findAllByRitualPackagesApplicantPackagesApplicantUinOrderBySeasonStartDesc(applicantUin);
        return getMapper().fromEntityList(companyRitualSeasons, mappingContext);
    }


}
