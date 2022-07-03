/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.token;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * Service handling trusted token generation.
 *
 * @author Aymen Dhaoui
 * @since 1.0.0
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TokenService {

    @Value("${bi.server.url}")
    private String biServerUrl;
    @Value("${bi.server.client.id}")
    private String biServerClientId;
    private final WebClient webClient;

    public String generateTrustedToken() {
        String token = webClient.post().uri(HttpHost.DEFAULT_SCHEME_NAME + "://" + biServerUrl + "/trusted")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromFormData("username", biServerClientId).with("client_ip", biServerUrl))
                .retrieve().bodyToMono(String.class).block();
        return Objects.equals(token, "-1") ? null : token;
    }
}
