package com.elm.shj.admin.portal.services.data.huic;

import com.elm.shj.admin.portal.services.data.validators.WithHousingMaster;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * class for the Package Housing  .
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HuicPackageHousing implements Serializable {

    private static final long serialVersionUID = -645928154124537718L;
    @WithHousingMaster
    private Long refNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "validation.data.constraints.msg.20001")
    private Date validityStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "validation.data.constraints.msg.20001")
    private Date validityEnd;
    private boolean isDefault;
    @Valid
    List<HuicPackageCatering> packageCaterings;

}
