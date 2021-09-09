package com.elm.shj.admin.portal.orm.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the shc_applicant_group database table.
 *
 * @author Jaafer Jarray
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_applicant_group")
@NamedQuery(name = "JpaApplicantGroup.findAll", query = "SELECT j FROM JpaApplicantGroup j")
@Data
@NoArgsConstructor
public class JpaApplicantGroup {

    private static final long serialVersionUID = -6527928280666512305L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "local_office_id")
    private long localOfficeId;

    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "group_leader_id")
    private long groupLeaderId;

    @ManyToOne
    @JoinColumn(name = "company_season_ritual", nullable = false)
    private JpaCompanyRitualSeason companySeasonRitual;

    @Column(name = "group_type_code")
    private String groupTypeCode;

    @Column(name = "entry_transportation_type_code")
    private String EntryTransportationTypeCode;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "groupId")
    private List<JpaGroupApplicantList> groupApplicantLists;

    @OneToMany(mappedBy = "applicantGroupId", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JpaCompanyRitualStep> companyRitualSteps;


    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }


}
