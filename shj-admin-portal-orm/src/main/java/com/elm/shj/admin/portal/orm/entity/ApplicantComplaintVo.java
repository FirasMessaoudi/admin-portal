/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dto class for applicant complaint domain.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicantComplaintVo implements Serializable {

    private static final long serialVersionUID = 5885377938235938843L;

    private long id;
    private ApplicantRitualVo applicantRitual;
    private String statusCode;
    private String referenceNumber;
    private String typeCode;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @Size(min = 1, max = 500)
    private String description;
    private Double locationLat;
    private Double locationLng;
    private String resolutionComment;
    private ComplaintAttachmentVo complaintAttachment;
    private Date creationDate;
    private Date updateDate;
    private String areaCode;
    private String city;
    private String campNumber;
    private String crmTicketNumber;
    private long count;

    public ApplicantComplaintVo(Long id, String referenceNumber, Date creationDate, String statusCode, String typeCode, String fullNameAr, String fullNameEn, String uin, Long count){
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.creationDate = creationDate;
        this.statusCode = statusCode;
        this.typeCode = typeCode;
        applicantRitual = new ApplicantRitualVo(fullNameAr,fullNameEn,uin);
        this.count = count;
    }
}
