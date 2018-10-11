package com.royarn.mini.service;

import com.royarn.mini.entity.Menu;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-11
 */
public interface MenuService {

    /**
     * @param ids
     * @return menu
     */
    List<Menu> get(List<String> ids);

    /**
     *
     * @return
     */
    List<Menu> list();

    /**
     *
     * @param user
     * @return
     */
    int insert(Menu menu);

    /**
     * @param users
     * @return
     */
    List<Menu> batchInsert(List<Menu> menuList);

    /**
     * @param user
     * @return
     */
    Menu update(Menu menu);

    /**
     * @param ids
     * @return
     */
    int delete(List<String> ids);
}
