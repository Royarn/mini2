package com.royarn.mini.service;

import com.royarn.mini.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    /**
     * @param user
     * @return
     */
    List<UserRole> get(List<String> ids);

    /**
     *
     * @return
     */
    List<UserRole> list();

    /**
     *
     * @param user
     * @return
     */
    int insert(UserRole user);

    /**
     * @param users
     * @return
     */
    List<UserRole> batchInsert(List<UserRole> userRoles);

    /**
     * @param user
     * @return
     */
    UserRole update(UserRole userRole);

    /**
     * @param ids
     * @return
     */
    int delete(List<String> ids);
}
