package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Value object class for returned applicantMobileTracking.
 *
 * @author Rammez Imtiaz
 * @since 1.4.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicantMobileTrackingVo implements Serializable {

    private static final long serialVersionUID = -7269254967366204060L;
    private final static String ISO8601_DATE_PATTERN = "yyyy-MM-dd";

    private String companyCode;
    private String nationalityCode;
    private Date lastLoginDate;
    private String uin;
    private double lat;
    private double lng;

    public ApplicantMobileTrackingVo(String companyCode, String nationalityCode, LocalDateTime gpsTime, String uin, Double lat, Double lng) throws Exception{
        this.companyCode = companyCode;
        this.nationalityCode = nationalityCode;
        this.lastLoginDate = Date.from(gpsTime.atZone(ZoneId.systemDefault()).toInstant());
        this.uin = uin;
        if (lat == null || lng == null) {
            this.lat = 0.0;
            this.lng = 0.0;
        } else {
            this.lat = lat.doubleValue();
            this.lng = lng.doubleValue();
        }
    }


}
