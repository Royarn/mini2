package com.royarn.mini.service;

import com.royarn.mini.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * @param ids
     * @return menu
     */
    List<Role> get(List<String> ids);

    /**
     *
     * @return
     */
    List<Role> list();

    /**
     *
     * @param user
     * @return
     */
    int insert(Role role);

    /**
     * @param users
     * @return
     */
    List<Role> batchInsert(List<Role> roles);

    /**
     * @param user
     * @return
     */
    Role update(Role role);

    /**
     * @param ids
     * @return
     */
    int delete(List<String> ids);
}
