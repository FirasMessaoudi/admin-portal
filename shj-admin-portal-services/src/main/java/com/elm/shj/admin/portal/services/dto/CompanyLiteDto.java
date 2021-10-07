package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Dto class for the company.
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */

@NoArgsConstructor
@Getter
@Setter
public class CompanyLiteDto implements Serializable {

    private static final long serialVersionUID = -2782740641877406749L;
    private long id;
    private String code;

    private String labelAr;

    private String labelEn;

    private String contactNumber;
}
