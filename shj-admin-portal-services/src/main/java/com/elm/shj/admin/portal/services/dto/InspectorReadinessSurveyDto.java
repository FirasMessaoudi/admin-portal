/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the Inspector Readiness Survey domain.
 *
 * @author salzoubi
 * @since 1.1.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InspectorReadinessSurveyDto implements Serializable {


    private static final long serialVersionUID = 7638674006205711476L;
    private long id;

    String suin;


    private String campNumber;


    private String campSiteCode;


    private String campCategoryCode;


    private String internalCompanyCode;


    private String establishmentCompanyCode;


    private String serviceGroupCompanyCode;


    private Date creationDate;





}