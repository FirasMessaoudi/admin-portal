/*
 * Copyright (c) 2017 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the shc_user database table.
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Entity
@Table(name = "shc_user")
@NamedQuery(name = "JpaUser.findAll", query = "SELECT j FROM JpaUser j")
@Getter
@Setter
@NoArgsConstructor
public class JpaUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long id;

	private boolean activated;

	@Column(name = "BLOCK_DATE")
	private Date blockDate;

	private boolean blocked;

	@Column(name = "CHANGE_PASSWORD_REQUIRED")
	private boolean changePasswordRequired;

	@Column(name = "CREATION_DATE", nullable = false, updatable = false)
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH_GREGORIAN")
	private Date dateOfBirthGregorian;

	@Column(name = "DATE_OF_BIRTH_HIJRI")
	private Integer dateOfBirthHijri;

	private boolean deleted;

	@Column(length = 255)
	private String email;

	@Column(name = "FAMILY_NAME", nullable = false, length = 100)
	private String familyName;

	@Column(name = "FATHER_NAME", length = 100)
	private String fatherName;

	@Column(name = "FIRST_NAME", nullable = false, length = 100)
	private String firstName;

	@Column(nullable = false, length = 1)
	private String gender;

	@Column(name = "GRAND_FATHER_NAME", length = 100)
	private String grandFatherName;

	@Column(name = "LAST_LOGIN_DATE")
	private Date lastLoginDate;

	@Column(name = "MOBILE_NUMBER", nullable = false)
	private int mobileNumber;

	@Column(nullable = false)
	private Long nin;

	@Column(name = "NUMBER_OF_TRIES")
	private int numberOfTries;

	@Column(name = "PASSWORD_HASH", nullable = false, length = 256)
	private String passwordHash;

	@Column(name = "PREFERRED_LANGUAGE", length = 2)
	private String preferredLanguage;

	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	@Column(name = "ACTION_DATE")
	private Date actionDate;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "user")
	private Set<JpaUserRole> userRoles;

	@Column(name = "AVATAR")
	private String avatar;

	@Column(name = "TOKEN_EXPIRY_DATE")
	private Date tokenExpiryDate;

	@PrePersist
	public void prePersist() {
		creationDate = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		updateDate = new Date();
	}
}
