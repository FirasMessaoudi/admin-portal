package com.elm.shj.admin.portal.services.tawakkalna;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPermitCard;
import com.elm.shj.admin.portal.orm.entity.JpaApplicantPermitCardView;
import com.elm.shj.admin.portal.orm.repository.ApplicantPermitCardRepository;
import com.elm.shj.admin.portal.orm.repository.ApplicantPermitCardViewRepository;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
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
public class TawakkalnaIntegrationService extends GenericService<JpaApplicantPermitCard, ApplicantPermitCardDto, Long>  {
    private final ApplicantPermitCardRepository applicantPermitCardRepository;
    private final RestTemplate restTemplate;

    private final ApplicantPermitCardViewRepository applicantPermitCardViewRepository;

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

    public  List<JpaApplicantPermitCardView> getApplicantForCardsPermitPush()
    {
       //List<ApplicantPermitCardDto> applicantPermitCardDto =  mapList(applicantPermitCardRepository.findAll());
       //return applicantPermitCardDto;
        List<JpaApplicantPermitCardView> applicantPermitCardsVo =applicantPermitCardViewRepository.findAll();
        return applicantPermitCardsVo;
    }

    private boolean saveApplicantPermitCard(TawakkalnaApplicantInputDto input)
    {
        // Insert Applicant Permit Card
        JpaApplicantPermitCard applicantPermitCardToAdd = new JpaApplicantPermitCard();
        applicantPermitCardToAdd.setCardSerial(Long.toString(input.getCardserial()));
        applicantPermitCardToAdd.setCardNumber(input.getSmartcardnumber());
        applicantPermitCardToAdd.setCardPushed(true);
        applicantPermitCardRepository.saveAndFlush(applicantPermitCardToAdd);
        return true;
    }

    public TawakkalnaApplicantOutputDto pushApplicantInfo(TawakkalnaApplicantInputDto input)   {
        log.info("Start TawakkalnaIntegrationService pushApplicantInfo");
        boolean operationStatus=false;
        TawakkalnaApplicantOutputDto tawakkalnaApplicantOutput=null;
       // List<JpaApplicantPermitCardView> applicantPermitCardsVo =applicantPermitCardViewRepository.findAll();
        try
        {
            // Call TAWAKKALNA API GET TOKEN
            TawakkalnaInputDto tokenInput = TawakkalnaInputDto.builder().clientId(tawakkalnaLoginClientClientId).clientSecret(tawakkalnaLoginClientClientSecret).build();
            // Setting Up Header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<TawakkalnaInputDto> request = new HttpEntity<>(tokenInput,headers);
            // TODO: Manage to Call Token API only once if it expires
            // Call API to get Token
            String bearerToken = restTemplate.postForObject(MessageFormat.format("{0}{1}", tawakkalnaBaseUrl,tawakkalnaLoginClientUrl), request,String.class);
            log.info("Tawakkalna login-client Response Token Returned {} ",bearerToken);

            String responsePushPermitCard = new String();

            if(bearerToken !=null && bearerToken.length() >0) // Call Push Permit Card
            {
                headers.set("Authorization", "Bearer "+ bearerToken);
                HttpEntity<TawakkalnaApplicantInputDto> tawakkalnaApplicantInputDto = new HttpEntity<>(input,headers);
                responsePushPermitCard = restTemplate.postForObject(MessageFormat.format("{0}{1}", tawakkalnaBaseUrl,tawakkalnaPushPermitCardUrl), tawakkalnaApplicantInputDto,String.class);
                log.info("External Response from pushpermitcard {} ",responsePushPermitCard);
                log.info("Finish TawakkalnaIntegrationService pushApplicantInfo");

                // Insert Data
                saveApplicantPermitCard(input);

            }

            if(responsePushPermitCard !=null && responsePushPermitCard.length() >0)
            {
                operationStatus = true;
                tawakkalnaApplicantOutput = TawakkalnaApplicantOutputDto.builder().status(operationStatus).build();
            }

        }
        catch (Exception e)
        {
            log.error("Error while push card permit info to tawakkalna",  e);
            throw e;
        }
        return tawakkalnaApplicantOutput;

    }
}
