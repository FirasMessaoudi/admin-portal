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
import java.util.List;

/**
 * The persistent class for the shc_data_request database table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_data_request")
@NamedQuery(name = "JpaDataRequest.findAll", query = "SELECT j FROM JpaDataRequest j")
@Getter
@Setter
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

    @Column(name = "item_count")
    private long itemCount;

    @Column(name = "error_count")
    private long errorCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_segment_id")
    private JpaDataSegment dataSegment;

    @OneToMany
    @JoinColumn(name = "last_update_data_request_id")
    private List<JpaDataRequestRecord> records;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private JpaDataRequestStatusLookup status;

    @Column(name = "creation_date", nullable = false, updatable = false)
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
