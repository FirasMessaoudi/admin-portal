package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicantCardDetails {


    private String hamlahPackageCode;
    private String tafweejCode;
    private String zoneCode;
    private String groupCode;
    private String unitCode;
    private int hijriSeason;
}
