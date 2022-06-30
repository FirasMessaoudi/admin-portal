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
 * The persistent class for the shc_applicant_incident database table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_incident_attachment")
@NamedQuery(name = "JpaIncidentAttachmentLite.findAll", query = "SELECT j FROM JpaIncidentAttachmentLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaIncidentAttachmentLite implements Serializable {

    private static final long serialVersionUID = 6286463714119294368L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "file_path")
    private String filePath;

    @OneToOne
    @JoinColumn(name = "applicant_incident_id")
    private JpaApplicantIncidentLite applicantIncident;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }
}
