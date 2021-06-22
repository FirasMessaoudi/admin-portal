/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_applicant_health_special_needs database table.
 *
 * @author Slim Ben Hadj
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_health_special_needs")
@NamedQuery(name = "JpaApplicantHealthSpecialNeeds.findAll", query = "SELECT j FROM JpaApplicantHealthSpecialNeeds j")
@Data
@NoArgsConstructor
public class JpaApplicantHealthSpecialNeeds implements Serializable {

    private static final long serialVersionUID = 8963168397661232916L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "applicant_health_id")
    private JpaApplicantHealth applicantHealth;

    @Column(name = "special_need_type_code", nullable = false)
    private String specialNeedTypeCode;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;


    @PrePersist
    public void prePersist() {
        creationDate = new Date();
        upperCase();
    }

    @PreUpdate
    public void preUpdate() {
        upperCase();
    }

    private void upperCase() {
        specialNeedTypeCode = StringUtils.upperCase(specialNeedTypeCode);
    }
}
