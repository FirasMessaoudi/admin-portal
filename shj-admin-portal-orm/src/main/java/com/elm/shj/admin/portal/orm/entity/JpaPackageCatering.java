package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the shc_package_catering database table.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_package_catering")
@NamedQuery(name = "JpaPackageCatering.findAll", query = "SELECT j FROM JpaPackageCatering j")
@Getter
@Setter
@NoArgsConstructor
public class JpaPackageCatering implements Serializable {

    private static final long serialVersionUID = 8613036264986315543L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "meal_code", length = 50)
    private String mealCode;

    @Column(name = "meal_time", nullable = false)
    private Time mealTime;

    @Column(name = "meal_description", nullable = false, length = 256)
    private String mealDescription;

    @Column(name = "type", length = 45)
    private String type;

    @Column(name = "description_ar", length = 250)
    private String descriptionAr;

    @Column(name = "description_en", length = 125)
    private String descriptionEn;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "is_default")
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "package_housing_id")
    private JpaPackageHousing packageHousing;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "packageCatering")
    private List<JpaApplicantPackageCatering> applicantPackageCaterings;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
