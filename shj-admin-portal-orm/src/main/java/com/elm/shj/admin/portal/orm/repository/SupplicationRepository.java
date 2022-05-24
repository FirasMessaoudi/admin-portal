package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaSupplicationLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository for Supplication Lookup table.
 *
 * @author Nihed Sidhom
 * @since 1.1.0
 */
public interface SupplicationRepository extends JpaRepository<JpaSupplicationLookup, Long > {

    List<JpaSupplicationLookup> findAllByType(String type);

}
