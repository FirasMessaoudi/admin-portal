package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaCompany;
import com.elm.shj.admin.portal.orm.repository.CompanyRepository;
import com.elm.shj.admin.portal.services.dto.CompanyLiteDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling company lite
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyLiteService extends GenericService<JpaCompany, CompanyLiteDto, Long> {

    private final CompanyRepository companyRepository;

    public CompanyLiteDto findCompanyByCompanyRitualSeasonsIdAndApplicantUin(long companyRitualSeasonsId, String applicantUin) {

        return getMapper().fromEntity(companyRepository.findByCompanyRitualSeasonsIdAndCompanyRitualSeasonsApplicantGroupsGroupApplicantListsApplicantUin(companyRitualSeasonsId, applicantUin), mappingContext);
    }

}
