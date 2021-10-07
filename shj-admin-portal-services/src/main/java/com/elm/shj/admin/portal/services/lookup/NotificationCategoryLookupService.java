package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationCategoryLookup;
import com.elm.shj.admin.portal.services.dto.NotificationCategoryLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service handling notification category lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
public class NotificationCategoryLookupService extends GenericService<JpaNotificationCategoryLookup, NotificationCategoryLookupDto, Long> {
}
