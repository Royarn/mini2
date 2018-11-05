package com.royarn.mini.service;

import com.royarn.mini.entity.LocalUser;

import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 16:53
 */
public interface UserService {

    /**
     * @param user
     * @return
     */
    List<LocalUser> get(List<String> ids);

    /**
     *
     * @return
     */
    List<LocalUser> list();

    /**
     *
     * @param user
     * @return
     */
    int insert(LocalUser user);

    /**
     * @param users
     * @return
     */
    List<LocalUser> batchInsert(List<LocalUser> users);

    /**
     * @param user
     * @return
     */
    LocalUser update(LocalUser user);

    /**
     * @param ids
     * @return
     */
    int delete(List<String> ids);

    /**
     * @return
     */
    String insertDTS();
}