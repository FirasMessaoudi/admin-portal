package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaHousingCategoryLookup;
import com.elm.shj.admin.portal.services.dto.HousingCategoryLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling housing category lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
public class HousingCategoryLookupService extends GenericService<JpaHousingCategoryLookup, HousingCategoryLookupDto, Long> {
}
