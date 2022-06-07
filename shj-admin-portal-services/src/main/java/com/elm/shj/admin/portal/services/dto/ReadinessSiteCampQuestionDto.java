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
 * The persistent class for the ReadinessSiteCampQuestion.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ReadinessSiteCampQuestionDto implements Serializable {


    private static final long serialVersionUID = -6344153557282715952L;
    private long id;


    private String questionCode;

    private String siteCode;

    private String campCategoryCode;





    private int questionOrder;


    private Date creationDate;


    private Date updateDate;



}