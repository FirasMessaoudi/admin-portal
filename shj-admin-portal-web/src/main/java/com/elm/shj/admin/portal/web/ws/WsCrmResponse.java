/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.ws;

import com.elm.shj.admin.portal.services.data.validators.FieldDependency;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * Web service operation result
 *
 * @author Aymen Dhaoui
 * @since 1.1.0
 */
@Getter
@Setter
@Builder
public class WsCrmResponse<T> {

    public enum EWsResponseStatus {
        SUCCESS(1), FAILURE(0);

        int code;

        EWsResponseStatus(int code) {
            this.code = code;
        }

        int getCode() {
            return code;
        }
    }

    private int ResponseCode  = EWsResponseStatus.SUCCESS.getCode();
    private T Message;
    private List<ErrorCrm> ErrorList ;

    @Getter
    @Setter
    @Builder
    static class ErrorCrm {
        private String ErrorCode;
        private String ErrorDesc;
    }

    @Getter
    @Setter
    @Builder
    static class WsCrmAttachmentResponse {
        private byte[] AttachmentContent  ;
        private List<ErrorCrm> ErrorList ;
    }
}
