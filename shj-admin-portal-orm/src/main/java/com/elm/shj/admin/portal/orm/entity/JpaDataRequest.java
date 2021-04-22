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
 * The persistent class for the sha_data_request database table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Entity
@Table(name = "sha_data_request")
@NamedQuery(name = "JpaDataRequest.findAll", query = "SELECT j FROM JpaDataRequest j")
@Data
@NoArgsConstructor
public class JpaDataRequest implements Serializable {

    private static final long serialVersionUID = 6481123450067590182L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @Column(name = "original_source_path", nullable = false)
    private String originalSourcePath;

    @Column(name = "error_file_path", nullable = false)
    private String errorFilePath;

    private String channel;

    @ManyToOne
    @JoinColumn(name = "data_segment_id")
    private JpaDataSegment dataSegment;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private JpaDataRequestStatusLookup status;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

}
