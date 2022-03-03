/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the camera domain.
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CameraDto implements Serializable {

    private static final long serialVersionUID = -8960778295403218626L;

    private long id;
    private String status;
    private String url;
    private int seasonYear;
    private int areaCode;
    private Date creationDate;
    private Date updateDate;
}
