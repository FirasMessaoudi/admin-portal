package com.elm.shj.admin.portal.orm.repository;
import com.elm.shj.admin.portal.orm.entity.JpaSupplicationTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository for Supplication Lookup table.
 *
 * @author Nihed Sidhom
 * @since 1.1.0
 */
public interface SupplicationTypeLookupRepository extends JpaRepository<JpaSupplicationTypeLookup, Long > {


}