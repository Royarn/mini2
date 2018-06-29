package com.royarn.mini.service.impl;

import com.royarn.mini.dao.LocalUserMapper;
import com.royarn.mini.entity.LocalUser;
import com.royarn.mini.entity.LocalUserExample;
import com.royarn.mini.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 16:54
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private LocalUserMapper mapper;

    @Override
    public List<LocalUser> findAll() {
        return mapper.selectByExample(new LocalUserExample());
    }

    @Override
    public int insertUser(LocalUser user) {
        return mapper.insert(user);
    }
}
