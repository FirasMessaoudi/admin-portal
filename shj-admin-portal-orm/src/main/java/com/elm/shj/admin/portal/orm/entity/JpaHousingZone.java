package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_housing_zone database table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_housing_zone")
@NamedQuery(name = "JpaHousingZone.findAll", query = "SELECT hz  FROM JpaHousingZone hz")
@Data
@NoArgsConstructor
public class JpaHousingZone implements Serializable {


    private static final long serialVersionUID = 3358143495376599793L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;


    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    private String color;

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
