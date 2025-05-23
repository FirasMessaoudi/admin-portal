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
 * The persistent class for the shc_applicant_package database table.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_package")
@NamedQuery(name = "JpaApplicantPackage.findAll", query = "SELECT j FROM JpaApplicantPackage j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantPackage implements Serializable {

    private static final long serialVersionUID = 6299760561882649100L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;
//TODO should be String not long
    @Column(name = "applicant_uin", nullable = false)
    private long applicantUin;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @ManyToOne
    @JoinColumn(name = "ritual_package_id")
    private JpaRitualPackage ritualPackage;

    @OneToOne(orphanRemoval = true, mappedBy = "applicantPackage")
    private JpaApplicantRitual applicantRitual;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantPackage")
    private List<JpaApplicantPackageCatering> applicantPackageCaterings;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantPackage")
    private List<JpaApplicantPackageTransportation> applicantPackageTransportations;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "applicantPackage")
    private List<JpaApplicantPackageHousing> applicantPackageHousings;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
