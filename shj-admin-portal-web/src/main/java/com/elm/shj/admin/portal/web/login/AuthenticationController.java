/*
 * Copyright (c) 2017 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.login;

import com.elm.dcc.foundation.providers.recaptcha.exception.RecaptchaException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtAuthenticationProvider;
import com.elm.shj.admin.portal.web.security.jwt.JwtToken;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * The controller handling the authentication operations
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@RestController
@RequestMapping(Navigation.API_AUTH)
public class AuthenticationController {

    public static final int INVALID_RECAPTCHA_RESPONSE_CODE = 555;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Value("${login.simultaneous.enabled}")
    private boolean simultaneousLoginEnabled;

    /**
     * Authenticates the user and returns a freshly generated token
     *
     * @param credentials the user credentials
     * @return the generated token
     */
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody Map<String, String> credentials, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Login request handler");
        JwtToken authentication;
        String idNumber = credentials.get("idNumber");
        try {
            authentication = (JwtToken) authenticationProvider
                    .authenticate(new UsernamePasswordAuthenticationToken(idNumber, credentials.get("password")));
        } catch (RecaptchaException rex) {
            return ResponseEntity.status(INVALID_RECAPTCHA_RESPONSE_CODE).body(null);
        }

        jwtTokenService.attachTokenCookie(response, authentication);
        jwtTokenService.refreshTokenExpiry(response, authentication.getToken());

        return ResponseEntity.ok(new JwtToken(null, ((UsernamePasswordAuthenticationToken) authentication.getPrincipal()).getName(),
                authentication.getAuthorities(), authentication.isPasswordExpired(), authentication.getFirstName(),
                authentication.getLastName(), authentication.getRole()));
    }

    /**
     * Invalidates the users token
     *
     * @param authentication the current user
     */
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(Authentication authentication, HttpServletResponse response) {
        Assert.notNull(authentication, "User should be logged-in in order to logout");
        logger.debug("Logout request handler");
        // if the authentication exists, then clear it
        if (!simultaneousLoginEnabled && authentication instanceof JwtToken) {
            jwtTokenService.invalidateToken(((JwtToken) authentication).getToken());
        }
        // clear security context anyway...
        authentication.setAuthenticated(false);
        SecurityContextHolder.clearContext();
        jwtTokenService.clearTokenCookies(response);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
