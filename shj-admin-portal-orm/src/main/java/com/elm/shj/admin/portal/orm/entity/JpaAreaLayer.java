/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "shc_area_layers")
@NamedQuery(name = "JpaAreaLayer.findAll", query = "SELECT j FROM JpaAreaLayer j")
@Getter
@Setter
@NoArgsConstructor
public class JpaAreaLayer implements Serializable {

    private static final long serialVersionUID = 8576548128327770418L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "layer")
    private String layer;

    @Column(name = "parent_layer_id")
    private Integer parentLayerId;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}
