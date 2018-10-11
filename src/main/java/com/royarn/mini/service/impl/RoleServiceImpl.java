package com.royarn.mini.service.impl;

import com.royarn.mini.entity.Role;
import com.royarn.mini.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-11
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> get(List<String> ids) {
        return null;
    }

    @Override
    public List<Role> list() {
        return null;
    }

    @Override
    public int insert(Role role) {
        return 0;
    }

    @Override
    public List<Role> batchInsert(List<Role> roles) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public int delete(List<String> ids) {
        return 0;
    }
}
