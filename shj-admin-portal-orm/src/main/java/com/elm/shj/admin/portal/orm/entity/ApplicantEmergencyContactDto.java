/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.*;

/**
 * Dto class for the user emergency contact.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicantEmergencyContactDto {
    private String emergencyContactName;
    private String emergencyContactMobileNumber;
}
