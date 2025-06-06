/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.notification;

import lombok.*;

/**
 * User new notifications counts value object
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNewNotificationsCountVo {

    private int userSpecificNewNotificationsCount;
    private int userNotSpecificNewNotificationsCount;
}
