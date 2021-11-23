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
 * The persistent class for the shc_applicant_chat_contact database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_chat_contact")
@NamedQuery(name = "JpaApplicantChatContact.findAll", query = "SELECT j FROM JpaApplicantChatContact j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantChatContact implements Serializable {

    private static final long serialVersionUID = 7212037715533180866L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String uin;

    @ManyToOne
    @JoinColumn(name = "applicant_ritual_id")
    private JpaApplicantRitual applicantRitual;

    @Column(name = "contact_uin")
    private String contactUin;

    private String alias;

    @Column(name = "photo_file_path")
    private String photoFilePath;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "contact_type_code")
    private String contactTypeCode;

    @Column(name = "system_defined")
    private Boolean systemDefined;

    private Boolean deleted;

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
