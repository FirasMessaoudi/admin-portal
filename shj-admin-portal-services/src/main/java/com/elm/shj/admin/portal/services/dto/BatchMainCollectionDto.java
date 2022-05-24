/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BatchMainCollectionDto implements Serializable {

    private static final long serialVersionUID = -6056378460337981567L;

    private long id;
    private String referenceNumber;
    private String statusCode;
    private String url;
    private Date creationDate;
    private Date updateDate;
    private List<SubCollectionVO> subCollections;

}
