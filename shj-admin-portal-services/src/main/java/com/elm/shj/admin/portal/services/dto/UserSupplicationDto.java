/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the applicant supplication domain.
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSupplicationDto implements Serializable {

    private static final long serialVersionUID = -8325398355769557350L;
    private long id;
    private String digitalId;
    private String code;
    private String lang;
    private String label;
    private int totalSupplication;
    private int lastSupplicationNumber;
    private boolean Deleted;
    private Date creationDate;
}
