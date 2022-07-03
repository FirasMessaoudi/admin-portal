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
 * The persistent class for the ReadinessSiteCampQuestionDescription.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ReadinessSiteCampQuestionDescriptionDto implements Serializable {


    private static final long serialVersionUID = 6607356202186249150L;
    private long id;


   private String questionDescriptionCode;


   private String questionCode;

   private String siteCode;


   private String campCategoryCode;


   private Date creationDate;


   private Date updateDate;




}