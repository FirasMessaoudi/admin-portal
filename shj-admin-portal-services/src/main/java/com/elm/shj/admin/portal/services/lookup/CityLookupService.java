package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaCityLookup;
import com.elm.shj.admin.portal.services.dto.CityLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Service handling Complaint status lookup
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
public class CityLookupService extends GenericService<JpaCityLookup, CityLookupDto, Long> {
}
