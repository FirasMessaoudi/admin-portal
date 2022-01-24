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
 * The persistent class for the shc_ritual_package database table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_ritual_package")
@NamedQuery(name = "JpaRitualPackage.findAll", query = "SELECT j FROM JpaRitualPackage j")
@Getter
@Setter
@NoArgsConstructor
public class JpaRitualPackage implements Serializable {
    private static final long serialVersionUID = -8657084839410538572L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "type_code", nullable = false)
    private String typeCode;

    private float price;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "country_id")
    private int countryId;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "company_ritual_season_id")
    private JpaCompanyRitualSeason companyRitualSeason;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "ritualPackage")
    private List<JpaApplicantPackage> applicantPackages;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "ritualPackage")
    private List<JpaPackageHousing> packageHousings;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "ritualPackage")
    private List<JpaPackageTransportation> packageTransportations;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

}
