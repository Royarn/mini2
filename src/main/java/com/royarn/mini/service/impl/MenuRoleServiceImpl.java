package com.royarn.mini.service.impl;

import com.royarn.mini.dao.MenuRoleMapper;
import com.royarn.mini.entity.MenuRole;
import com.royarn.mini.entity.MenuRoleExample;
import com.royarn.mini.service.MenuRoleService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.CollectionUtil;
import com.royarn.mini.util.StringUtils;
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
public class MenuRoleServiceImpl implements MenuRoleService {

    private MenuRoleMapper mapper;

    @Override
    public List<MenuRole> get(List<String> ids) {
        MenuRoleExample example = new MenuRoleExample();
        MenuRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.selectByExample(example);
    }

    @Override
    public List<MenuRole> list() {
        return mapper.selectByExample(null);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = BusinessException.class)
    public int insert(MenuRole menuRole) {
        if (StringUtils.isEmpty(menuRole.getMenuId()) || StringUtils.isEmpty(menuRole.getRoleId()))
            throw new BusinessException("数据错误");
        menuRole.setId(UUID.randomUUID().toString());
        return mapper.insert(menuRole);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = BusinessException.class)
    public int batchInsert(List<MenuRole> menuRoles) {
        return mapper.batchInsert(menuRoles);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = BusinessException.class)
    public MenuRole update(MenuRole menuRole) {
        MenuRoleExample example = new MenuRoleExample();
        MenuRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(menuRole.getId());
        criteria.andMenuIdEqualTo(menuRole.getMenuId());
        criteria.andRoleIdNotEqualTo(menuRole.getRoleId());
        List<MenuRole> menuRoles = mapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(menuRoles))
            throw new BusinessException("该角色已配置！");
        return menuRole;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = BusinessException.class)
    public int delete(List<String> ids) {
        MenuRoleExample example = new MenuRoleExample();
        MenuRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.deleteByExample(example);
    }
}
