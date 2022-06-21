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
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_complaint_attachment")
@NamedQuery(name = "JpaComplaintAttachmentLite.findAll", query = "SELECT j FROM JpaComplaintAttachmentLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaComplaintAttachmentLite implements Serializable {

    private static final long serialVersionUID = 6286463714119294368L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "file_path")
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "applicant_complaint_id")
    private JpaApplicantComplaintLite applicantComplaintLite;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }
}
