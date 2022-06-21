package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaRitualPackageBasicWithDetails;
import com.elm.shj.admin.portal.orm.repository.RitualPackageBasicWithDetailsRepository;
import com.elm.shj.admin.portal.services.dto.RitualPackageBasicWithDetailsDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RitualPackageBasicWithDetailsService extends GenericService<JpaRitualPackageBasicWithDetails, RitualPackageBasicWithDetailsDto, Long> {

    private final RitualPackageBasicWithDetailsRepository ritualPackageRepository;
}
