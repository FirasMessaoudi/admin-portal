/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant ritual domain.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantRitualVo implements Serializable {

    private static final long serialVersionUID = 8699536906254699723L;

    public ApplicantRitualVo(String fullNameAr, String fullNameEn, String uin) {
        applicant = new ApplicantBasicInfoVo(fullNameAr, fullNameEn, uin);
    }

    public ApplicantRitualVo(String fullNameAr, String fullNameEn, String uin, String preferredLanguage) {
        applicant = new ApplicantBasicInfoVo(fullNameAr, fullNameEn, uin, preferredLanguage);
    }

    private long id;

    private ApplicantBasicInfoVo applicant;

    private String packageReferenceNumber;

    private String groupReferenceNumber;

    private String visaNumber;

    private String permitNumber;

    private String insuranceNumber;

    private String borderNumber;

    private String busNumber;

    private String seatNumber;

    private String roomNumber;

    private String bedNumber;

    private Date creationDate;

    private Date updateDate;

    private Long dataRequestRecordId;

    private String typeCode;
}
