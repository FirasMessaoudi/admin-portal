package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaTransportationTypeLookup;
import com.elm.shj.admin.portal.services.dto.TransportationTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling transportation type lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
public class TransportationTypeLookupService extends GenericService<JpaTransportationTypeLookup, TransportationTypeLookupDto, Long> {
}
