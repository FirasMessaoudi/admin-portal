/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateStatusLookup;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateStatusLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling notification template status lookup
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Service
@Slf4j
public class NotificationTemplateStatusLookupService extends GenericService<JpaNotificationTemplateStatusLookup, NotificationTemplateStatusLookupDto, Long> {
}
