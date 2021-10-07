package com.elm.shj.admin.portal.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplicantCardSearchCriteriaDto implements Serializable {
    private String uin;
    private String idNumber;
    private String passportNumber;

}
