/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.reader;

import com.elm.shj.admin.portal.services.dto.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Excel Item Reader Factory that creates item readers based on data segment
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
@Component
public class ExcelItemReaderFactory {

    @SuppressWarnings("rawtypes")
    private final Map<EDataSegment, Class> readersRegistry = new HashMap<>();

    /**
     * Populates the registry
     */
    @PostConstruct
    private void init() {
        readersRegistry.put(EDataSegment.APPLICANT_DATA, ApplicantDto.class);
        readersRegistry.put(EDataSegment.APPLICANT_RELATIVES_DATA, ApplicantRelativeDto.class);
        readersRegistry.put(EDataSegment.APPLICANT_HEALTH_DATA, ApplicantHealthDto.class);
        readersRegistry.put(EDataSegment.APPLICANT_IMMUNIZATION_DATA, ApplicantHealthImmunizationDto.class);
        readersRegistry.put(EDataSegment.APPLICANT_DISEASE_DATA, ApplicantHealthDiseaseDto.class);
        readersRegistry.put(EDataSegment.APPLICANT_RITUAL_DATA, ApplicantRitualDto.class);
        readersRegistry.put(EDataSegment.APPLICANT_EMERGENCY_DATA, ApplicantEmergencyDto.class);
        readersRegistry.put(EDataSegment.STAFF_MAIN_DATA, CompanyStaffDto.class);
        readersRegistry.put(EDataSegment.STAFF_RITUAL_DATA, CompanyStaffRitualDto.class);
        readersRegistry.put(EDataSegment.STAFF_APPLICANT_GROUP_DATA, StaffApplicantGroupDto.class);

    }

    /**
     * Creates a new reader suitable for the data segment passed in parameter
     *
     * @param dataSegment the data segment subject of the reader
     * @param <T>         the type of the read object
     * @return the suitable reader based on the data segment
     */
    @SuppressWarnings("unchecked")
    public <T> ExcelItemReader<T> create(DataSegmentDto dataSegment) {
        Class<T> typeClass = readersRegistry.get(EDataSegment.fromId(dataSegment.getId()));
        return typeClass != null ? new ExcelItemReader<>(typeClass) : null;
    }
}
