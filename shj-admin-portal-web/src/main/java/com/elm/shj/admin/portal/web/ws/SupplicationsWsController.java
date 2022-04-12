package com.elm.shj.admin.portal.web.ws;


import com.elm.shj.admin.portal.services.lookup.SupplicationsLookupService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for exposing supplication web services for external party.
 *
 * @author Nihed Sidhom
 * @since 1.1.0
 */

@Slf4j
@RestController
@RequestMapping(Navigation.API_SUPPLICATIONS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplicationsWsController {
 private final SupplicationsLookupService supplicationsService ;

    /**
     * finds supplication by type
     * @param type the type of the supplication
     * @return the supplications
     */
    @GetMapping("/findSupplications/{type}")
    public ResponseEntity<WsResponse<?>> findSupplicationsByType(@PathVariable("type") String type) {
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(supplicationsService.findSupplicationsListByType(type)).build());
    }


}
