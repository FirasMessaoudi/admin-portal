/*
 * Copyright (c) 2022 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.card.BatchMainCollectionService;
import com.elm.shj.admin.portal.services.dto.BatchCollectionVO;
import com.elm.shj.admin.portal.services.dto.EManifestType;
import com.elm.shj.admin.portal.services.dto.ManifestVo;
import com.elm.shj.admin.portal.services.prinitng.ManifestService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import com.elm.shj.admin.portal.web.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * Controller for exposing batch main collection web services for external party.
 *
 * @author f.messaoudi
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
@RequestMapping(Navigation.API_BATCH)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BatchMainCollectionWsController {


    private final BatchMainCollectionService batchMainCollectionService;
    private final ManifestService manifestService;

    /**
     * generate batch cards
     *
     * @param batchCollection the batch main collection with the batch reference number
     * @return
     */
    @PostMapping("/generate")
    public ResponseEntity<WsResponse<?>> generateBatchCards(@RequestBody BatchCollectionVO batchCollection) {
        batchMainCollectionService.generateBatchCards(batchCollection);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                "SUCCESS").build());
    }

    @GetMapping("/collection-status/{reference}")
    public ResponseEntity<WsResponse<?>> trackBatchCollectionStatus(@PathVariable String reference) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                batchMainCollectionService.findBatchStatusByReference(reference)).build());
    }

    /**
     * generate manifest file as images for
     * batch cards that belong to batch or main collection or sub collection
     *
     * @param batchCollection the batch main collection with the batch reference number
     * @return image or list of images (each image for sub collection)
     */
    @PostMapping("/manifest/generate/{printRequestReferenceNumber}")
    public ResponseEntity<WsResponse<?>> generateManifestFileAsImages(@PathVariable("printRequestReferenceNumber") String printRequestReferenceNumber, @RequestBody BatchCollectionVO batchCollection) {
      //  batchMainCollectionService.generateBatchCards(batchCollection);
        ManifestVo manifest = manifestService.generateManifest(printRequestReferenceNumber, batchCollection, EManifestType.IMAGE);
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode()).body(
                manifest).build());
    }


}
