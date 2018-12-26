package com.royarn.mini.service;

import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.entity.Group;
import com.royarn.mini.entity.PermissionGroup;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */
public interface GroupService {

    /**
     *  根据设备id查询指定摄像机
     */
    Group selectOne(String id);
    /**
     * 分页
     */
    List<Group> list(int currentPage, int pageSize);

    /**
     * 插入
     */
    Group addOne(Group group);

    /**
     * 更新
     */
    Group update(Group group);

    /**
     * 删除
     */
    DeleteResult delete(String id);

    /**
     * 获取所有分组
     */
    List<Group> getAllGroups();

    /**
     * 统计所有分组
     */
    Long count();

    /**
     * 查询所有子分组
     */
    List<Group> qryChildGroups(String parentId);

    /**
     * 分页查询子分组
     */
    List<Group> qryChildGroupsByPage(int currentPage, int pageSize, String parentId);

    /**
     * 统计子分组数量
     */
    Long getChildGroupCount(String parentId);

    /**
     * 查询分组下的摄像机以及所有权限
     */
    List<Camera> getCameras(String id, String userId);

    /**
     *
     */
    List<PermissionGroup> getSelf(String id, String userId);
}
