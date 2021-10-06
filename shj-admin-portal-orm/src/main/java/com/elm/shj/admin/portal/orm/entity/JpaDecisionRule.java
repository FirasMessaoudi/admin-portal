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
 * The persistent class for the shc_decision_rule database table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_decision_rule")
@NamedQuery(name = "JpaDecisionRule.findAll", query = "SELECT j FROM JpaDecisionRule j")
@Getter
@Setter
@NoArgsConstructor
public class JpaDecisionRule implements Serializable {

    private static final long serialVersionUID = 2532495233381225382L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "label_ar", nullable = false)
    private String labelAr;

    @Column(name = "label_en", nullable = false)
    private String labelEn;

    @Column(nullable = false)
    private String dmn;

    @ManyToOne
    @JoinColumn(name = "data_segment_id")
    private JpaDataSegment dataSegment;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;
}
