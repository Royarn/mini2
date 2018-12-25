package com.royarn.mini.service;

import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.entity.Device;
import com.royarn.mini.entity.DeviceEditVo;
import com.royarn.mini.entity.DeviceVo;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */
public interface DeviceService {

    /**
     *  根据设备id查询指定摄像机
     */
    DeviceEditVo selectOne(String id);
    /**
     * 分页
     */
    List<Device> list(int currentPage, int pageSize);

    /**
     * 插入
     */
    Device addOne(String body);

    /**
     * 更新
     */
    Device update(String id, String body);

    /**
     * 删除
     */
    DeleteResult delete(List<String> ids);

    /**
     * 获取所有设备
     */
    List<Device> getAllGroups();

    /**
     * 统计所有设备
     */
    Long count();

    /**
     * 查询所有子分组
     */
    List<Device> qryChildGroups(String parentId);

    /**
     * 分页查询子分组
     */
    List<Device> qryChildGroupsByPage(int currentPage, int pageSize, String parentId);

    /**
     * 统计子分组数量
     */
    Long getChildGroupCount(String parentId);

    /**
     * 模糊查询结果分页
     */
    List<DeviceVo> getDevicesOfGroupByPage(int currentPage, int pageSize, String groupId, String regex);

    /**
     * 统计模糊查询结果
     */
    Long getDeviceOfGroupCount(String groupId, String regex);
}
