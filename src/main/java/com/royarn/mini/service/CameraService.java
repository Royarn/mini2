package com.royarn.mini.service;

import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.entity.Camera;

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
    Camera selectOne(String device_id);
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
    DeleteResult delete(String id);
}
