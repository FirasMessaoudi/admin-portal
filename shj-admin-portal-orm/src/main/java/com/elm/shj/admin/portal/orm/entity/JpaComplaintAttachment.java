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

/**
 * The persistent class for the shc_applicant_complaint database table.
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Entity
@Table(name = "shc_complaint_attachment")
@NamedQuery(name = "JpaComplaintAttachment.findAll", query = "SELECT j FROM JpaComplaintAttachment j")
@Getter
@Setter
@NoArgsConstructor
public class JpaComplaintAttachment implements Serializable {

    private static final long serialVersionUID = 7776410571220637393L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "file_path")
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "applicant_complaint_id")
    private JpaApplicantComplaint applicantComplaint;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }
}
