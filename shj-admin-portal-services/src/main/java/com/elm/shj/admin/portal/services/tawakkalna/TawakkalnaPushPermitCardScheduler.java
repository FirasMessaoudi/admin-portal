package com.elm.shj.admin.portal.services.tawakkalna;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantPermitCardView;
import com.elm.shj.admin.portal.services.dto.ApplicantPermitCardDto;
import com.elm.shj.admin.portal.services.dto.TawakkalnaApplicantInputDto;
import com.elm.shj.admin.portal.services.dto.TawakkalnaApplicantOutputDto;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TawakkalnaPushPermitCardScheduler {
    private final TawakkalnaIntegrationService tawakkalnaIntegrationService;
    @Value("${push.applicant.permit.card.scheduler.active.nodes}")
    private String schedulerActiveNodes;
    @Scheduled(fixedDelayString = "${scheduler.push.applicant.permit.card.delay.milliseconds}")
    @SchedulerLock(name = "tawakkalna-push-permit-card-scheduler")
    public void pushApplicantPermitCards() {
        String runningIpAddress;
        try{
            runningIpAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("TawakkalnaPushPermitCardScheduler:running IP address for potential applicant card scheduler is: {}", runningIpAddress);
        }
        catch (UnknownHostException e)
        {
            log.error("TawakkalnaPushPermitCardScheduler:Error while getting the running ip address. Scheduler TawakkalnaPushPermitCardScheduler will not run.",  e);
            return;
        }
        if (schedulerActiveNodes == null || schedulerActiveNodes.isEmpty()) {
            log.warn("TawakkalnaPushPermitCardScheduler:Scheduler TawakkalnaPushPermitCardScheduler will not run, no active nodes are configured in database.");
            return;
        }

        if (!schedulerActiveNodes.contains(runningIpAddress)) {
            log.warn("TawakkalnaPushPermitCardScheduler:Scheduler TawakkalnaPushPermitCardScheduler will not run, {} ip is not in the configured active nodes list.");
            return;
        }
        log.debug("TawakkalnaPushPermitCardScheduler:Scheduler Tawakkalna Push PermitCard started...");
        LockAssert.assertLocked();

        // Fetch Record to Process
        try
        {
            log.debug("TawakkalnaPushPermitCardScheduler:Scheduler Tawakkalna Push PermitCard: Getting Data to Process ");
            // Get Data To Process
            List<JpaApplicantPermitCardView>  applicantPermitCards =  tawakkalnaIntegrationService.getApplicantForCardsPermitPush();

            if(applicantPermitCards!=null && !applicantPermitCards.isEmpty())
            {
                log.debug("TawakkalnaPushPermitCardScheduler:Scheduler Tawakkalna Push PermitCard: Data Fetched Successfully ");
                for(JpaApplicantPermitCardView applicantPermitCard :  applicantPermitCards)
                {
                    TawakkalnaApplicantInputDto tawakkalnaApplicantInputDto =initailizeTawakkalnaApplicantInput(applicantPermitCard);
                    log.debug("TawakkalnaPushPermitCardScheduler:Scheduler Tawakkalna Push PermitCard: Initialize Object Successfully ");
                    // Push Card Permit Data to Tawakkalna
                    log.debug("TawakkalnaPushPermitCardScheduler:Scheduler Tawakkalna Push PermitCard: Pushing to  tawakkalnaIntegrationService Calling External Web Service ");
                    TawakkalnaApplicantOutputDto serviceResult  = tawakkalnaIntegrationService.pushApplicantInfo(tawakkalnaApplicantInputDto);
                    log.debug("TawakkalnaPushPermitCardScheduler:Scheduler Tawakkalna Push PermitCard: Pushing to  tawakkalnaIntegrationService Calling External Web Service Successfully Finished");
                }
            }

        }
        catch (Exception e)
        {
            log.error("TawakkalnaPushPermitCardScheduler:Error on Scheduler Tawakkalna Push PermitCard ",  e);
        }
        log.debug("TawakkalnaPushPermitCardScheduler:Scheduler Tawakkalna Push PermitCard finished...");
    }

    private TawakkalnaApplicantInputDto initailizeTawakkalnaApplicantInput(JpaApplicantPermitCardView applicantPermitCard)
    {
        // Create Object Input For External Tawakkalna Service
        TawakkalnaApplicantInputDto tawakkalnaApplicantInputDto = new TawakkalnaApplicantInputDto();

        if(applicantPermitCard.getNationalityCode()>0)
        tawakkalnaApplicantInputDto.setNationalId(applicantPermitCard.getNationalityCode());

        if(!checkIfStringNullorEmpty(applicantPermitCard.getPassportNumber()))
        tawakkalnaApplicantInputDto.setPassportNumber(applicantPermitCard.getPassportNumber());

        if(applicantPermitCard.getIqamaNin()>0)
        tawakkalnaApplicantInputDto.setNationalityCode(applicantPermitCard.getIqamaNin());

        if(!checkIfStringNullorEmpty(applicantPermitCard.getCardNumber()))
        tawakkalnaApplicantInputDto.setSmartCardNumber(applicantPermitCard.getCardNumber());

        if(!checkIfStringNullorEmpty(applicantPermitCard.getCardSerial()))
        tawakkalnaApplicantInputDto.setCardSerial(Long.parseLong(applicantPermitCard.getCardSerial()));

        if(!checkIfStringNullorEmpty(applicantPermitCard.getBackPhoneNumber()))
        tawakkalnaApplicantInputDto.setPhoneNumber(applicantPermitCard.getBackPhoneNumber());

        if(!checkIfStringNullorEmpty(applicantPermitCard.getFrontQr()))
        tawakkalnaApplicantInputDto.setFrontQrValue(applicantPermitCard.getFrontQr());

        if(!checkIfStringNullorEmpty(applicantPermitCard.getEstablishmentRefCode()))
        tawakkalnaApplicantInputDto.setEstablishmentId(Integer.parseInt(applicantPermitCard.getEstablishmentRefCode()));

        if(!checkIfStringNullorEmpty(applicantPermitCard.getEstablishmentAr()))
        tawakkalnaApplicantInputDto.setEstablishmentNameAr(applicantPermitCard.getEstablishmentAr());

        if(!checkIfStringNullorEmpty(applicantPermitCard.getEstablishmentEn()))
        tawakkalnaApplicantInputDto.setEstablishmentNameEn(applicantPermitCard.getEstablishmentEn());

        if(!checkIfStringNullorEmpty(applicantPermitCard.getCompanyNameAr()))
        tawakkalnaApplicantInputDto.setCompanyNameAr(applicantPermitCard.getCompanyNameAr());

        if(!checkIfStringNullorEmpty(applicantPermitCard.getCompanyNameEn()))
        tawakkalnaApplicantInputDto.setCompanyNameEn(applicantPermitCard.getCompanyNameEn());

        if(!checkIfStringNullorEmpty(applicantPermitCard.getServiceOfficeNumber()))
        tawakkalnaApplicantInputDto.setServicegroupnumber(applicantPermitCard.getServiceOfficeNumber());

        if(applicantPermitCard.getCardStatus()>0)
        tawakkalnaApplicantInputDto.setCardstatus(applicantPermitCard.getCardStatus());
        return tawakkalnaApplicantInputDto;
    }

    private boolean checkIfStringNullorEmpty(String property)
    {
        if (property == null || property.isEmpty() || property.trim().isEmpty())
            return true;
        else
            return false;
    }
}
