/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * PasswordExpiryNotificationRequest class for Password Expiry Notification Request from the user
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordExpiryNotificationRequest implements Serializable {
    private static final long serialVersionUID = 8663675565915427887L;
    private Set<PasswordExpiryNotificationRequestUserParameters> userParametersList;


}
