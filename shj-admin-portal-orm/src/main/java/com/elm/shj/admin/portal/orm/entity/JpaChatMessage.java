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
 * The persistent class for the shc_chat_message database table.
 *
 * @author Slim Ben Hadj
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_chat_message")
@NamedQuery(name = "JpaChatMessage.findAll", query = "SELECT j FROM JpaChatMessage j")
@Getter
@Setter
@NoArgsConstructor
public class JpaChatMessage implements Serializable {

    private static final long serialVersionUID = 1566425009166544255L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    private String text;

    @Column(name = "type_id")
    private String type;

    @Column(name = "content_file_path")
    private String contentFilePath;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private JpaChatContact sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private JpaChatContact receiver;

    @Column(name = "sent_date", nullable = false)
    private Date sentDate;

    @Transient
    private long sentDateTimestamp;

    @Column(name = "received_date", nullable = false)
    private Date receivedDate;

    @Column(name = "read_date", nullable = false)
    private Date readDate;

    private Boolean deleted;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
        sentDate = new Date(sentDateTimestamp);
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = new Date();
    }

}
