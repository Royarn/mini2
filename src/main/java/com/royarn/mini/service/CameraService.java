package com.royarn.mini.service;

import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.entity.CameraVo;

import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 11:50
 */
public interface CameraService {

    /**
     *  根据设备id查询指定摄像机
     */
    Camera selectOne(String cameraId);
    /**
     * 分页
     */
    List<Camera> list(int pageSize, int pageNum);

    /**
     * 插入
     */
    void addOne(Camera camera);

    /**
     * 更新
     */
    void update(Camera camera);

    /**
     * 删除
     */
    DeleteResult delete(List<String> ids);

    /**
     * 生成摄像机ID
     */
    String generateCameraId(String deviceIp, Long chanNo);

    /**
     * 模糊查询结果分页
     */
    List<CameraVo> qryCameraOfGroupByPage(int currentPage, int pageSize, String groupId, String regex);

    /**
     * 统计模糊查询结果
     */
    Long qryCameraOfGroupCount(String groupId, String regex);
}
