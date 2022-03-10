package com.elm.shj.admin.portal.services.zone;

import com.elm.shj.admin.portal.orm.entity.JpaAreaLayer;
import com.elm.shj.admin.portal.orm.repository.AreaLayerRepository;
import com.elm.shj.admin.portal.services.dto.AreaLayerDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service handling area layer domain.
 *
 * @author jaafer jarray
 * @since 1.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AreaLayerService extends GenericService<JpaAreaLayer, AreaLayerDto, Long> {

    private final AreaLayerRepository areaLayerRepository;
}
