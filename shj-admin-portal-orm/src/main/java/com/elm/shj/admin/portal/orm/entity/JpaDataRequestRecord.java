/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the sha_data_request_record database table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_data_request_record")
@NamedQuery(name = "JpaDataRequestRecord.findAll", query = "SELECT j FROM JpaDataRequestRecord j")
@Data
@NoArgsConstructor
public class JpaDataRequestRecord implements Serializable {

    private static final long serialVersionUID = 6481123451234590182L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "create_data_request_id")
    private Long createDataRequestId;

    @Column(name = "last_update_data_request_id")
    private Long lastUpdateDataRequestId;

    @Column(name = "create_data_request_row_num")
    private Long createDataRequestRowNum;

    @Column(name = "last_update_data_request_row_num")
    private Long lastUpdateDataRequestRowNum;

    @Column(name = "item_id")
    private long itemId;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

}
