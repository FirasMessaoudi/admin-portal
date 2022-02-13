package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


/**
 * PrintRequests filter value object.
 *
 * @author Jarray Jaafer
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class PrintRequestCriteriaDto implements Serializable {

    private static final long serialVersionUID = 6344767991328308660L;

    private String requestNumber;
    private Integer season;
    private int batchNumber;
    private String cardNumber;
    private String companyCode;
    private String description;
    private String idNumber;
    private Date fromDate;
    private Date toDate;
    private String ritualTypeCode;
    private String statusCode;

}
