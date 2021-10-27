package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaReligiousOccasionsDayLookup;
import com.elm.shj.admin.portal.services.dto.ReligiousOccasionsDayLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Service handling religious occasions day lookup
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
public class ReligiousOccasionsDayLookupService extends GenericService<JpaReligiousOccasionsDayLookup, ReligiousOccasionsDayLookupDto, Long> {
}
