package com.royarn.mini.service.impl;

import com.royarn.mini.entity.UserRole;
import com.royarn.mini.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-11
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Override
    public List<UserRole> get(List<String> ids) {
        return null;
    }

    @Override
    public List<UserRole> list() {
        return null;
    }

    @Override
    public int insert(UserRole user) {
        return 0;
    }

    @Override
    public List<UserRole> batchInsert(List<UserRole> userRoles) {
        return null;
    }

    @Override
    public UserRole update(UserRole userRole) {
        return null;
    }

    @Override
    public int delete(List<String> ids) {
        return 0;
    }
}
