package com.royarn.mini.service.impl;

import com.royarn.mini.dao.MenuMapper;
import com.royarn.mini.entity.Menu;
import com.royarn.mini.entity.MenuExample;
import com.royarn.mini.service.MenuService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.CollectionUtil;
import com.royarn.mini.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-11
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper mapper;

    @Override
    public List<Menu> get(List<String> ids) {
        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.selectByExample(example);
    }

    @Override
    public List<Menu> list() {
        return mapper.selectByExample(null);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public int insert(Menu menu) {
        if (StringUtils.isEmpty(menu.getName())) {
            throw new BusinessException("名称不能为空！");
        }
        menu.setId(UUID.randomUUID().toString());
        return mapper.insert(menu);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public List<Menu> batchInsert(List<Menu> menuList) {
        List<Menu> realList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (StringUtils.isNotEmpty(menu.getName())) {
                menu.setId(UUID.randomUUID().toString());
                realList.add(menu);
            }
        }
        mapper.batachInsert(realList);
        return realList;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public Menu update(Menu menu) {
        if (StringUtils.isEmpty(menu.getName())) {throw new BusinessException("名称不能为空！");}
        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(menu.getId());
        criteria.andNameEqualTo(menu.getName());
        List<Menu> menuList = mapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(menuList)) {throw new BusinessException("功能已存在！");}
        mapper.updateByPrimaryKey(menu);
        return menu;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public int delete(List<String> ids) {
        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.deleteByExample(example);
    }
}
