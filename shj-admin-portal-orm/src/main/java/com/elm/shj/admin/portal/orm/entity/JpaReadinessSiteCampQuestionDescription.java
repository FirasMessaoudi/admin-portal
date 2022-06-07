/*
 *  Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_readiness_site_camp_question_description database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_readiness_site_camp_question_description")
@NamedQuery(name = "JpaReadinessSiteCampQuestionDescription.findAll", query = "SELECT j FROM JpaReadinessSiteCampQuestionDescription j")
@Getter
@Setter
@NoArgsConstructor
public class JpaReadinessSiteCampQuestionDescription implements Serializable {


    private static final long serialVersionUID = 8874145864083571612L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "question_description_code")
    String questionDescriptionCode;

    @Column(name = "question_code")
    String questionCode;
    
    @Column(name = "site_code")
    String siteCode;

    @Column(name = "camp_category_code")
    String campCategoryCode;
    
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