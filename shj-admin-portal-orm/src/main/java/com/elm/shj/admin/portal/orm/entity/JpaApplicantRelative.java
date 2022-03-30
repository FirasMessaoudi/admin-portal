/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_applicant_relative database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_relative")
@NamedQuery(name = "JpaApplicantRelative.findAll", query = "SELECT j FROM JpaApplicantRelative j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantRelative implements Serializable {

    private static final long serialVersionUID = 6481783740067590182L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "relationship_code")
    private String relationshipCode;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private JpaApplicant applicant;

    @ManyToOne
    @JoinColumn(name = "relative_applicant_id")
    private JpaApplicant relativeApplicant;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @Column(name = "data_request_record_id")
    private Long dataRequestRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_ritual_id")
    private JpaApplicantRitual applicantRitual;

    @Column(name = "package_reference_number")
    private String packageReferenceNumber;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
        upperCase();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
        upperCase();
    }

    private void upperCase() {
        relationshipCode = StringUtils.upperCase(relationshipCode);
    }

}
