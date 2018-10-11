package com.royarn.mini.service;

import com.royarn.mini.entity.MenuRole;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-11
 */
public interface MenuRoleService {

    /**
     * @param ids
     * @return menu
     */
    List<MenuRole> get(List<String> ids);

    /**
     *
     * @return
     */
    List<MenuRole> list();

    /**
     *
     * @param user
     * @return
     */
    int insert(MenuRole menuRole);

    /**
     * @param users
     * @return
     */
    int batchInsert(List<MenuRole> menuRoles);

    /**
     * @param user
     * @return
     */
    MenuRole update(MenuRole menuRole);

    /**
     * @param ids
     * @return
     */
    int delete(List<String> ids);
}
