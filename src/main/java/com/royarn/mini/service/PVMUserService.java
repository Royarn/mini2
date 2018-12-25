package com.royarn.mini.service;

import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.entity.User;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-24
 */
public interface PVMUserService {

    /**
     *  根据设备id查询指定摄像机
     */
    User selectOne(String id);
    /**
     * 分页
     */
    List<User> list(int currentPage, int pageSize);

    /**
     * 插入
     */
    User addOne(String body);

    /**
     * 更新
     */
    User update(String id, String body);

    /**
     * 删除
     */
    DeleteResult delete(List<String> ids);

    /**
     * 获取所有设备
     */
    List<User> getAllGroups();

    /**
     * 统计所有设备
     */
    Long count();

    /**
     * 查询所有子分组
     */
    List<User> qryChildGroups(String parentId);

    /**
     * 分页查询子分组
     */
    List<User> qryChildGroupsByPage(int currentPage, int pageSize, String parentId);

    /**
     * 统计子分组数量
     */
    Long getChildGroupCount(String parentId);

    /**
     * 模糊查询结果分页
     */
    List<User> qryUsersOfGroupByPage(int currentPage, int pageSize, String groupId, String regex);

    /**
     * 统计模糊查询结果
     */
    Long qryUsersOfGroupCount(String groupId, String regex);
}
