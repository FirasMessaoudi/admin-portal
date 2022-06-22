package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TawakkalnaApplicantInputDto implements Serializable {
    private long nationalId;
    private String passportNum;
    private int nationalitycode;
    private String smartcardnumber;
    private long cardserial;
    private String phonenumber;
    private String frontqrvalue;
    private int establishmentid;
    private String establishmentnamear;
    private String establishmentnameen;
    private String companynamear;
    private String companynameen;
    private String servicegroupnumber;
    private int cardstatus;
}
