/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * dto class for user location domain.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserLocationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String userId;

    private String userType;

    private Double latitude;

    private Double longitude;

    private Double altitude;

    private double heading;

    private double speed;
    private double SpeedAccuracy;
    private double locationAccuracy;

    private String timestamp;
    private LocalDateTime gpsTime;

    private Date creationDate;
}
