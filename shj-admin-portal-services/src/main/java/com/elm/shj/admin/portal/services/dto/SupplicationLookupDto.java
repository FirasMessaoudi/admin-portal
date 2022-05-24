package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Dto class for the Supplication Lookup domain.
 *
 * @author Nihed Sidhom
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplicationLookupDto implements Serializable {

    private static final long serialVersionUID = 5873918641003781311L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
    private String type ;
    private Integer counter ;

}
