/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the shc_chat_contact database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_chat_contact")
@NamedQuery(name = "JpaChatContact.findAll", query = "SELECT j FROM JpaChatContact j")
@Getter
@Setter
@NoArgsConstructor
public class JpaChatContact implements Serializable {

    private static final long serialVersionUID = 7212037715533180866L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "digital_id")
    private String digitalId;

    @Column(name = "contact_digital_id")
    private String contactDigitalId;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private JpaContactTypeLookup type;

    private String alias;

    private String avatar;

    @Column(name = "system_defined")
    private boolean systemDefined;

    @Column(name = "staff_title_code")
    private String staffTitleCode;

    @Column(name = "relationship_code")
    private String relationshipCode;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "country_phone_prefix")
    private String countryPhonePrefix;

    @Column(name = "country_code")
    private String countryCode;

    private boolean deleted;

    @Column(name = "auto_added")
    private boolean autoAdded;

    @Column(name = "applicant_ritual_id")
    private Long applicantRitualId;

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
