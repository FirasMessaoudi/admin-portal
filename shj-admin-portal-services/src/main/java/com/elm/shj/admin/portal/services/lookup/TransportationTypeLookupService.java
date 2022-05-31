package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaTransportationTypeLookup;
import com.elm.shj.admin.portal.orm.repository.TransportationTypeLookupRepository;
import com.elm.shj.admin.portal.services.dto.TransportationTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling transportation type lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class TransportationTypeLookupService extends GenericService<JpaTransportationTypeLookup, TransportationTypeLookupDto, Long> {
    private final TransportationTypeLookupRepository transportationTypeLookupRepository;

    public boolean existsByCode(String code) {
        return transportationTypeLookupRepository.existsByCode(code);
    }
}
