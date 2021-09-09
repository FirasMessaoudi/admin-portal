package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_ritual_package database table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_ritual_package")
@NamedQuery(name = "JpaRitualPackage.findAll", query = "SELECT rp  FROM JpaRitualPackage rp")
@Data
@NoArgsConstructor
public class JpaRitualPackage implements Serializable {
    private static final long serialVersionUID = -8657084839410538572L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;


    @Column(name = "type_code")
    private String typeCode;

    private float price;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "country_id")
    private int countryId;


    @Column(name = "creation_date", nullable = false)
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
