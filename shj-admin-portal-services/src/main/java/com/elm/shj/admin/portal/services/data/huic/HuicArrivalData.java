/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * class for arrival data huic.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@WithApplicant
@NoArgsConstructor
@Getter
@Setter
public class HuicArrivalData implements Serializable {


    private static final long serialVersionUID = -2526608219169871962L;
    @IdNumber(minLength = 10, maxLength = 16, ninOrIqama = true)
    private Long idNumber;
    @PassportNumber
    private String passportNo;

    @NotNull(message = "validation.data.constraints.msg.20001")
    @CountryCode
    private Long nationality;
    @NotNull(message = "validation.data.constraints.msg.20001")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDateTime;
    @WithArrivalCity
    private Integer arrivalCity;


}
