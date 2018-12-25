package com.royarn.mini.service;

import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.entity.Server;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */
public interface ServerService {

    /**
     *  根据设备id查询指定摄像机
     */
    Server selectOne(String device_id);
    /**
     * 分页
     */
    List<Server> list(int pageSize, int pageNum);

    /**
     * 插入
     */
    void addOne(Server server);

    /**
     * 更新
     */
    void update(Server server);

    /**
     * 删除
     */
    DeleteResult delete(String id);

    /**
     * 批量添加
     */
    List<Server> batch(String body);

    /**
     * 模糊查询
     */
    List<Server> qryServers(Integer offset, Integer pageSize, String search, List<String> types);

    /**
     * 统计
     */
    Long count(String search, List<String> types);
}
