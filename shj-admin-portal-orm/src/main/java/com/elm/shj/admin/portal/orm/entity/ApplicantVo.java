package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Value object class for returned applicant.
 *
 * @author Jarray Jaafer
 * @since 1.4.0
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

    public ApplicantVo(String fullNameAr, String fullNameEn, String uin, String photo, Double lat, Double lng) {
        this.fullNameAr = fullNameAr;
        this.fullNameEn = fullNameEn;
        this.uin = uin;
        this.photo = photo;
        if (lat == null || lng == null) {
            this.lat = 0.0;
            this.lng = 0.0;
        } else {
            this.lat = lat.doubleValue();
            this.lng = lng.doubleValue();
        }
    }


}
