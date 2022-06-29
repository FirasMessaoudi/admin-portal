package com.elm.shj.admin.portal.services.tawakkalna;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TawakkalnaApplicantRequest implements Serializable {
    @JsonProperty("nationalId")
    private long nationalId;

    @JsonProperty("passportNum")
    private String passportNumber;

    @JsonProperty("nationalitycode")
    private long nationalityCode;

    @JsonProperty("smartcardnumber")
    private String smartCardNumber;

    @JsonProperty("cardserial")
    private long cardSerial;

    @JsonProperty("phonenumber")
    private String phoneNumber;

    @JsonProperty("frontqrvalue")
    private String frontQrValue;

    @JsonProperty("establishmentid")
    private int establishmentId;

    @JsonProperty("establishmentnamear")
    private String establishmentNameAr;

    @JsonProperty("establishmentnameen")
    private String establishmentNameEn;

    @JsonProperty("companynamear")
    private String companyNameAr;

    @JsonProperty("companynameen")
    private String companyNameEn;

    @JsonProperty("serviceGroupNumber")
    private String servicegroupnumber;

    @JsonProperty("cardStatus")
    private int cardstatus;
}