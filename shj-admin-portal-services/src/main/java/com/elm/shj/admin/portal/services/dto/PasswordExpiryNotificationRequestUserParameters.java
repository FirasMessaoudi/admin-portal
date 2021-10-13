/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
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
public class PasswordExpiryNotificationRequestUserParameters {

    private long userId;
    private String userLang;
    private int daysToExpiry;
}
