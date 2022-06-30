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
    private String mobileNumber;
    private Boolean crmStatusUpdated;
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

    public ApplicantComplaintVo(Long id, String referenceNumber, Date creationDate, String statusCode, String typeCode, String city, String description, Double locationLat, Double locationLng, String campNumber, Long complaintAttachmentId, String filePath, String resolutionComment, Date updateDate, String fullNameAr, String fullNameEn, String uin, String preferredLanguage){
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.creationDate = creationDate;
        this.statusCode = statusCode;
        this.typeCode = typeCode;
        this.description = description;
        this.locationLat = locationLat;
        this.locationLng = locationLng;
        this.city = city;
        this.campNumber = campNumber;
        if (complaintAttachmentId != null) {
            this.complaintAttachment = new ComplaintAttachmentVo(complaintAttachmentId, filePath);
        }
        this.resolutionComment = resolutionComment;
        this.updateDate = updateDate;
        applicantRitual = new ApplicantRitualVo(fullNameAr,fullNameEn,uin,preferredLanguage);
    }

    public ApplicantComplaintVo(Long id,String referenceNumber, String typeCode, String statusCode, String city, String resolutionComment,
                                Boolean crmStatusUpdated, String crmTicketNumber, String description,Double locationLat, Double locationLng,
                                String mobileNumber,Date creationDate, Long complaintAttachmentId, String fullNameAr,
                                String fullNameEn,
                                String fullNameOrigin, String idNumber, String passportNumber, Long dateOfBirthHijri,
                                Date dateOfBirthGregorian, String gender, String nationalityCode,String email,
                                String localMobileNumber, String intlMobileNumber,String countryCode, String uin)
    {
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.creationDate = creationDate;
        this.typeCode = typeCode;
        this.statusCode = statusCode;
        this.description = description;
        this.locationLat = locationLat;
        this.locationLng = locationLng;
        this.city = city;
        this.crmStatusUpdated = crmStatusUpdated;
        this.crmTicketNumber = crmTicketNumber;
        this.resolutionComment = resolutionComment;
        this.mobileNumber = mobileNumber;
        if (complaintAttachmentId != null) {
            this.complaintAttachment = new ComplaintAttachmentVo(complaintAttachmentId, null);
        }
        applicantRitual = new ApplicantRitualVo(fullNameAr,fullNameEn, fullNameOrigin, idNumber, passportNumber, dateOfBirthHijri, dateOfBirthGregorian, gender, nationalityCode, email, localMobileNumber, intlMobileNumber,countryCode,uin);
    }
}
