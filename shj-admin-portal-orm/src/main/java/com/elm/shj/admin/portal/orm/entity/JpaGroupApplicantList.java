package com.elm.shj.admin.portal.orm.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the shc_group_applicant_list database table.
 *
 * @author Jaafer Jarray
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_group_applicant_list")
@NamedQuery(name = "JpaGroupApplicantList.findAll", query = "SELECT j FROM JpaGroupApplicantList j")
@Data
@NoArgsConstructor
public class JpaGroupApplicantList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private JpaApplicantGroup groupId;

    @Column(name = "applicant_uin")
    private String applicantUin;

    @Column(name = "creation_date")
    private Date creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }
}
