/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant emergency data upload domain.
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantEmergencyDataUploadDto implements Serializable {

    private static final long serialVersionUID = 8543573832563308467L;

    private long id;
    private String idNumber;
    private String passportNumber;
    private Long dateOfBirthHijri;
    private Date dateOfBirthGregorian;
    private String packageReferenceNumber;
    private String seatNumber;
    private String busNumber;
    private Date creationDate;
}
