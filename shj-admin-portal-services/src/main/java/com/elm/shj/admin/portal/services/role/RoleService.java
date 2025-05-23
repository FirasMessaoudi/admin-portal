/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.role;

import com.elm.shj.admin.portal.orm.entity.JpaRole;
import com.elm.shj.admin.portal.orm.repository.RoleRepository;
import com.elm.shj.admin.portal.services.dto.RoleDto;
import com.elm.shj.admin.portal.services.generic.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Service handling user role
 *
 * @author ahmad flaifel
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RoleService extends GenericService<JpaRole, RoleDto, Long> {

    private final RoleRepository roleRepository;

    /**
     * Find all roles.
     *
     * @param pageable
     * @return
     */
    public Page<RoleDto> findAll(Pageable pageable, Set loggedInUserRoleIds) {
        if (loggedInUserRoleIds.contains(RoleRepository.SYSTEM_ADMIN_USER_ROLE_ID))
            return mapPage(roleRepository.findByDeletedFalse(pageable));
        // exclude system users in returned list
        return mapPage(roleRepository.findDistinctByDeletedFalseAndIdNot(pageable, RoleRepository.SYSTEM_ADMIN_ROLE_ID));
    }

    /**
     * Searches for roles by authority and name
     *
     * @param authorityId filer by authority id
     * @param arabicName  filer by arabic name
     * @param englishName filer by english name
     * @return the found list
     */
    public Page<RoleDto> searchByAuthorityOrName(Pageable pageable, Long authorityId, String arabicName, String englishName) {
        return mapPage(roleRepository.findByAuthorityOrName(pageable, authorityId,
                StringUtils.isNotBlank(arabicName) ? "%" + arabicName.toLowerCase() + "%" : null,
                StringUtils.isNotBlank(englishName) ? "%" + englishName.toLowerCase() + "%" : null, RoleRepository.WS_USER));
    }

    /**
     * List active roles.
     *
     * @param loggedInUserRoleIds
     * @return the list of roles matching criteria
     */
    public List<RoleDto> findAll(Set<Long> loggedInUserRoleIds) {
        if (loggedInUserRoleIds.contains(RoleRepository.SYSTEM_ADMIN_USER_ROLE_ID))
            //exclude ws user
            return mapList(roleRepository.findByDeletedFalseAndActivatedAndIdNot(true,RoleRepository.WS_USER));
        // exclude system admin and ws user roles in returned list
        return mapList(roleRepository.findByDeletedFalseAndActivatedTrueAndIdNotIn(RoleRepository.EXCLUDED_USERS_ROLES_ID_LIST));
    }

    /**
     * Find all active roles.
     * @return
     */
    public List<RoleDto> findActive() {
        // exclude system admin and system user roles from returned list
        return mapList(roleRepository.findByDeletedFalseAndActivatedTrueAndIdNotIn(RoleRepository.EXCLUDED_USERS_ROLES_ID_LIST));
    }


    /**
     * Deletes a role from the system
     *
     * @param roleId the id of the role to delete
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void deleteRole(long roleId) {
        roleRepository.markDeleted(roleId);
    }

    /**
     * Activate inactive role
     *
     * @param roleId
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void activateRole(long roleId) {
        roleRepository.activate(roleId);
    }

    /**
     * Deactivate active role
     *
     * @param roleId
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void deactivateRole(long roleId) {
        roleRepository.deactivate(roleId);
    }

    /**
     * Find new user default role.
     * @return
     */
    public RoleDto findNewUserDefaultRole() {
        return getMapper().fromEntity(roleRepository.findById(RoleRepository.SYSTEM_USER_ROLE_ID), mappingContext);
    }
}
