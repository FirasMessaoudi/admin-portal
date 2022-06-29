package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "shc_tawakkalna_service_log")
@NamedQuery(name = "JpaTawakkalnaServiceLog.findAll", query = "SELECT j FROM JpaTawakkalnaServiceLog j")
@Getter
@Setter
@NoArgsConstructor
public class JpaTawakkalnaServiceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "source")
    private String source;

    @Column(name = "service_url")
    private String serviceUrl;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
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
