/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaSurveyTypeLookup;
import com.elm.shj.admin.portal.orm.repository.SurveyTypeLookupRepository;
import com.elm.shj.admin.portal.services.dto.SurveyTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
