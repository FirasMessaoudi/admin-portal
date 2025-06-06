/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */

package com.elm.shj.admin.portal.services.sms;

import com.elm.shj.admin.portal.services.dto.HUICRequestDto;
import com.elm.shj.admin.portal.services.dto.SmsRequestDto;
import com.elm.shj.admin.portal.services.dto.SmsResponseDto;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

/**
 * Service handling otp operations
 *
 * @author Noor Nawaz
 * @since 1.2.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HUICSmsService {

    @Value("${huic.sms.api.token}")
    private String smsApiToken;

    @Value("${huic.sms.api.url}")
    private String smsApiUrl;

    @Value("${huic.sms.api.mock.enabled}")
    private Boolean smsMockEnabled;

    public boolean sendMessage(Integer countryCode, String recipientNumber, String body, String comments) throws SSLException {
        if(smsMockEnabled) {
            log.info("SMS : {} ", body);
            log.info("Mock SMS sent successfully");
            return true;
        }
        SmsRequestDto smsRequest = SmsRequestDto
                .builder()
                .countryCode(countryCode)
                .receiverNumber(recipientNumber)
                .body(body)
                .comment(comments).build();
        HUICRequestDto<SmsRequestDto> huicSmsRequest = new HUICRequestDto<>();
        huicSmsRequest.setRequest(smsRequest);
        huicSmsRequest.setRequestToken("string");

        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        log.info("Sms request {}", smsRequest);
        SmsResponseDto smsResponse = WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s",smsApiToken))
                .build()
                .post()
                .uri(smsApiUrl)
                .body(BodyInserters.fromValue(huicSmsRequest))
                .retrieve()
                .bodyToMono(SmsResponseDto.class)
                .block();
        if(smsResponse.getHasErrors()) {
            log.info("OTP sending has some error");
        } else {
            log.info("OTP sent successfully");
        }
        return !smsResponse.getHasErrors();
    }
}
