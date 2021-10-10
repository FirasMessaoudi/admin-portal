package com.elm.shj.admin.portal.services.dto;

import lombok.*;

/**
 * class for  Password Expiry Notification Request Parameter Values from the user
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordExpiryNotificationRequestParameterValue {

    private long userId;
    private long uin;
    private String userName;
    private String userLang;
    private int dayDiff;
}
