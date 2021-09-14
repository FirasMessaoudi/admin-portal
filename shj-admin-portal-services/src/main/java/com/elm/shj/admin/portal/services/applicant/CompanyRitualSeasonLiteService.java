package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaCompanyRitualSeason;
import com.elm.shj.admin.portal.orm.repository.CompanyRitualSeasonRepository;
import com.elm.shj.admin.portal.services.dto.CompanyRitualSeasonLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service handling CompanyRitualSeason
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyRitualSeasonLiteService extends GenericService<JpaCompanyRitualSeason, CompanyRitualSeasonLiteDto, Long> {

    private final CompanyRitualSeasonRepository companyRitualSeasonRepository;


    public CompanyRitualSeasonLiteDto getLatestCompanyRitualSeasonByApplicantUin(String applicantUin) {
        JpaCompanyRitualSeason companyRitualSeason = companyRitualSeasonRepository.findTopByApplicantGroupsGroupApplicantListsApplicantUinOrderBySeasonStartDesc(applicantUin);
        return getMapper().fromEntity(companyRitualSeason, mappingContext);
    }

}
