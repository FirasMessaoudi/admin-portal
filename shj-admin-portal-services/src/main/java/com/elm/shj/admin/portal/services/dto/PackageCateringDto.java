package com.elm.shj.admin.portal.services.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Dto class for the package catering domain.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@NoArgsConstructor
@Data
public class PackageCateringDto {

    private static final long serialVersionUID = 4099330015218595333L;

    private long id;

    private String mealCode;

    private int mealTime;

    private String mealDescription;

    private String type;

    private String descriptionAr;

    private String descriptionEn;

    private Date creationDate;

    private Date updateDate;

//    @JsonBackReference(value = "packageHousing")
//    private PackageHousingDto packageHousing;

//    @JsonBackReference(value = "applicantPackageCaterings")
//    private List<ApplicantPackageCateringDto> applicantPackageCaterings;

}
