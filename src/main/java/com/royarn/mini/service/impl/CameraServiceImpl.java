package com.royarn.mini.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.service.AbstractService;
import com.royarn.mini.service.CameraService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 11:54
 */
@Service
public class CameraServiceImpl extends AbstractService implements CameraService {

    @Resource
    private MongoConfig config;

    @Override
    public Camera selectOne(String device_id) {
        MongoTemplate template = config.mongoTemplate();
        if (device_id == null) {
            throw new RuntimeException("设备ID不能为空！");
        }
        //Query
        return template.findOne(new Query(Criteria.where("device_id").
                is(device_id)), Camera.class);
    }

    @Override
    public List<Camera> list(int pageSize, int pageNum) {
        return config.mongoTemplate()
                .find(new Query().limit(pageSize).skip(Long.valueOf(pageNum)), Camera.class);
    }

    @Override
    public void addOne(Camera camera) {
        config.mongoTemplate().insert(camera);
    }

    @Override
    public void update(Camera camera) {
        config.mongoTemplate().save(camera);
    }

    @Override
    public DeleteResult delete(String id) {
        DeleteResult result = config.mongoTemplate().remove(id);
        return result;
    }
}
