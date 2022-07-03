package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.NotificationSearchCriteriaDto;
import com.elm.shj.admin.portal.services.dto.NotificationTemplateDto;
import com.elm.shj.admin.portal.services.notification.NotificationTemplateService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)
@Slf4j
@RestController
@RequestMapping(Navigation.API_USER_NOTIFICATION_INTEGRATION)
@RequiredArgsConstructor
public class UserNotificationWsController {
    private final static String USER_DEFINED = "USER_DEFINED";
    private final NotificationTemplateService notificationTemplateService;

    @PostMapping
    public ResponseEntity<WsResponse<?>> createNotification(@RequestBody NotificationTemplateDto notificationTemplate) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(notificationTemplateService.create(notificationTemplate)).build());
    }

    @PostMapping("/update")
    public ResponseEntity<WsResponse<?>> updateNotification(@RequestBody NotificationTemplateDto notificationTemplate) {
        log.debug("Handler for {}", "Update User Defined Notification");
        NotificationTemplateDto savedNotificationTemplate;
        if (new Date().after(notificationTemplate.getSendingDate())) {
            savedNotificationTemplate = notificationTemplateService.updateDescription(notificationTemplate);
        } else {
            savedNotificationTemplate = notificationTemplateService.updateUserDefined(notificationTemplate);
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(Objects.requireNonNull(savedNotificationTemplate)).build());
    }

    @PostMapping("/find")
    public ResponseEntity<WsResponse<?>> findNotificationTemplate(@RequestBody NotificationSearchCriteriaDto notificationSearchCriteria, Pageable pageable) {

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(notificationTemplateService.findByFilter(notificationSearchCriteria, USER_DEFINED, pageable)).build());
    }

    @GetMapping("/{notificationTemplateId}")
    public ResponseEntity<WsResponse<?>> findNotificationTemplateById(@PathVariable Long notificationTemplateId) {

        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(notificationTemplateService.findOne(notificationTemplateId)).build());
    }

}
