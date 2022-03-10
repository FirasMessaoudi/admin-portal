package com.elm.shj.admin.portal.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the area layer domain.
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class AreaLayerDto implements Serializable {

    private static final long serialVersionUID = 9147583371796645468L;


    private long id;
    private String areaCode;
    private String layer;
    private Integer parentLayerId;
    private Date creationDate;

}
