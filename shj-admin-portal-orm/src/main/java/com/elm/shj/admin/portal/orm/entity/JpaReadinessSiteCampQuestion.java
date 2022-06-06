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
 * The persistent class for the shc_readiness_site_camp_question database table.
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_readiness_site_camp_question")
@NamedQuery(name = "JpaReadinessSiteCampQuestion.findAll", query = "SELECT j FROM JpaReadinessSiteCampQuestion j")
@Getter
@Setter
@NoArgsConstructor
public class JpaReadinessSiteCampQuestion implements Serializable {


    private static final long serialVersionUID = 7249368128031827389L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "question_code")
    String questionCode;
    
    @Column(name = "site_code")
    String siteCode;

    @Column(name = "camp_category_code")
    String campCategoryCode;



    @Column(name = "question_order")
    private int questionOrder;

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