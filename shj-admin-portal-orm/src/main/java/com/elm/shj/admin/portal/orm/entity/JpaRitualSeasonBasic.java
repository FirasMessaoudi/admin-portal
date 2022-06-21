package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the shc_ritual_season database table.
 *
 * @author ahmad flaifel
 * @since 1.2.5
 */
@Entity
@Table(name = "shc_ritual_season")
@NamedQuery(name = "JpaRitualSeasonBasic.findAll", query = "SELECT j FROM JpaRitualSeasonBasic j")
@Getter
@Setter
@NoArgsConstructor
public class JpaRitualSeasonBasic implements Serializable {

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
}
