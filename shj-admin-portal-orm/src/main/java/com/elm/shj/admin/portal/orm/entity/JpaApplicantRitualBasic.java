/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the shc_applicant_ritual database table.
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_applicant_ritual")
@NamedQuery(name = "JpaApplicantRitualBasic.findAll", query = "SELECT j FROM JpaApplicantRitualBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantRitualBasic implements Serializable {

    private static final long serialVersionUID = 8576548128327770418L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    private JpaApplicantBasic applicant;

}
