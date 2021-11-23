package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaIncidentTypeLookup;
import com.elm.shj.admin.portal.services.dto.IncidentTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Service handling Incident type lookup
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
public class IncidentTypeLookupService extends GenericService<JpaIncidentTypeLookup, IncidentTypeLookupDto, Long> {
}
