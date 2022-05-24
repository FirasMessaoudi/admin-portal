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
 * The persistent class for the shc_applicant_supplication database table.
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_user_supplication")
@NamedQuery(name = "shc_user_supplication", query = "SELECT j FROM JpaUserSupplication  j")
@Getter
@Setter
@NoArgsConstructor
public class JpaUserSupplication implements Serializable {
    private static final long serialVersionUID = -1523933134279344057L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "digital_id",nullable = false,updatable = false)
    private String digitalId;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "lang", nullable = false)
    private String lang;

    @Column(name = "label", nullable = false)
    private String label;

    private boolean deleted;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

}
