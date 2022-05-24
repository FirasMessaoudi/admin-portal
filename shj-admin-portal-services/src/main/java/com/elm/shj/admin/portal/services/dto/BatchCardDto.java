package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BatchCardDto implements Serializable {
    private static final long serialVersionUID = 8107536294952271416L;

    private long id;
    private String digitalId;
    private String serialNumber;
    private String statusCode;
    private String groupReferenceNumber;

}
