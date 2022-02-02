/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;

/**
 * VO class for the card domain (Applicant/Staff).
 *
 * @author ahmad Ali
 * @since 1.1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardVO implements Serializable, HibernateAwareMapper {
    private static final long serialVersionUID = 8153002399883065619L;

    private long id;
    private String fullNameAr;
    private String fullNameEn;
    private String digitalId;
    private String passportNumber;
    private String idNumber;
    private String nationalityCode;
    private String hamlahPackageCode;
    private String tafweejCode;
    private String referenceNumber;
    private String statusCode;
}
