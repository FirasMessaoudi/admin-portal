package com.elm.shj.admin.portal.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicantCardSearchCriteriaDto implements Serializable {
    private String uin;
    private String idNumber;
    private String passportNumber;

}
