package com.elm.shj.admin.portal.services.data.huic;

import com.elm.dcc.foundation.commons.validation.ArabicCharacters;
import com.elm.shj.admin.portal.services.data.validators.OnlyCharacters;
import com.elm.shj.admin.portal.services.data.validators.WithTransportationType;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * class for the Package Transportation huic.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HuicPackageTransportation implements Serializable {
    private static final long serialVersionUID = -8214844201380607365L;

    @WithTransportationType
    private Long typeCode;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String locationFromNameAr;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String locationFromNameEn;
    @ArabicCharacters(lettersOnly = true, numbersOnly = false)
    private String locationToNameAr;
    @Pattern(regexp = "(^[a-zA-Z0-9]*)"
            , message = "validation.data.constraints.msg.20003")
    private String locationToNameEn;
    private Date validityStart;
    private Date validityEnd;
    @OnlyCharacters(min = 0, max = 256)
    private String routeDetails;
}
