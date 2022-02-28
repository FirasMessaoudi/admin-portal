package com.elm.shj.admin.portal.orm.repository;

import com.elm.shj.admin.portal.orm.entity.JpaCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Camera Table.
 *
 * @author r.chebbi
 * @since 1.1.0
 */

public interface CameraRepository extends JpaRepository<JpaCamera, Long> {

    @Query("select count (c.id) from JpaCamera c where  :seasonYear >= c.creationHijriYear")
    long countCameraByHijriSeason(@Param("seasonYear") int seasonYear);

    @Query("select count (c.id) from JpaCamera c where c.status = :status and :seasonYear >= c.creationHijriYear")
    long countCameraByStatusAndHijriYear(@Param("status") String status,@Param("seasonYear") int seasonYear);
}
