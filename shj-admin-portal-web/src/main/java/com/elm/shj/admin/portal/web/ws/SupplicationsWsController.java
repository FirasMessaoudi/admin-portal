package com.elm.shj.admin.portal.web.ws;
import com.elm.shj.admin.portal.services.dto.SupplicationLookupDto;
import com.elm.shj.admin.portal.services.dto.SupplicationTypeLookupDto;
import com.elm.shj.admin.portal.services.lookup.SupplicationTypeLookupService;
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
import java.util.List;
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
    private final SupplicationTypeLookupService supplicationTypeLookupService ;
    /**
     * finds supplication by type
     * @param code the code of the supplication
     * @return the supplications
     */
    @GetMapping("/find/{code}/{lang}")
    public ResponseEntity<WsResponse<?>> findSupplicationsByCodeAndLang(@PathVariable("code") String code,@PathVariable("lang") String lang) {
        List<SupplicationLookupDto> supplicationsListByType = supplicationsService.findSupplicationsListByCodeAndLang(code , lang );
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(supplicationsListByType).build());
    }

    /////
    @GetMapping("/findSupplicationType")
    public ResponseEntity<WsResponse<?>> findSupplicationsType() {
        List<SupplicationTypeLookupDto> supplicationsTypeList = supplicationTypeLookupService.findAll();
        return ResponseEntity.ok(WsResponse.builder().status(WsResponse.EWsResponseStatus.SUCCESS.getCode())
                .body(supplicationsTypeList).build());
    }



}