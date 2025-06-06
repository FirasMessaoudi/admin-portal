package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the shc_ritual_season database table.
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_ritual_season")
@NamedQuery(name = "JpaRitualSeason.findAll", query = "SELECT j FROM JpaRitualSeason j")
@Getter
@Setter
@NoArgsConstructor
public class JpaRitualSeason implements Serializable {

    private static final long serialVersionUID = 6421655004418134795L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "season_year", nullable = false)
    private int seasonYear;

    @Column(name = "ritual_type_code", nullable = false, length = 50)
    private String ritualTypeCode;

    @Column(name = "season_start", nullable = false)
    private long seasonStart;

    @Column(name = "season_end", nullable = false)
    private long seasonEnd;

    private boolean active;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "ritualSeason")
    private List<JpaCompanyRitualSeason> companyRitualSeasons;
}
