/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the DecisionRule domain.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DecisionRuleDto implements Serializable {

    private static final long serialVersionUID = 4778905193540474532L;

    private long id;
    private String labelAr;
    private String labelEn;
    private String dmn;
    private Date creationDate;
    private Date updateDate;
}
