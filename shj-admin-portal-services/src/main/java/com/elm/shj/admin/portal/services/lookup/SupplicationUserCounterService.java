/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaSupplicationUserCounter;
import com.elm.shj.admin.portal.orm.repository.SupplicationUserCounterRepository;
import com.elm.shj.admin.portal.services.dto.SupplicationUserCounterDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service handling user supplication
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SupplicationUserCounterService extends GenericService<JpaSupplicationUserCounter, SupplicationUserCounterDto,Long> {
    private final SupplicationUserCounterRepository supplicationUserCounterRepository;

    public List<SupplicationUserCounterDto> findAllSupplicationsCounterByDigitalId(String digitalId) {
        List<JpaSupplicationUserCounter> supplicationUserCounters = supplicationUserCounterRepository.findAllByDigitalId(digitalId);
        return mapList(supplicationUserCounters);
    }
    public Optional<SupplicationUserCounterDto> findSupplicationCounterByCodeAndDigitalId(String code,String digitalId){
        Optional<JpaSupplicationUserCounter> supplicationUserCounter = supplicationUserCounterRepository.findByCodeAndDigitalId(code,digitalId);
        if(supplicationUserCounter.isPresent()){
            return Optional.of(getMapper().fromEntity(supplicationUserCounter.get(),mappingContext));
        }
        return Optional.empty();
    }
   @Transactional
    public int resetSupplicationCounter(long id){
        return supplicationUserCounterRepository.resetSupplicationCounter(id);
    }
    @Transactional
    public int updateSupplicationCounter(long id, int total, int last){
        return supplicationUserCounterRepository.updateSupplicationCounter(id,total,last);
    }
    @Transactional
    public SupplicationUserCounterDto saveUserSupplicationCounter(SupplicationUserCounterDto supplicationUserCounterDto){
        SupplicationUserCounterDto counterBuilder = SupplicationUserCounterDto.builder()
                .digitalId(supplicationUserCounterDto.getDigitalId())
                .code(supplicationUserCounterDto.getCode())
                .supplicationLastCount(supplicationUserCounterDto.getSupplicationLastCount())
                .supplicationTotalCount(supplicationUserCounterDto.getSupplicationLastCount())
                .suggested(supplicationUserCounterDto.isSuggested())
                .creationDate(supplicationUserCounterDto.getCreationDate())
                .build();
       return save(counterBuilder);

    }

}
