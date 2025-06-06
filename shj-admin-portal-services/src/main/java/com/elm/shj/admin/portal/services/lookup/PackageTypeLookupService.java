package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaPackageTypeLookup;
import com.elm.shj.admin.portal.services.dto.PackageTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Service handling package type lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
public class PackageTypeLookupService extends GenericService<JpaPackageTypeLookup, PackageTypeLookupDto, Long> {
}
