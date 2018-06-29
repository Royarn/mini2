package com.royarn.mini.service;

import com.royarn.mini.entity.TUser;

import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 10:22
 */
public interface TUserService {

    List<TUser> findAll();

    int insertUser(TUser user);
}
