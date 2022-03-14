/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaSurveyTypeLookup;
import com.elm.shj.admin.portal.services.dto.SurveyTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling Survey Type Lookup
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
public class SurveyTypeLookupService extends GenericService<JpaSurveyTypeLookup, SurveyTypeLookupDto, Long> {
}
