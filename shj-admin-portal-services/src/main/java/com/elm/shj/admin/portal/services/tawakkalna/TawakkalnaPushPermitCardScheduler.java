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
            log.info("running IP address for potential applicant card scheduler is: {}", runningIpAddress);
        }
        catch (UnknownHostException e)
        {
            log.error("Error while getting the running ip address. Scheduler TawakkalnaPushPermitCardScheduler will not run.",  e);
            return;
        }

        if (schedulerActiveNodes == null || schedulerActiveNodes.isEmpty()) {
            log.warn("Scheduler TawakkalnaPushPermitCardScheduler will not run, no active nodes are configured in database.");
            return;
        }

        if (!schedulerActiveNodes.contains(runningIpAddress)) {
            log.warn("Scheduler TawakkalnaPushPermitCardScheduler will not run, {} ip is not in the configured active nodes list.");
            return;
        }
        log.debug("Scheduler Tawakkalna Push PermitCard started...");
        LockAssert.assertLocked();

        // Fetch Record to Process

        // Create Object Input For External Tawakkalna Service
        TawakkalnaApplicantInputDto tawakkalnaApplicantInputDto = new TawakkalnaApplicantInputDto();

        // Get Data To Process
        List<JpaApplicantPermitCardView>  applicantPermitCards =  tawakkalnaIntegrationService.getApplicantForCardsPermitPush();

        if(applicantPermitCards!=null && !applicantPermitCards.isEmpty())
        {
            for(JpaApplicantPermitCardView applicantPermitCard :  applicantPermitCards)
            {

                tawakkalnaApplicantInputDto.setNationalId(applicantPermitCard.getNationalityCode());
                tawakkalnaApplicantInputDto.setPassportNum(applicantPermitCard.getPassportNumber());
                tawakkalnaApplicantInputDto.setNationalitycode(applicantPermitCard.getIqamaNin());
                tawakkalnaApplicantInputDto.setSmartcardnumber(applicantPermitCard.getCardNumber());
                tawakkalnaApplicantInputDto.setCardserial(Long.parseLong(applicantPermitCard.getCardSerial()));
                tawakkalnaApplicantInputDto.setPhonenumber(applicantPermitCard.getBackPhoneNumber());
                tawakkalnaApplicantInputDto.setFrontqrvalue(applicantPermitCard.getFrontQr());
                tawakkalnaApplicantInputDto.setEstablishmentid(1);
                tawakkalnaApplicantInputDto.setEstablishmentnamear(applicantPermitCard.getEstablishmentAr());
                tawakkalnaApplicantInputDto.setEstablishmentnameen(applicantPermitCard.getEstablishmentEn());
                tawakkalnaApplicantInputDto.setCompanynamear(applicantPermitCard.getCompanyNameAr());
                tawakkalnaApplicantInputDto.setCompanynameen(applicantPermitCard.getCompanyNameEn());
                tawakkalnaApplicantInputDto.setServicegroupnumber(applicantPermitCard.getServiceOfficeNumber());
                tawakkalnaApplicantInputDto.setCardstatus(applicantPermitCard.getCardStatus());

                // Push Card Permit Data to Tawakkalna
                TawakkalnaApplicantOutputDto serviceResult  = tawakkalnaIntegrationService.pushApplicantInfo(tawakkalnaApplicantInputDto);

            }

        }

        log.debug("Scheduler Tawakkalna Push PermitCard finished...");
    }
}
