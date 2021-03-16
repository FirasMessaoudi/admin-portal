/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaAuthorityLookup;
import com.elm.shj.admin.portal.orm.repository.AuthorityLookupRepository;
import com.elm.shj.admin.portal.services.dto.AuthorityLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling authority lookup
 *
 * @author ahmad flaifel
 * @since 1.8.0
 */
@Service
public class AuthorityLookupService extends GenericService<JpaAuthorityLookup, AuthorityLookupDto, Long> {

    public List<AuthorityLookupDto> findAllParentAuthorities() {
        return mapList(((AuthorityLookupRepository) getRepository()).findAllByParentIsNull());
    }
}
