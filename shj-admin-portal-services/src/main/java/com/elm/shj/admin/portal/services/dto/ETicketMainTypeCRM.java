/*
 *  Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enum for ritual types
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public enum ETicketMainTypeCRM {
    Incident(1),
    Complaint(2);
    private final int id;

    ETicketMainTypeCRM(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static ETicketMainTypeCRM fromId(int id) {
        List<ETicketMainTypeCRM> result = Arrays.stream(ETicketMainTypeCRM.values()).filter(e -> e.getId() == id).collect(Collectors.toList());
        return result.isEmpty() ? null : (ETicketMainTypeCRM) CollectionUtils.get(result, 0);
    }
}
