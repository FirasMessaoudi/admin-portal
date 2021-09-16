package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the shc_housing_zone database table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_housing_zone")
@NamedQuery(name = "JpaHousingZone.findAll", query = "SELECT j FROM JpaHousingZone j")
@Data
@NoArgsConstructor
public class JpaHousingZone implements Serializable {


    private static final long serialVersionUID = 3358143495376599793L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;


    @Column(name = "name_ar", nullable = false)
    private String nameAr;

    @Column(name = "name_en", nullable = false)
    private String nameEn;

    private String color;


    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "housingZone")
    private List<JpaPackageHousing> packageHousings;


}
