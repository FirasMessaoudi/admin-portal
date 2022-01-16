package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantContact;
import com.elm.shj.admin.portal.orm.repository.ApplicantContactRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantContactDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantContactService extends GenericService<JpaApplicantContact, ApplicantContactDto, Long> {

    private final ApplicantContactRepository applicantContactRepository;

    public List<ApplicantContactDto> findByApplicantId(Long id) {
        return mapList(applicantContactRepository.findAllByApplicantId(id));
    }

    /**
     * Set applicant ritual id for the applicant contacts.
     *
     * @param applicantRitualId
     * @param applicantId
     * @return
     */
    @Transactional
    public int updateContactApplicantRitual(long applicantRitualId, long applicantId) {
        return applicantContactRepository.updateContactApplicantRitual(applicantRitualId, applicantId);
    }

}
