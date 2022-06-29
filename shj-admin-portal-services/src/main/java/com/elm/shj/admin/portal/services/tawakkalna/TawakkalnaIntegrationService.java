package com.elm.shj.admin.portal.services.tawakkalna;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPermitCard;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPermitCardView;
import com.elm.shj.admin.portal.orm.entity.JpaTawakkalnaServiceLog;
import com.elm.shj.admin.portal.orm.repository.ApplicantPermitCardRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantPermitCardViewRepository;
import com.elm.shj.admin.portal.orm.repository.TawakkalnaServiceLogRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantPermitCardDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TawakkalnaIntegrationService extends GenericService<JpaApplicantPermitCard, ApplicantPermitCardDto, Long> {
    private final ApplicantPermitCardRepository applicantPermitCardRepository;
    private final RestTemplate restTemplate;
    private final ApplicantPermitCardViewRepository applicantPermitCardViewRepository;
    private final TawakkalnaServiceLogRepository tawakkalnaServiceLogRepository;

    @Value("${tawakkalna.base.url}")
    private String tawakkalnaBaseUrl;

    @Value("${tawakkalna.login.client.url}")
    private String tawakkalnaLoginClientUrl;

    @Value("${tawakkalna.push.permit.card.url}")
    private String tawakkalnaPushPermitCardUrl;

    @Value("${tawakkalna.login.client.clientid}")
    private String tawakkalnaLoginClientClientId;

    @Value("${tawakkalna.login.client.clientsecret}")
    private String tawakkalnaLoginClientClientSecret;

    public List<JpaApplicantPermitCardView> getApplicantForCardsPermitPush() {
        //List<ApplicantPermitCardDto> applicantPermitCardDto =  mapList(applicantPermitCardRepository.findAll());
        //return applicantPermitCardDto;
        List<JpaApplicantPermitCardView> applicantPermitCardsVo = applicantPermitCardViewRepository.findAll();
        return applicantPermitCardsVo;
    }

    private boolean saveApplicantPermitCard(TawakkalnaApplicantRequest input) {
        // Insert Applicant Permit Card
        JpaApplicantPermitCard applicantPermitCardToAdd = new JpaApplicantPermitCard();
        applicantPermitCardToAdd.setCardSerial(Long.toString(input.getCardSerial()));
        applicantPermitCardToAdd.setCardNumber(input.getSmartCardNumber());
        applicantPermitCardToAdd.setPushed(true);
        applicantPermitCardToAdd.setCardStatus(input.getCardstatus());
        applicantPermitCardRepository.saveAndFlush(applicantPermitCardToAdd);
        return true;
    }

    // Service Trace Log Request Response Tawakkalna
    private void logTawakkalnaRequestRespone(JpaTawakkalnaServiceLog tawakkalnaServiceLog)
    {
        tawakkalnaServiceLogRepository.saveAndFlush(tawakkalnaServiceLog);
    }

    private TawakkalnaApplicantRequest initailizeTawakkalnaApplicantInput(JpaApplicantPermitCardView applicantPermitCard) {
        // Create Object Input For External Tawakkalna Service
        TawakkalnaApplicantRequest tawakkalnaApplicantRequest = new TawakkalnaApplicantRequest();

        if (applicantPermitCard.getNationalityCode() > 0)
            tawakkalnaApplicantRequest.setNationalityCode(applicantPermitCard.getNationalityCode());

        if (checkIfStringNullorEmpty(applicantPermitCard.getIqamaNin()))
            tawakkalnaApplicantRequest.setNationalId(Long.parseLong(applicantPermitCard.getIqamaNin()));

        if (checkIfStringNullorEmpty(applicantPermitCard.getPassportNumber()))
            tawakkalnaApplicantRequest.setPassportNumber(applicantPermitCard.getPassportNumber());

        if (checkIfStringNullorEmpty(applicantPermitCard.getCardNumber()))
            tawakkalnaApplicantRequest.setSmartCardNumber(applicantPermitCard.getCardNumber());

        if (checkIfStringNullorEmpty(applicantPermitCard.getCardSerial()))
            tawakkalnaApplicantRequest.setCardSerial(Long.parseLong(applicantPermitCard.getCardSerial()));

        if (checkIfStringNullorEmpty(applicantPermitCard.getBackPhoneNumber()))
            tawakkalnaApplicantRequest.setPhoneNumber(applicantPermitCard.getBackPhoneNumber());

        if (checkIfStringNullorEmpty(applicantPermitCard.getFrontQr()))
            tawakkalnaApplicantRequest.setFrontQrValue(applicantPermitCard.getFrontQr());

        if (checkIfStringNullorEmpty(applicantPermitCard.getEstablishmentRefCode()))
            tawakkalnaApplicantRequest.setEstablishmentId(Integer.parseInt(applicantPermitCard.getEstablishmentRefCode()));

        if (checkIfStringNullorEmpty(applicantPermitCard.getEstablishmentAr()))
            tawakkalnaApplicantRequest.setEstablishmentNameAr(applicantPermitCard.getEstablishmentAr());

        if (checkIfStringNullorEmpty(applicantPermitCard.getEstablishmentEn()))
            tawakkalnaApplicantRequest.setEstablishmentNameEn(applicantPermitCard.getEstablishmentEn());

        if (checkIfStringNullorEmpty(applicantPermitCard.getCompanyNameAr()))
            tawakkalnaApplicantRequest.setCompanyNameAr(applicantPermitCard.getCompanyNameAr());

        if (checkIfStringNullorEmpty(applicantPermitCard.getCompanyNameEn()))
            tawakkalnaApplicantRequest.setCompanyNameEn(applicantPermitCard.getCompanyNameEn());

        if (checkIfStringNullorEmpty(applicantPermitCard.getServiceOfficeNumber()))
            tawakkalnaApplicantRequest.setServicegroupnumber(applicantPermitCard.getServiceOfficeNumber());

        if (applicantPermitCard.getCardStatus() > 0)
            tawakkalnaApplicantRequest.setCardstatus(applicantPermitCard.getCardStatus());

        return tawakkalnaApplicantRequest;
    }

    private boolean checkIfStringNullorEmpty(String property) {
        return property != null && !property.isEmpty() && !property.trim().isEmpty();
    }

    public TawakkalnaApplicantResponse pushApplicantInfo(JpaApplicantPermitCardView applicantPermitCard) {
        log.debug("TawakkalnaIntegrationService:Start TawakkalnaIntegrationService pushApplicantInfo");
        boolean operationStatus = false;
        ObjectMapper jsonMapper = new ObjectMapper();
        TawakkalnaApplicantResponse tawakkalnaApplicantOutput = null;
        // Create Service Log Object
        JpaTawakkalnaServiceLog tawakkalnaServiceLog = null;
        try {

            tawakkalnaServiceLog = new JpaTawakkalnaServiceLog();
            TawakkalnaApplicantRequest input = initailizeTawakkalnaApplicantInput(applicantPermitCard);
            // Call TAWAKKALNA API GET TOKEN
            TawakkalnaAuthRequest tokenInput = TawakkalnaAuthRequest.builder().clientId(tawakkalnaLoginClientClientId).clientSecret(tawakkalnaLoginClientClientSecret).build();
            // Setting Up Header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<TawakkalnaAuthRequest> request = new HttpEntity<>(tokenInput, headers);

            // TODO: Manage to Call Token API only once if it expires
            // Call API to get Token
            String bearerToken = restTemplate.postForObject(MessageFormat.format("{0}{1}", tawakkalnaBaseUrl, tawakkalnaLoginClientUrl), request, String.class);
            log.debug("TawakkalnaIntegrationService:Tawakkalna login-client Response Token Returned {} ", bearerToken);

            String responsePushPermitCard = "";

            if (bearerToken != null && bearerToken.length() > 0) // Call Push Permit Card
            {
                headers.set("Authorization", "Bearer " + bearerToken);
                HttpEntity<TawakkalnaApplicantRequest> tawakkalnaApplicantRequest = new HttpEntity<>(input, headers);

                tawakkalnaServiceLog.setServiceUrl(MessageFormat.format("{0}{1}", tawakkalnaBaseUrl,tawakkalnaPushPermitCardUrl));
                if(applicantPermitCard!=null)
                    tawakkalnaServiceLog.setSource(jsonMapper.writeValueAsString(applicantPermitCard));
                if(input!=null)
                    tawakkalnaServiceLog.setRequest(jsonMapper.writeValueAsString(input));

                responsePushPermitCard = restTemplate.postForObject(MessageFormat.format("{0}{1}", tawakkalnaBaseUrl, tawakkalnaPushPermitCardUrl), tawakkalnaApplicantRequest, String.class);
                log.debug("TawakkalnaIntegrationService:External Response from pushpermitcard {} ", responsePushPermitCard);
                log.debug("TawakkalnaIntegrationService:Finish TawakkalnaIntegrationService pushApplicantInfo");

                if(checkIfStringNullorEmpty(responsePushPermitCard))
                    tawakkalnaServiceLog.setResponse(responsePushPermitCard);

                logTawakkalnaRequestRespone(tawakkalnaServiceLog);

            }

            if (responsePushPermitCard != null && responsePushPermitCard.length() > 0) {
                // Insert Data
                saveApplicantPermitCard(input);
                operationStatus = true;
                tawakkalnaApplicantOutput = TawakkalnaApplicantResponse.builder().status(operationStatus).build();
            }

        } catch (Exception e) {
            tawakkalnaServiceLog.setResponse(e.toString());
            logTawakkalnaRequestRespone(tawakkalnaServiceLog);
            log.error("TawakkalnaIntegrationService:Error while push card permit info to tawakkalna", e);
        }
        return tawakkalnaApplicantOutput;

    }


}
