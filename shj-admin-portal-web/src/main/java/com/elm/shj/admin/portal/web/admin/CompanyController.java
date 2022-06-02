/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller for company related pages
 *
 * @author ahmad flaifel
 * @since 1.1.0
 */
@RestController
@RequestMapping(Navigation.API_COMPANY)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyController {


}
