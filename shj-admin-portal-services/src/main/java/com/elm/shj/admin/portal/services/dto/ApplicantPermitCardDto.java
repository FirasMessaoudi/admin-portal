package com.elm.shj.admin.portal.services.dto;

import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantPermitCardDto implements Serializable {

    private long id;

    @NullOrNotBlank(min = 1, max = 20)
    private String cardNumber;

    @NullOrNotBlank(min = 1, max = 20)
    private String cardSerial;

    private Date creationDate;

    private Date updateDate;
}
