/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.applicant;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantSupplication;
import com.elm.shj.admin.portal.orm.repository.ApplicantSupplicationRepository;
import com.elm.shj.admin.portal.services.dto.ApplicantSupplicationDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service handling applicant supplication
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ApplicantSupplicationService extends GenericService<JpaApplicantSupplication,ApplicantSupplicationDto, Long> {
    private final ApplicantSupplicationRepository applicantSupplicationRepository;

    public List<ApplicantSupplicationDto> findSupplicationByDigitalId(String digitalId){
        List<JpaApplicantSupplication> applicantSupplications=applicantSupplicationRepository.findAllByDigitalIdAndDeleted(digitalId,false);
        return mapList(applicantSupplications) ;
    }
    @Transactional
    public int deleteSupplication(long id){
       return applicantSupplicationRepository.disableSuggestedSupplication(id);
    }
    @Transactional
    public int resetSupplicationLastNumber(long id){
     return applicantSupplicationRepository.resetSupplicationLastNumber(id);
    }
    @Transactional
    public int updateSupplicationNumbers(long id,int total,int last){
        return applicantSupplicationRepository.updateSupplicationLastNumber(id,total,last);
    }


}
