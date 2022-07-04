package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Value object class for returned applicant.
 *
 * @author Jarray Jaafer
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicantVo implements Serializable {

    private static final long serialVersionUID = -7269254967366204060L;

    private String fullNameAr;
    private String fullNameEn;
    private String uin;
    private double lat;
    private double lng;
    private String photo;
    private String idNumber;
    private String passportNumber;
    private String gender;


    public ApplicantVo(String fullNameAr, String fullNameEn, String uin, String photo, String idNumber,
             String passportNumber) {
        this.fullNameAr = fullNameAr;
        this.fullNameEn = fullNameEn;
        this.uin = uin;
        this.photo = photo;
        this.idNumber= idNumber;
        this.passportNumber= passportNumber;

    }

    public ApplicantVo(String fullNameAr, String fullNameEn, String uin, String photo, String idNumber,
                       String passportNumber, String gender) {
        this.fullNameAr = fullNameAr;
        this.fullNameEn = fullNameEn;
        this.uin = uin;
        this.photo = photo;
        this.idNumber= idNumber;
        this.passportNumber= passportNumber;
        this.gender = gender;

    }

    public ApplicantVo(String uin,Double lat, Double lng) {
        this.uin = uin;
        if (lat == null || lng == null) {
            this.lat = -200.0;
            this.lng = -200.0;
        } else {
            this.lat = lat.doubleValue();
            this.lng = lng.doubleValue();
        }
    }


}
