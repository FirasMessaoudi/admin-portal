package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "shc_applicant_permit_card")
@NamedQuery(name = "JpaApplicantPermitCard.findAll", query = "SELECT j FROM JpaApplicantPermitCard j")
@Getter
@Setter
@NoArgsConstructor
public class JpaApplicantPermitCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_serial")
    private String cardSerial;

    @Column(name = "is_card_pushed")
    private boolean isCardPushed;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }
}
