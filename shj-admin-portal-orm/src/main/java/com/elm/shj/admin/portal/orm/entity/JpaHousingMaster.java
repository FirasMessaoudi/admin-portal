package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_housing_master database table.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_housing_master")
@NamedQuery(name = "JpaHousingMaster.findAll", query = "SELECT j  FROM JpaHousingMaster j")
@Getter
@Setter
@NoArgsConstructor
public class JpaHousingMaster implements Serializable {
    private static final long serialVersionUID = -105052748928018549L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "housing_ref_code", nullable = false)
    private String housingReferenceCode;

    @Column(name = "type_code", nullable = false)
    private String typeCode;

    @Column(name = "site_code")
    private String siteCode;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "location_name_ar", nullable = false)
    private String locationNameAr;

    @Column(name = "location_name_en", nullable = false)
    private String locationNameEn;

    @Column(name = "address_en")
    private String addressEn;

    @Column(name = "address_ar")
    private String addressAr;

    @Column(name = "zone_code")
    private String zoneCode;

    private Double lat;

    private Double lng;

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
