package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaSupplicationTypeLookup;
import com.elm.shj.admin.portal.orm.repository.SupplicationTypeLookupRepository;
import com.elm.shj.admin.portal.services.dto.SupplicationTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Service handling supplication lookup
 *
 * @author Nihed Sidhom
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SupplicationTypeLookupService extends GenericService<JpaSupplicationTypeLookup, SupplicationTypeLookupDto, Long > {
    private final SupplicationTypeLookupRepository supplicationTypeLookupRepository;
    public List<SupplicationTypeLookupDto> findAll() {
        List<JpaSupplicationTypeLookup>  jpaSupplicationTypeLookupLits = supplicationTypeLookupRepository.findAll();
        return mapList(jpaSupplicationTypeLookupLits);
    }
}
