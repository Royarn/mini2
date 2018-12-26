package com.royarn.mini.service;

import com.royarn.mini.entity.PermissionCamera;
import com.royarn.mini.entity.PermissionGroup;
import com.royarn.mini.entity.ReqCamera;

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
    void groupCommit(String groupId, boolean flag, List<String> ids, String userId);

    /**
     * 摄像机权限提交
     */
    void cameraCommit(ReqCamera reqCamera);
}
