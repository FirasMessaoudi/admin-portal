package com.elm.shj.admin.portal.services.tawakkalna;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TawakkalnaAuthRequest implements Serializable {
    private String clientId;
    private String clientSecret;
}
