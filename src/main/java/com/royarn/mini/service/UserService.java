package com.royarn.mini.service;

import com.royarn.mini.entity.LocalUser;

import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 16:53
 */
public interface UserService {

    List<LocalUser> findAll();

    int insertUser(LocalUser user);
}
