package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaComplaintTypeLookup;
import com.elm.shj.admin.portal.orm.repository.ComplaintTypeLookupRepository;
import com.elm.shj.admin.portal.services.dto.ComplaintTypeLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Service handling Complaint type lookup
 *
 * @author f.messaoudi
 * @since 1.1.0
 */
@Service
@Slf4j
public class ComplaintTypeLookupService extends GenericService<JpaComplaintTypeLookup, ComplaintTypeLookupDto, Long> {
    /**
     * finds if an complaint by its code
     *
     * @param typeCode the code of the complaint to look for
     * @return the found complaint
     */
    public ComplaintTypeLookupDto findByCode(String typeCode) {
        return getMapper().fromEntity(((ComplaintTypeLookupRepository) getRepository()).findFirstByCode(typeCode), mappingContext);
    }
}
