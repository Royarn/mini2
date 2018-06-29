package com.royarn.mini.service.impl;

import com.royarn.mini.dao.TUserMapper;
import com.royarn.mini.entity.TUser;
import com.royarn.mini.entity.TUserExample;
import com.royarn.mini.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 10:22
 */

@Service
public class TUserServiceImpl implements TUserService {

    @Resource
    private TUserMapper userMapper;

    @Override
    public List<TUser> findAll() {
        return userMapper.selectByExample(new TUserExample());
    }

    @Override
    public int insertUser(TUser user) {
        return userMapper.insert(user);
    }
}
