package com.royarn.mini.service.impl;

import com.royarn.mini.entity.Menu;
import com.royarn.mini.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-11
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Override
    public List<Menu> get(List<String> ids) {
        return null;
    }

    @Override
    public List<Menu> list() {
        return null;
    }

    @Override
    public int insert(Menu menu) {
        return 0;
    }

    @Override
    public List<Menu> batchInsert(List<Menu> menuList) {
        return null;
    }

    @Override
    public Menu update(Menu menu) {
        return null;
    }

    @Override
    public int delete(List<String> ids) {
        return 0;
    }
}
