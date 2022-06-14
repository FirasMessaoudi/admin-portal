package com.elm.shj.admin.portal.services.company;


import com.elm.shj.admin.portal.orm.entity.JpaCompanyStaffDigitalId;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffDigitalIdRepository;
import com.elm.shj.admin.portal.services.applicant.ChatContactService;
import com.elm.shj.admin.portal.services.dto.ECardStatus;
import com.elm.shj.admin.portal.services.dto.EStaffDigitalIdStatus;
import com.elm.shj.admin.portal.services.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * scheduler to mark staff ID as invalid
 *
 * @author Jaafer Jarray
 * @since 1.1.0
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffExpirationScheduler {

    private final CompanyStaffDigitalIdRepository companyStaffDigitalIdRepository;
    private final ChatContactService chatContactService;

//    @PostConstruct
//    @Scheduled(cron = "${scheduler.staff.digitalId.invalidate.cron}")
//    @SchedulerLock(name = "staff-digitalId-card-expiration-task")
    void markStaffDigitalIdAsInvalid() {
        log.debug("verify staff expiration scheduler started ...");
        long currentHijriDate = DateUtils.toHijri(new Date());
        List<JpaCompanyStaffDigitalId> companyStaffDigitalIdList;
        companyStaffDigitalIdList = companyStaffDigitalIdRepository.findByStatusCodeAndCompanyStaffCardsCompanyRitualSeasonSeasonEndLessThan(EStaffDigitalIdStatus.VALID.name(), currentHijriDate);
        companyStaffDigitalIdList.forEach(companyStaffDigitalId -> {
            companyStaffDigitalId.setStatusCode(EStaffDigitalIdStatus.INVALID.name());
            companyStaffDigitalId.getCompanyStaffCards().forEach(companyStaffCard -> {
                companyStaffCard.setStatusCode(ECardStatus.EXPIRED.name());
            });
            try {
                chatContactService.deleteInvalidStaffChatContact(companyStaffDigitalId.getSuin());
            } catch (Exception e) {
                log.error("Failed to delete staff contact with suin  >>  {}", companyStaffDigitalId.getSuin());
            }
        });
        companyStaffDigitalIdRepository.saveAll(companyStaffDigitalIdList);

    }
}
