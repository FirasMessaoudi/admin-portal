package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaHousingSiteLookup;
import com.elm.shj.admin.portal.orm.repository.HousingSiteLookupRepository;
import com.elm.shj.admin.portal.services.dto.ECampSite;
import com.elm.shj.admin.portal.services.dto.HousingSiteLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling housing site lookup
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HousingSiteLookupService extends GenericService<JpaHousingSiteLookup, HousingSiteLookupDto, Long> {

    private final HousingSiteLookupRepository housingSiteLookupRepository;

    List<String> sitesCodesWithCamps = List.of(ECampSite.ARAFAT.name(), ECampSite.MENA.name());

    /**
     * Fetches housing sites with camps
     *
     * @return the list of housing sites with camps
     */
    public List<HousingSiteLookupDto> findCampSites() {
        return mapList(housingSiteLookupRepository.findByCodeIn(sitesCodesWithCamps));
    }


    public boolean existsByCode(String code) {
        return housingSiteLookupRepository.existsByCode(code);
    }
}
