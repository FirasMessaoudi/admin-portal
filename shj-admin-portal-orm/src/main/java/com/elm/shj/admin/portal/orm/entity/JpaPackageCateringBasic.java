package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * The persistent class for the shc_package_catering database table.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Entity
@Table(name = "shc_package_catering")
@NamedQuery(name = "JpaPackageCateringBasic.findAll", query = "SELECT j FROM JpaPackageCateringBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPackageCateringBasic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "meal_code", length = 50)
    private String mealCode;

    @Column(name = "meal_time")
    private Time mealTime;

    @Column(name = "meal_description", length = 256)
    private String mealDescription;

    @Column(name = "meal_time_code", length = 45, nullable = false)
    private String mealTimeCode;

    @Column(name = "meal_type_code", length = 45, nullable = false)
    private String mealTypeCode;

    @Column(name = "description_ar", length = 250)
    private String descriptionAr;

    @Column(name = "description_en", length = 125)
    private String descriptionEn;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "is_default")
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "package_housing_id")
    private JpaPackageHousingBasic packageHousing;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
