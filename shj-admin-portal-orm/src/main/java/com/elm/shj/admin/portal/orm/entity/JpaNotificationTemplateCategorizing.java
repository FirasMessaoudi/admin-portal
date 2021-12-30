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
 * The persistent class for the shc_notification_template_categorizing database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_notification_template_categorizing")
@NamedQuery(name = "JpaNotificationTemplateCategorizing.findAll", query = "SELECT j FROM JpaNotificationTemplateCategorizing j")
@Getter
@Setter
@NoArgsConstructor
public class JpaNotificationTemplateCategorizing implements Serializable {

    private static final long serialVersionUID = 6943741740059680258L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @OneToOne
    @JoinColumn(name = "notification_template_id")
    private JpaNotificationTemplate notificationTemplate;

    @Column(name = "camp_id")
    private Long campId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "nationality_code")
    private String nationalityCode;

    @Column(name = "min_age")
    private Integer minAge;

    @Column(name = "max_age")
    private Integer maxAge;

    private String gender;

    @Column(name = "selected_applicants")
    private String selectedApplicants;

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
