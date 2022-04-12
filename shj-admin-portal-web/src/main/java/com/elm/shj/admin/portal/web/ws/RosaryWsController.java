/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.applicant.ApplicantSupplicationService;
import com.elm.shj.admin.portal.services.dto.ApplicantSupplicationDto;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final ApplicantSupplicationService applicantSupplicationService;
    /**
     * finds rosary supplications by uin
     *
     * @param digitalId               the UIN of the applicant
     * @return list of supplications
     */
    @GetMapping("/find-rosary-supplications/{digitalId}")
    public ResponseEntity<WsResponse<?>> findRosarySupplicationsByDigitalId(@PathVariable("digitalId") String digitalId) {
        log.debug("List rosary supplications by digital Id {} ", digitalId);
        List<ApplicantSupplicationDto> applicantSupplications= applicantSupplicationService.findSupplicationByDigitalId(digitalId);
        if (applicantSupplications == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    WsResponse.builder().status(WsResponse.EWsResponseStatus.FAILURE.getCode()).body(null).build());
        } else {
            return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(applicantSupplications).build());
        }
    }
    @PutMapping("/delete-supplication/{id}")
    public ResponseEntity<WsResponse<?>> deleteSupplication(@PathVariable("id") long id){
        log.debug("Delete supplication by digital Id {}   ",id);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantSupplicationService.deleteSupplication(id)).build());
    }
    @PutMapping("/reset-supplication-number/{id}")
    public ResponseEntity<WsResponse<?>> resetSupplicationLastNumber(@PathVariable("id") long id){
        log.debug("modify supplication by digital Id {}   ",id);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantSupplicationService.resetSupplicationLastNumber(id)).build());
    }
    @PutMapping("/update-supplication-numbers/{id}/{total}/{last}")
    public ResponseEntity<WsResponse<?>> updateSupplicationNumbers(@PathVariable("id") long id,@PathVariable("total") int total,@PathVariable("last") int last){
        log.debug("modify supplication numbers by digital Id {}   ",id);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(applicantSupplicationService.updateSupplicationNumbers(id,total,last)).build());
    }
}
