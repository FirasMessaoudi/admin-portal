package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackageBasicWithDetails;
import com.elm.shj.admin.portal.orm.repository.RitualPackageBasicWithDetailsRepository;
import com.elm.shj.admin.portal.services.dto.RitualPackageBasicWithDetailsDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RitualPackageBasicWithDetailsService extends GenericService<JpaRitualPackageBasicWithDetails, RitualPackageBasicWithDetailsDto, Long> {

    private final RitualPackageBasicWithDetailsRepository ritualPackageBasicWithDetailsRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public RitualPackageBasicWithDetailsDto findByReferenceAndRitualTypeAndSeason(String referenceCode, String typeCode, int year) {
        log.info("Start findByReferenceAndRitualTypeAndSeason for {} reference code, {} ritual type code and {} season year.", referenceCode, typeCode, year);
        Optional<JpaRitualPackageBasicWithDetails> ritualPackage = ritualPackageBasicWithDetailsRepository.findIdByReferenceNumberAndRitualTypeAndSeason(referenceCode, typeCode, year);
        if (ritualPackage.isPresent()) {
            log.info("Found a ritual package with {} id.", ritualPackage.get().getId());
            return getMapper().fromEntity(ritualPackage.get(), mappingContext);
        }
        log.info("Finish findByReferenceAndRitualTypeAndSeason for {} reference code, {} ritual type code and {} season year and no ritual package is found.", referenceCode, typeCode, year);
        return null;
    }
}
