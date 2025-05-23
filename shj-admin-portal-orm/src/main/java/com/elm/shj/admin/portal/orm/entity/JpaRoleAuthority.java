/*
 *  Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_role_authority database table.
 *
 * @author ahmad flaifel
 * @since 1.8.0
 */
@Entity
@Table(name = "shc_role_authority")
@NamedQuery(name = "JpaRoleAuthority.findAll", query = "SELECT j FROM JpaRoleAuthority j")
@Getter
@Setter
@NoArgsConstructor
public class JpaRoleAuthority implements Serializable {

    private static final long serialVersionUID = -6656022888548145405L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private JpaRole role;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private JpaAuthorityLookup authority;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;
}
