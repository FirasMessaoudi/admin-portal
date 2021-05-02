/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaRelativeRelationshipLookup;
import com.elm.shj.admin.portal.services.dto.RelativeRelationshipLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling card status lookup
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
public class RelativeRelationshipLookupService extends GenericService<JpaRelativeRelationshipLookup, RelativeRelationshipLookupDto, Long> {
}
