package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaHousingSiteLookup;
import com.elm.shj.admin.portal.services.dto.HousingSiteLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling housing site lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
public class HousingSiteLookupService extends GenericService<JpaHousingSiteLookup, HousingSiteLookupDto, Long> {
}
