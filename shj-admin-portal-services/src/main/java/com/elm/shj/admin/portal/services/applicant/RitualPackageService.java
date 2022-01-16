package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackage;
import com.elm.shj.admin.portal.orm.repository.RitualPackageRepository;
import com.elm.shj.admin.portal.services.dto.RitualPackageDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RitualPackageService extends GenericService<JpaRitualPackage, RitualPackageDto, Long> {

    private final RitualPackageRepository ritualPackageRepository;

    public boolean existRitualPackageByReferenceNumber(String referenceNumber) {
        Optional<JpaRitualPackage> ritualPackageOptional = ritualPackageRepository.findByReferenceNumber(referenceNumber);
        return ritualPackageOptional.isPresent();
    }

    public Long findRitualPackageIdByReferenceNumber(String referenceNumber) {
        return ritualPackageRepository.findIdByReferenceNumber(referenceNumber);
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
}
