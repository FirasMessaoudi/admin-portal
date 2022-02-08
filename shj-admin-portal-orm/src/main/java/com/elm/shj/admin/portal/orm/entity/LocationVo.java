package com.elm.shj.admin.portal.orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Value object class for returned locations.
 *
 * @author Jaafer Jarray
 * @since 1.4.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationVo {

    private double lat;
    private double lng;
}
