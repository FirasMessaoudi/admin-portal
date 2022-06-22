package com.elm.shj.admin.portal.services.tawakkalna;

import com.elm.shj.admin.portal.services.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.MessageFormat;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TawakkalnaIntegrationService {

    private static final String TAWAKKALNA_Base_API = "https://snf4.nic.gov.sa";
    private static final String TAWAKKALNA_POST_Login_Client_API = "/api/core/user/public/login-client";
    private static final String TAWAKKALNA_POST_Push_Permit_Card_API = "/api/hajjdata/pushpermitcard";
    private static final String TAWAKKALNA_Client_Id = "maidan";
    private static final String TAWAKKALNA_Client_Secret = "maidan123";


    private final RestTemplate restTemplate;
    public TawakkalnaIntegrationService(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplate = restTemplateBuilder.build();
    }
    public TawakkalnaApplicantOutputDto pushApplicantInfo(TawakkalnaApplicantInputDto input)   {
        log.info("Start TawakkalnaIntegrationService pushApplicantInfo");
        TawakkalnaApplicantOutputDto tawakkalnaApplicantOutput = TawakkalnaApplicantOutputDto.builder().status(true).build();
        // Call TAWAKKALNA API GET TOKEN
        TawakkalnaInputDto tokenInput = TawakkalnaInputDto.builder().clientId(TAWAKKALNA_Client_Id).clientSecret(TAWAKKALNA_Client_Secret).build();
        // Setting Up Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TawakkalnaInputDto> request = new HttpEntity<>(tokenInput,headers);
        // Call API to get Token
        String bearerToken = restTemplate.postForObject(MessageFormat.format("{0}{1}", TAWAKKALNA_Base_API,TAWAKKALNA_POST_Login_Client_API), request,String.class);
        log.info("Tawakkalna login-client Response Token Returned {} ",bearerToken);
        // Call Push Permit Card
        headers.set("Authorization", "Bearer "+ bearerToken);
        HttpEntity<TawakkalnaApplicantInputDto> tawakkalnaApplicantInputDto = new HttpEntity<>(input,headers);
        String responsePushPermitCard = restTemplate.postForObject(MessageFormat.format("{0}{1}", TAWAKKALNA_Base_API,TAWAKKALNA_POST_Push_Permit_Card_API), tawakkalnaApplicantInputDto,String.class);
        log.info("External Response from pushpermitcard {} ",responsePushPermitCard);
        log.info("Finish TawakkalnaIntegrationService pushApplicantInfo");
        return tawakkalnaApplicantOutput;
    }
}
