package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
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
@Data
@NoArgsConstructor
public class JpaApplicantPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "applicant_uin", nullable = false)
    private long applicantUin;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "ritual_package_id")
    private JpaRitualPackage ritualPackage;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "packageCatering")
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
