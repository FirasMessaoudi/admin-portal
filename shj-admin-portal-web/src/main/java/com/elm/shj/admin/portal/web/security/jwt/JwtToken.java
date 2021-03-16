/*
 * Copyright (c) 2018 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.security.jwt;

import com.elm.shj.admin.portal.services.dto.RoleDto;
import com.google.common.base.Objects;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * JWT Token used for Spring Security authentication process
 *
 * @author Aymen Dhaoui <adhaoui@elm.sa>
 * @since 1.0.0
 */
public class JwtToken extends UsernamePasswordAuthenticationToken {
    private String token;
    private long tokenExpirationDate;
    private boolean passwordExpired;
    private Collection<GrantedAuthority> authorities;
    private RoleDto role;
    private String firstName;
    private String lastName;

    public JwtToken(final String token, final Object principal,
                    final Collection<? extends GrantedAuthority> grantedAuthorities,
                    boolean passwordExpired) {
        super(principal, token, grantedAuthorities);
        this.authorities = (Collection<GrantedAuthority>)grantedAuthorities;
        this.token = token;
        this.passwordExpired = passwordExpired;
    }

    public JwtToken(final String token, final Object principal,
                    final Collection<? extends GrantedAuthority> grantedAuthorities,
                    boolean passwordExpired, String firstName, String lastName, RoleDto role) {
        super(principal, token, grantedAuthorities);
        this.authorities = (Collection<GrantedAuthority>)grantedAuthorities;
        this.token = token;
        this.passwordExpired = passwordExpired;
        this.role = role;
        this.role.setRoleAuthorities(null);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setTokenExpirationDate(long tokenExpirationDate) {
        this.tokenExpirationDate = tokenExpirationDate;
    }

    public long getTokenExpirationDate() {
        return tokenExpirationDate;
    }

    public boolean isPasswordExpired() { return passwordExpired; }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public RoleDto getRole() {
        return this.role;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JwtToken)) return false;
        if (!super.equals(o)) return false;
        JwtToken jwtToken = (JwtToken) o;
        return getTokenExpirationDate() == jwtToken.getTokenExpirationDate() &&
                Objects.equal(getToken(), jwtToken.getToken());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getToken(), getTokenExpirationDate());
    }
}
