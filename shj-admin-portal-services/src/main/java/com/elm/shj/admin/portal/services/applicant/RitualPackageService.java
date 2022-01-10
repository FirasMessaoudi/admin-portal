package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackage;
import com.elm.shj.admin.portal.orm.repository.RitualPackageRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageCateringDto;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageDto;
import com.elm.shj.admin.portal.services.dto.PackageHousingDto;
import com.elm.shj.admin.portal.services.dto.RitualPackageDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RitualPackageService extends GenericService<JpaRitualPackage, RitualPackageDto, Long> {

    private final RitualPackageRepository ritualPackageRepository;
    private final ApplicantPackageService applicantPackageService;
    private final ApplicantPackageCateringService applicantPackageCateringService;
    private final PackageHousingService packageHousingService;

    public boolean existRitualPackageByReferenceNumber(String referenceNumber) {
        Optional<JpaRitualPackage> ritualPackageOptional = ritualPackageRepository.findByReferenceNumber(referenceNumber);
        return ritualPackageOptional.isPresent();
    }

    @Transactional
    public RitualPackageDto findRitualPackageByReferenceNumber(String referenceNumber) {
        Optional<JpaRitualPackage> ritualPackageOptional = ritualPackageRepository.findByReferenceNumber(referenceNumber);
        if (ritualPackageOptional.isPresent()) {
            JpaRitualPackage ritualPackage = ritualPackageOptional.get();

            ritualPackage.getPackageTransportations().size();
            ritualPackage.getPackageHousings().size();
            if(CollectionUtils.isNotEmpty(ritualPackage.getPackageHousings())){
                ritualPackage.getPackageHousings().forEach(ph -> ph.getPackageCatering().size());
            }

            return getMapper().fromEntity(ritualPackage, mappingContext);
        }

        return null;
    }


    @Transactional
    public RitualPackageDto findRitualPackageByApplicantUinAndCompanyRitualSeasonId(long uin, long companyRitualSeasonId) {

        Optional<JpaRitualPackage> ritualPackageOptional =  ritualPackageRepository.findByApplicantPackagesApplicantUinAndCompanyRitualSeasonId(uin,companyRitualSeasonId);

        if (ritualPackageOptional.isPresent()) {
            JpaRitualPackage ritualPackage = ritualPackageOptional.get();


            ritualPackage.getApplicantPackages().size();
            ritualPackage.getCompanyRitualSeason();
            ritualPackage.getApplicantPackages().forEach(e-> {e.getApplicantPackageHousings().size();});
            ritualPackage.getPackageTransportations().size();
            ritualPackage.getPackageHousings().size();
            if(CollectionUtils.isNotEmpty(ritualPackage.getPackageHousings())){
                ritualPackage.getPackageHousings().forEach(ph -> ph.getPackageCatering().size());
                ritualPackage.getPackageHousings().forEach(ph -> ph.getPackageCatering().forEach(
                        c -> c.getApplicantPackageCaterings().size()
                ));
            }

            return getMapper().fromEntity(ritualPackage, mappingContext);
        }

        return null;
    }

    public List<ApplicantPackageCateringDto> findPackageCateringFromRitualPackage(long uin, long applicantPackageId) {
        List<ApplicantPackageCateringDto> applicantPackageCateringDtos = new ArrayList<>();
        ApplicantPackageDto applicantPackage = applicantPackageService.findOne(applicantPackageId);
        List<PackageHousingDto> packageHousingDtos = packageHousingService.findByRitualPackageId(applicantPackage.getRitualPackage().getId());

        Optional<PackageHousingDto> packageHousingDto = packageHousingDtos.stream()
                .filter(p -> LocalDateTime.now().isAfter(DateUtils.convertToLocalDate(p.getValidityStart()))
                        && LocalDateTime.now().isBefore(DateUtils.convertToLocalDate(p.getValidityEnd()))).findFirst();
        if (!packageHousingDto.isPresent()) {
            return applicantPackageCateringDtos;
        }

        //today's catering
        applicantPackageCateringDtos = applicantPackageCateringService.findAllByApplicantPackageApplicantUinAndPackageCateringPackageHousingId(uin, packageHousingDto.get().getId());

        return applicantPackageCateringDtos.stream().sorted(Comparator.comparing(o -> o.getPackageCatering().getMealTime())).collect(Collectors.toList());
    }
}
