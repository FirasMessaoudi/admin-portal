package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpCacheDto implements Serializable {

    private long id;
    private String principle;
    private String otp;
    private Date creationDate;
}
