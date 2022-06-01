package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaMealTypeLookup;
import com.elm.shj.admin.portal.orm.repository.MealTypeLookupRepository;
import com.elm.shj.admin.portal.services.dto.MealTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service handling meal type lookup
 *
 * @author salzoubi
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class MealTypeLookupService extends GenericService<JpaMealTypeLookup, MealTypeLookupDto, Long> {
    private final MealTypeLookupRepository mealTypeLookupRepository;

    public boolean existsByCode(String code) {
        return mealTypeLookupRepository.existsByCode(code);
    }

}
