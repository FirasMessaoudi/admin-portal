/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplateNameLookup;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateNameLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling notification template name lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
public class NotificationTemplateNameLookupService extends GenericService<JpaNotificationTemplateNameLookup, NotificationTemplateNameLookupDto, Long> {
}
