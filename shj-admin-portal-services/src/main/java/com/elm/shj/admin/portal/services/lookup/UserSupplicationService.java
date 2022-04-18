/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaUserSupplication;
import com.elm.shj.admin.portal.orm.repository.UserSupplicationRepository;
import com.elm.shj.admin.portal.services.dto.UserSupplicationDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service handling user supplication
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserSupplicationService extends GenericService<JpaUserSupplication, UserSupplicationDto, Long> {
    private final UserSupplicationRepository userSupplicationRepository;

    public List<UserSupplicationDto> findSupplicationByDigitalId(String digitalId){
        List<JpaUserSupplication> applicantSupplications= userSupplicationRepository.findAllByDigitalIdAndDeleted(digitalId,false);
        return mapList(applicantSupplications) ;
    }
    @Transactional
    public int deleteSupplication(long id){
        return userSupplicationRepository.disableUserSupplication(id);
    }


}
