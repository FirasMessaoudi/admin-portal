package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
/**
 * Dto class for the applicant supplication domain.
 *
 * @author r.chebbi
 * @since 1.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuggestedSupplicationLookupDto implements Serializable {
    private static final long serialVersionUID = -3709023902414845455L;

    private long id;
    private String code;
    private String lang;
    private String label;
    private Date creationDate;
}
