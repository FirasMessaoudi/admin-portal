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
import java.util.List;

/**
 * The persistent class for the shc_package_transportation database table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_package_transportation")
@NamedQuery(name = "JpaPackageTransportation.findAll", query = "SELECT j FROM JpaPackageTransportation j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPackageTransportation implements Serializable {


    private static final long serialVersionUID = -2449410033454976614L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;


    @Column(name = "type_code")
    private String typeCode;


    @Column(name = "validity_start")
    private Date validityStart;
    @Column(name = "validity_end")
    private Date validityEnd;

    @Column(name = "location_from_name_ar")
    private String locationFromNameAr;

    @Column(name = "location_from_name_en")
    private String locationFromNameEn;

    @Column(name = "location_to_name_ar")
    private String locationToNameAr;

    @Column(name = "location_to_name_en")
    private String locationToNameEn;

    @Column(name = "ritual_step_code")
    private String ritualStepCode;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;


    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "packageTransportation")
    private List<JpaApplicantPackageTransportation> applicantPackageTransportations;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private JpaRitualPackage ritualPackage;


    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }


}
