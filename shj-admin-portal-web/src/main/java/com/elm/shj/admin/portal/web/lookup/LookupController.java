/*
 *  Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.lookup;

import com.elm.shj.admin.portal.services.dto.AuthorityConstants;
import com.elm.shj.admin.portal.services.dto.AuthorityLookupDto;
import com.elm.shj.admin.portal.services.lookup.AuthorityLookupService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * Controller for lookup data
 *
 * @author ahmad flaifel
 * @since 1.3.0
 */
@RestController
@RequestMapping(Navigation.API_LOOKUP)
public class LookupController {

    @Autowired
    private AuthorityLookupService authorityLookupService;

    @GetMapping("/authority/list/parent")
    @RolesAllowed(AuthorityConstants.ROLE_MANAGEMENT)
    public List<AuthorityLookupDto> listParentAuthorities(Authentication authentication) {
        return authorityLookupService.findAllParentAuthorities();
    }
}
