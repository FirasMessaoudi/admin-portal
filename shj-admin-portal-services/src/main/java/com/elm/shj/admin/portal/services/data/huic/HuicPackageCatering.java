package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.WithMealTime;
import com.elm.shj.admin.portal.services.data.validators.WithMealType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * class for the package catering huic.
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HuicPackageCatering implements Serializable {

    private static final long serialVersionUID = -8878979584205935800L;

    @NotNull(message = "validation.data.constraints.msg.20001")
    private String mealCode;

    @WithMealTime
    private Integer mealTime;

    @WithMealType
    private Integer mealType;

    private String optionDescriptionAr;

    private String optionDescriptionEn;

    private boolean isDefault;
}
