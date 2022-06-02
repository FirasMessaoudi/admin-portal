/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the Inspector Readiness Survey Result domain
 *
 * @author salzoubi
 * @since 1.1.0
 */

@Getter
@Setter
@NoArgsConstructor
public class InspectorReadinessSurveyResultDto implements Serializable {


    private static final long serialVersionUID = 4520458744230437008L;

    private long id;


    private int inspectorReadinessSurveyId ;


    private String questionCode ;


    private int rate;


    private Date creationDate;


    private Date updateDate;

}