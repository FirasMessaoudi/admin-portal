package com.elm.shj.admin.portal.services.applicant;

import com.elm.dcc.foundation.commons.core.mapper.CycleAvoidingMappingContext;
import com.elm.shj.admin.portal.orm.entity.ApplicantRitualPackageVo;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPackage;
import com.elm.shj.admin.portal.orm.repository.ApplicantPackageRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantPackageService extends GenericService<JpaApplicantPackage, ApplicantPackageDto, Long> {

    private final ApplicantPackageRepository applicantPackageRepository;

    @Transactional(readOnly = true)
    public ApplicantPackageDto findApplicantPackageByUinAndReferenceNumber(Long applicantUin, String packageReferenceNumber) {
        Optional<JpaApplicantPackage> applicantPackageOptional = applicantPackageRepository.findByApplicantUinAndRitualPackageReferenceNumber(applicantUin, packageReferenceNumber);
        if (applicantPackageOptional.isPresent()) {
            JpaApplicantPackage applicantPackage = applicantPackageOptional.get();
            if (applicantPackage.getApplicantRituals() != null) {
                applicantPackage.getApplicantRituals().size();
            }
            applicantPackage.getApplicantPackageTransportations().size();
            CycleAvoidingMappingContext mappingContext = new CycleAvoidingMappingContext();
            return getMapper().fromEntity(applicantPackage, mappingContext);
        }
        return null;
    }

    public List<ApplicantRitualPackageVo> findApplicantRitualPackageByUin(long applicantUin) {
        return applicantPackageRepository.findApplicantRitualPackageByUin(applicantUin);

    }

    public ApplicantRitualPackageVo findLatestApplicantRitualPackage(long applicantUin) {
        List<ApplicantRitualPackageVo> applicantRitualSeasons = findApplicantRitualPackageByUin(applicantUin);
        if (applicantRitualSeasons.size() > 0) {
            return applicantRitualSeasons.get(0);
        }
        return null;
    }

    public ApplicantPackageDto findByIdAndApplicantUin(Long id, Long applicantUin){
        Optional<JpaApplicantPackage>  applicantPackage = applicantPackageRepository.findByIdAndApplicantUin(id,applicantUin);
        if(applicantPackage.isPresent()){
            return getMapper().fromEntity(applicantPackage.get(),mappingContext);
        }
        return  null;
    }

}
