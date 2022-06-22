package com.elm.shj.admin.portal.services.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TawakkalnaInputDto implements Serializable {
    private String clientId;
    private String clientSecret;
}
