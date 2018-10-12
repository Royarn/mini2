package com.royarn.mini.service.impl;

import com.royarn.mini.dao.RoleMapper;
import com.royarn.mini.entity.Role;
import com.royarn.mini.entity.RoleExample;
import com.royarn.mini.service.RoleService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.CollectionUtil;
import com.royarn.mini.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-11
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mapper;

    @Override
    public List<Role> get(List<String> ids) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.selectByExample(example);
    }

    @Override
    public List<Role> list() {
        return mapper.selectByExample(null);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = BusinessException.class)
    public int insert(Role role) {
        if (StringUtils.isEmpty(role.getName())) {
            throw new BusinessException("角色名称不能为空！");
        }
        role.setId(UUID.randomUUID().toString());
        return mapper.insert(role);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = BusinessException.class)
    public List<Role> batchInsert(List<Role> roles) {
        for (Role role : roles) {
            role.setId(UUID.randomUUID().toString());
        }
        mapper.batchInsert(roles);
        return roles;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = BusinessException.class)
    public Role update(Role role) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(role.getName());
        List<Role> roles = mapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(roles)) {throw new BusinessException("角色名称重复！");}
        mapper.updateByPrimaryKey(role);
        return role;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = BusinessException.class)
    public int delete(List<String> ids) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.deleteByExample(example);
    }
}
