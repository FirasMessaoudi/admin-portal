package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantContact;
import com.elm.shj.admin.portal.orm.repository.ApplicantContactRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantContactDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantContactService extends GenericService<JpaApplicantContact, ApplicantContactDto, Long> {

    private final ApplicantContactRepository applicantContactRepository;
    //TODO this metohod not used
    public List<ApplicantContactDto> findByApplicantId(Long id) {
        log.info("Start findByApplicantId for {} id.", id);
        List<ApplicantContactDto> applicantContactDtos = mapList(applicantContactRepository.findAllByApplicantId(id));
        log.info("Finish findByApplicantId with {} id", id);
        return applicantContactDtos;
    }
}
