package com.elm.shj.admin.portal.web.tawakkalna;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.tawakkalna.TawakkalnaIntegrationService;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(Navigation.API_TAWAKKALNA_INTEGRATION)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TawakkalnaIntegrationController {
    private final TawakkalnaIntegrationService tawakkalnaIntegrationService;
    @PostMapping("/push-applicant-info")
    public TawakkalnaApplicantOutputDto pushApplicantInfo(@RequestBody TawakkalnaApplicantInputDto input) {
        log.info("Starting Pushing Tawakkalna Data");
        //return tawakkalnaIntegrationService.pushApplicantInfo(input);
        return null;
    }

}
