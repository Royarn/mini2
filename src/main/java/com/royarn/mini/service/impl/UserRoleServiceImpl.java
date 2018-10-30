package com.royarn.mini.service.impl;

import com.royarn.mini.dao.UserRoleMapper;
import com.royarn.mini.entity.UserRole;
import com.royarn.mini.entity.UserRoleExample;
import com.royarn.mini.service.UserRoleService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-11
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper mapper;

    @Override
    public List<UserRole> get(List<String> ids) {
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.selectByExample(example);
    }

    @Override
    public List<UserRole> list() {
        return mapper.selectByExample(null);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public int insert(UserRole user) {
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(user.getRoleId());
        criteria.andUserIdEqualTo(user.getUserId());
        List<UserRole> userRoles = mapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(userRoles)) {
            throw new BusinessException("该用户角色已存在！");
        }
        user.setId(UUID.randomUUID().toString());
        return mapper.insert(user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public List<UserRole> batchInsert(List<UserRole> userRoles) {
        UserRoleExample example = new UserRoleExample();
        Map<String, UserRole> map = new HashMap<>();
        List<UserRole> realList = new ArrayList<>();
        //查询已有
        for (UserRole userRole : userRoles) {
            UserRoleExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(userRole.getRoleId());
            criteria.andRoleIdEqualTo(userRole.getRoleId());
            example.or(criteria);
        }
        List<UserRole> userRoleList = mapper.selectByExample(example);
        for (UserRole userRole : userRoleList) {
            map.put(userRole.getRoleId() + userRole.getUserId(), userRole);
        }
        //数据过滤
        for (UserRole userRole : userRoles) {
            if (!map.containsKey(userRole.getRoleId() + userRole.getUserId())) {
                userRole.setId(UUID.randomUUID().toString());
                realList.add(userRole);
            }
        }
        if (CollectionUtil.isNotEmpty(realList)) {mapper.batchInsert(realList);}
        return realList;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public UserRole update(UserRole userRole) {
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(userRole.getId());
        criteria.andUserIdEqualTo(userRole.getUserId());
        criteria.andRoleIdEqualTo(userRole.getRoleId());
        if (CollectionUtil.isNotEmpty(mapper.selectByExample(example))) {
            throw new BusinessException("用户角色已存在！");
        }
        mapper.updateByPrimaryKey(userRole);
        return userRole;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public int delete(List<String> ids) {
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.deleteByExample(example);
    }
}
