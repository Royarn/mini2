package com.royarn.mini.service;

import com.royarn.mini.entity.PermissionCamera;
import com.royarn.mini.entity.PermissionGroup;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-24
 */
public interface PermissionGroupService {

    /**
     * 分组本部权限
     */
    List<PermissionCamera> self(String groupId);

    /**
     * 查询所有分组
     */
    List<PermissionGroup> group();

    /**
     * 分组权限提交
     */
    void commit(String groupId, boolean flag, List<String> ids, String userId);
}
