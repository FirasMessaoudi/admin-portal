/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.orm.entity.JpaDataRequestStatusLookup;
import com.elm.shj.admin.portal.orm.entity.JpaDataSegment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
@Data
public class DataRequestStatusLookupDto implements Serializable {

    private static final long serialVersionUID = 4712345693540474532L;

    private long id;
    private String labelAr;
    private String labelEn;
    private Date creationDate;
}
