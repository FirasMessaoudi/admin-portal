package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.orm.entity.JpaApplicantRitual;
import com.elm.shj.admin.portal.services.dto.ApplicantRitualCardLiteDto;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.error.CardDetailsNotFoundException;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for controller {@link ApplicantController}
 *
 * @author Ahmed Elsayed
 * @since 1.1.0
 */
public class ApplicantCardControllerTest extends AbstractControllerTestSuite {

    private final static String EXIST_USER_UIN = "59737700000059";
    private final static String FAKE_USER_UIN = "1234567893";
    private final static String EXIST_RITUAL_ID = "36";
    private static final int TEST_CARD_DETAILS_NOT_FOUND_RESPONSE_CODE = 561;

    @Override
    public void setUp() throws Exception {

    }

    @Override
    public void tearDown() {

    }




}
