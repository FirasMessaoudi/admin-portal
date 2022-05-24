package com.elm.shj.admin.portal.orm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
/**
 * The persistent class for the shc_supplication_Lk database table.
 *
 * @author Nihed Sidhom
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_supplication_Lk")
@NamedQuery(name = "shc_supplicationsLookup.findAll", query = "SELECT j FROM JpaSupplicationLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaSupplicationLookup extends JpaLocalizedLookup{

    private static final long serialVersionUID = 7980889654642593788L;



    @Column(name = "type",nullable = false)
    private String type ;


    @Column(name = "counter",nullable = false)
    private Integer counter ;
}
