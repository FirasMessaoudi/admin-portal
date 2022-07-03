/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.token;

import lombok.Builder;
import lombok.Data;

/**
 * Trusted token request command
 *
 * @author Aymen Dhaoui
 * @since 1.0.0
 */
@Data
@Builder
public class TokenRequestVo {

    private String username;
    private String client_ip;

}
