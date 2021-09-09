package com.elm.shj.admin.portal.orm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shc_group_applicant_list")
@NamedQuery(name = "JpaCompanyRitualStep.findAll", query = "SELECT j FROM JpaCompanyRitualStep j")
@Data
@NoArgsConstructor
public class JpaCompanyRitualStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "applicant_group_id")
    private JpaApplicantGroup applicantGroupId;

    @Column(name = "transportation_type_code")
    private String transportationTypeCode;

    @Column(name = "step_index")
    private long index;

    @Column(name = "step_code")
    private String stepCode;

    @Column(name = "time")
    private Date time;

    @Column(name = "location_lat")
    private double locationLat;

    @Column(name = "location_lng")
    private double LocationLng;

    @Column(name = "location_name_ar")
    private String locationNameAr;

    @Column(name = "location_name_en")
    private String locationNameEn;

    @Column(name = "creation_date")
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
