package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaHousingCategoryLookup;
import com.elm.shj.admin.portal.orm.repository.HousingCategoryLookupRepository;
import com.elm.shj.admin.portal.services.dto.HousingCategoryLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling housing category lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HousingCategoryLookupService extends GenericService<JpaHousingCategoryLookup, HousingCategoryLookupDto, Long> {
    private final HousingCategoryLookupRepository housingCategoryLookupRepository;

    public boolean existsByCode(String code) {
        return housingCategoryLookupRepository.existsByCode(code);
    }
}
