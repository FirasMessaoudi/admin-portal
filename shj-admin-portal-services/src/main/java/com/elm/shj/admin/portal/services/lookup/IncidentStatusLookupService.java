package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaIncidentStatusLookup;
import com.elm.shj.admin.portal.services.dto.IncidentStatusLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Service handling Incident status lookup
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
public class IncidentStatusLookupService extends GenericService<JpaIncidentStatusLookup, IncidentStatusLookupDto, Long> {
}
