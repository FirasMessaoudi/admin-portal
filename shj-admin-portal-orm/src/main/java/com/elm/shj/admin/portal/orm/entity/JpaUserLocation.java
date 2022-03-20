/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * The persistent class for the shs_user_location database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_user_location")
@NamedQuery(name = "JpaUserLocation.findAll", query = "SELECT j FROM JpaUserLocation j")
@Setter
@Getter
@NoArgsConstructor
public class JpaUserLocation implements Serializable {


    private static final long serialVersionUID = 3413128593173931801L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "altitude")
    private double altitude;

    @Column(name = "heading")
    private double heading;

    @Column(name = "speed")
    private double speed;

    @Column(name = "speed_accuracy")
    private double speedAccuracy;
    @Column(name = "location_accuracy")
    private double locationAccuracy;

    @Column(name = "gps_time")
    private LocalDateTime gpsTime;

    @Transient
    private String timestamp;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    public void prePersist(){
        creationDate = new Date();

    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }


}
