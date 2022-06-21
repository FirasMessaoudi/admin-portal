package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaCityLookup;
import com.elm.shj.admin.portal.services.dto.CityLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Service handling city lookup
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Service
@Slf4j
public class CityLookupService extends GenericService<JpaCityLookup, CityLookupDto, Long> {
}
