package com.elm.shj.admin.portal.services.notification;

import com.elm.shj.admin.portal.orm.entity.JpaNotificationTemplate;
import com.elm.shj.admin.portal.orm.repository.NotificationTemplateRepository;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service handling  Notification Template
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NotificationTemplateService extends GenericService<JpaNotificationTemplate, NotificationTemplateDto, Long> {

        private final NotificationTemplateRepository notificationTemplateRepository;

        /**
         * Finds Notification Template By Name
         *
         * @param nameCode the nameCode of the notification template  to find
         * @return the found Notification Template or empty structure
         */
        public Optional<NotificationTemplateDto> findNotificationTemplateByNameCode(String nameCode) {
                JpaNotificationTemplate jpaNotificationTemplate = notificationTemplateRepository.findByNameCodeAndEnabledTrue(nameCode);
                return (jpaNotificationTemplate != null) ? Optional.of(getMapper().fromEntity(jpaNotificationTemplate, mappingContext)) : Optional.empty();
        }

}
