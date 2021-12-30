package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the shc_company database table.
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_company")
@NamedQuery(name = "JpaCompanyLite.findAll", query = "SELECT j FROM JpaCompanyLite j")
@Getter
@Setter
@NoArgsConstructor
public class JpaCompanyLite implements Serializable {

    private static final long serialVersionUID = 4101520768244593266L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;
}
