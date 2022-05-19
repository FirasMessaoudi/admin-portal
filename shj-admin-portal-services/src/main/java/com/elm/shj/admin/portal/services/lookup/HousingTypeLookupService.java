package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaHousingTypeLookup;
import com.elm.shj.admin.portal.orm.repository.HousingTypeLookupRepository;
import com.elm.shj.admin.portal.services.dto.HousingTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling housing type lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class HousingTypeLookupService extends GenericService<JpaHousingTypeLookup, HousingTypeLookupDto, Long> {
    private final HousingTypeLookupRepository housingTypeLookupRepository;

    public boolean existsByCode(String code) {
        return housingTypeLookupRepository.existsByCode(code);
    }
}
