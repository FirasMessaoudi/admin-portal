/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Staff print request filter value object.
 *
 * @author ahmed Ali
 * @since 1.1.0
 */
@Getter
@Setter
public class StaffPrintRequestFilterVo implements Serializable {

    private static final long serialVersionUID = -8414842137514438188L;

    private String statusCode;
    private String description;
    private int season;
    private String companyCode;
    private int requestNumber;
    private int batchNumber;
    private String printingStartDate;
    private String printingEndDate;
    private String idNumber;
    private String ritualTypeCode;

}
