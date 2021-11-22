package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackage;
import com.elm.shj.admin.portal.orm.repository.RitualPackageRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantPackageDto;
import com.elm.shj.admin.portal.services.dto.PackageCateringDto;
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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RitualPackageService extends GenericService<JpaRitualPackage, RitualPackageDto, Long> {

    private final RitualPackageRepository ritualPackageRepository;

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
                        c-> c.getApplicantPackageCaterings().size()
                ));
            }

            return getMapper().fromEntity(ritualPackage, mappingContext);
        }

        return null;
    }

    public Set<PackageCateringDto> findPackageCateringFromRitualPackage(long uin, RitualPackageDto ritualPackage){
        Set<PackageCateringDto> packageCateringDtoList = new HashSet<>();

        Optional<ApplicantPackageDto> applicantPackageDto = ritualPackage.getApplicantPackages().stream()
                .filter(p-> p.getApplicantUin() == uin)
                .filter(p -> LocalDateTime.now().minusDays(1).isBefore(DateUtils.convertToLocalDate(p.getEndDate()))
                        && LocalDateTime.now().plusDays(1).isAfter(DateUtils.convertToLocalDate(p.getStartDate()))).findFirst();
        if(!applicantPackageDto.isPresent()){
           return packageCateringDtoList;
        }

        Optional<PackageHousingDto> packageHousingDto = ritualPackage.getPackageHousings().stream()
                .filter(p -> LocalDateTime.now().isAfter(DateUtils.convertToLocalDate(p.getValidityStart()))
                        && LocalDateTime.now().isBefore(DateUtils.convertToLocalDate(p.getValidityEnd()))).findFirst();
        if(!packageHousingDto.isPresent()){
            return packageCateringDtoList;
        }

        ApplicantPackageDto applicantPackage = applicantPackageDto.get();
        PackageHousingDto packageHousing = packageHousingDto.get();
        packageHousing.getPackageCatering().forEach(c-> {
            if(c.getApplicantPackageCaterings().stream()
                    .filter(pc-> pc.getApplicantPackage().getId() == applicantPackage.getId()).collect(Collectors.toList()).size()==0){
                if(c.isDefault())
                    packageCateringDtoList.add(c);
            }else{
                StringBuilder descriptionEn = new StringBuilder();
                StringBuilder descriptionAr = new StringBuilder();
                c.getApplicantPackageCaterings().stream().filter(pc-> pc.getApplicantPackage().getId() == applicantPackage.getId()).forEach(pc -> {
                    descriptionEn.append(pc.getOptionEn()).append(" , ");
                    descriptionAr.append(pc.getOptionAr()).append(" , ");
                });
                c.setDescriptionEn(descriptionEn.substring(0,descriptionEn.length()-3));
                c.setDescriptionAr(descriptionAr.substring(0,descriptionAr.length()-3));
                packageCateringDtoList.add(c);
            }

        });
        return packageCateringDtoList;
    }
}
