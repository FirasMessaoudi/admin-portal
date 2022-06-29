package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.HijriDate;
import com.elm.shj.admin.portal.services.data.validators.NullOrNotBlank;
import com.elm.shj.admin.portal.services.data.validators.WithTransportationType;
import lombok.*;

import java.io.Serializable;

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
    private Integer typeCode;

    private String locationFromNameAr;
    private String locationFromNameEn;
    private String locationToNameAr;
    private String locationToNameEn;

    @HijriDate(minOffset = -140, maxOffset = 1)
    private Long validityStart;

    @HijriDate(minOffset = -140, maxOffset = 1)
    private Long validityEnd;

    @NullOrNotBlank(min = 0, max = 600)
    private String routeDetails;
}
