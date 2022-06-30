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
 * */
@Entity
@Table(name = "shc_complaint_attachment")
@NamedQuery(name = "JpaComplaintAttachmentLite.findAll", query = "SELECT j FROM JpaComplaintAttachmentLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaComplaintAttachmentLite implements Serializable {

    private static final long serialVersionUID = -6421025504092047467L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "file_path")
    private String filePath;

    @OneToOne
    @JoinColumn(name = "applicant_complaint_id")
    private JpaApplicantComplaintLite applicantComplaint;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }
}
