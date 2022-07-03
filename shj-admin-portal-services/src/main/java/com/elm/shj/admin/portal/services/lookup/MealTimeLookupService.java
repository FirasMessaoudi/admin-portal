package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaMealTimeLookup;
import com.elm.shj.admin.portal.orm.repository.MealTimeLookupRepository;
import com.elm.shj.admin.portal.services.dto.MealTimeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service handling meal time lookup
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class MealTimeLookupService extends GenericService<JpaMealTimeLookup, MealTimeLookupDto, Long> {
    private final MealTimeLookupRepository mealTimeLookupRepository;

    public boolean existsByCode(String code) {
        return mealTimeLookupRepository.existsByCode(code);
    }

}
