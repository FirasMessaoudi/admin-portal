package com.elm.shj.admin.portal.services.lookup;

import com.elm.shj.admin.portal.orm.entity.JpaComplaintStatusLookup;
import com.elm.shj.admin.portal.services.dto.ComplaintStatusLookupDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Service handling Complaint status lookup
 *
 * @author othman alamoud
 * @since 1.2.6
 */
@Service
@Slf4j
public class ComplaintStatusLookupService extends GenericService<JpaComplaintStatusLookup, ComplaintStatusLookupDto, Long> {
}
