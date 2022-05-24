/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.dto.SupplicationUserCounterDto;
import com.elm.shj.admin.portal.services.dto.UserSupplicationDto;
import com.elm.shj.admin.portal.services.lookup.SuggestedSupplicationLookupService;
import com.elm.shj.admin.portal.services.lookup.SupplicationUserCounterService;
import com.elm.shj.admin.portal.services.lookup.UserSupplicationService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * controller for islamic rosary management
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@CrossOrigin(
        originPatterns = "*",
        maxAge = 3600,
        exposedHeaders = {"Authorization", JwtTokenService.CALLER_TYPE_HEADER_NAME, JwtTokenService.TOKEN_COOKIE_NAME},
        allowCredentials = "true"
)
@Slf4j
@RestController
@RequestMapping(Navigation.API_ROSARY_INTEGRATION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RosaryWsController {

    private final UserSupplicationService userSupplicationService;
    private final SuggestedSupplicationLookupService suggestedSupplicationLookupService;
    private final SupplicationUserCounterService supplicationUserCounterService;
    /**
     * finds user supplications by uin
     *
     * @param digitalId               the UIN of the applicant
     * @return list of supplications
     */
    @GetMapping("/find-user-supplications/{digitalId}")
    public ResponseEntity<WsResponse<?>> findUserSupplicationsByDigitalId(@PathVariable("digitalId") String digitalId) {
        log.debug("List of user supplications by digital Id {} ", digitalId);
        List<UserSupplicationDto> applicantSupplications= userSupplicationService.findSupplicationByDigitalId(digitalId);
        if (applicantSupplications == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(null).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantSupplications).build());
        }
    }
    /**
     * finds suggested supplications
     *
     * @return list of supplications
     */
    @GetMapping("/find-supplications-lookup")
    public ResponseEntity<WsResponse<?>> findSupplicationsLookup() {
        log.debug("List of suggested supplications  ");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(suggestedSupplicationLookupService.findAll()).build());
    }
    /**
     * finds supplications user counter
     * @param digitalId               the UIN of the applicant
     * @return list of supplications
     */
    @GetMapping("/find-supplications-user-counter/{digitalId}")
    public ResponseEntity<WsResponse<?>> findSupplicationsUserCounter(@PathVariable("digitalId") String digitalId) {
        log.debug("List rosary supplications  ");
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(supplicationUserCounterService.findAllSupplicationsCounterByDigitalId(digitalId)).build());
    }
    @PutMapping("/delete-supplication/{id}")
    public ResponseEntity<WsResponse<?>> deleteSupplication(@PathVariable("id") long id){
        log.debug("Delete supplication by digital Id {}   ",id);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(userSupplicationService.deleteSupplication(id)).build());
    }
    @PutMapping("/reset-supplication-number/{id}")
    public ResponseEntity<WsResponse<?>> resetSupplicationLastNumber(@PathVariable("id") long id){
        log.debug("modify supplication by digital Id {}   ",id);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(supplicationUserCounterService.resetSupplicationCounter(id)).build());
    }
    @PutMapping("/update-supplication-numbers/{id}/{total}/{last}")
    public ResponseEntity<WsResponse<?>> updateSupplicationNumbers(@PathVariable("id") long id,@PathVariable("total") int total,@PathVariable("last") int last){
        log.debug("modify supplication numbers by digital Id {}   ",id);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(supplicationUserCounterService.updateSupplicationCounter(id,total,last)).build());
    }
    @PostMapping(value ="/save-supplication-user-counter")
    public ResponseEntity<WsResponse<?>> SaveSupplicationUserCounter(@RequestBody SupplicationUserCounterDto supplicationUserCounterDto){
        log.debug("save supplication user counter");
        Optional<SupplicationUserCounterDto> supplicationUserCounter = supplicationUserCounterService.findSupplicationCounterByCodeAndDigitalId(supplicationUserCounterDto.getCode(),supplicationUserCounterDto.getDigitalId());
        if(supplicationUserCounter.isPresent()){
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode())
                    .body(WsError.builder().error(WsError.EWsError.SUPPLICATION_COUNTER_EXIST_ALREADY.getCode())
                            .referenceNumber(supplicationUserCounterDto.getDigitalId()).build()).build());
        }
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(supplicationUserCounterService.saveUserSupplicationCounter(supplicationUserCounterDto)).build());

    }
}
