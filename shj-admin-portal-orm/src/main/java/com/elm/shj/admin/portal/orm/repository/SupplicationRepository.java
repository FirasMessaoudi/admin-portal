package com.elm.shj.admin.portal.orm.repository;
import com.elm.shj.admin.portal.orm.entity.JpaSupplicationLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
/**
 * Repository for Supplication Lookup table.
 *
 * @author Nihed Sidhom
 * @since 1.1.0
 */
public interface SupplicationRepository extends JpaRepository<JpaSupplicationLookup, Long > {

    List<JpaSupplicationLookup> findAllByType(String type);
    @Query ("select s from JpaSupplicationLookup s where s.code Like :code% AND s.lang =:lang " )
    List<JpaSupplicationLookup> findAllSupplication(@Param("code") String code, @Param("lang") String lang);

}


