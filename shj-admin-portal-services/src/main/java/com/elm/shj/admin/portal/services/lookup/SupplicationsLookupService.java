package com.elm.shj.admin.portal.services.lookup;
import com.elm.shj.admin.portal.orm.entity.JpaSupplicationLookup;
import com.elm.shj.admin.portal.orm.repository.SupplicationRepository;
import com.elm.shj.admin.portal.services.dto.SupplicationLookupDto;
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
public class SupplicationsLookupService extends GenericService <JpaSupplicationLookup, SupplicationLookupDto,Long> {

    private final SupplicationRepository supplicationsRepository ;
    /**
     * Find all supplication by type
     * @return list of supplication .
     */
    public  List<SupplicationLookupDto> findSupplicationsListByCodeAndLang(String code , String lang){
        List<JpaSupplicationLookup> supplications = supplicationsRepository.findAllSupplication(code, lang);
        return mapList(supplications);


    }



}