package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the shc_housing_zone database table.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Entity
@Table(name = "shc_housing_zone")
@NamedQuery(name = "JpaHousingZoneBasic.findAll", query = "SELECT j FROM JpaHousingZoneBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaHousingZoneBasic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    private String color;
}
