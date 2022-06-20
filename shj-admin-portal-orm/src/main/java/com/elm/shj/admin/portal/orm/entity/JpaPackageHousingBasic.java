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
 * The persistent class for the shc_package_housing database table.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Entity
@Table(name = "shc_package_housing")
@NamedQuery(name = "JpaPackageHousingBasic.findAll", query = "SELECT j  FROM JpaPackageHousingBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPackageHousingBasic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "site_code")
    private String siteCode;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private JpaRitualPackageBasic ritualPackage;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private JpaHousingZoneBasic housingZone;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "location_name_ar", nullable = false)
    private String locationNameAr;

    @Column(name = "location_name_en", nullable = false)
    private String locationNameEn;

    @Column(name = "validity_start")
    private Date validityStart;

    @Column(name = "validity_end")
    private Date validityEnd;

    @Column(name = "address_en")
    private String addressEn;

    @Column(name = "address_ar")
    private String addressAr;

    @Column(name = "is_default")
    private boolean isDefault;

    private double lat;

    private double lng;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "packageHousing")
    private List<JpaPackageCateringBasic> packageCatering;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
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
