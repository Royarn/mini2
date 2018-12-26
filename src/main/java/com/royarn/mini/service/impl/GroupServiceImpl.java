package com.royarn.mini.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.entity.Group;
import com.royarn.mini.entity.PermissionCamera;
import com.royarn.mini.entity.PermissionGroup;
import com.royarn.mini.service.GroupService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private MongoConfig config;

    @Override
    public Group selectOne(String id) {
        return config.mongoTemplate().findOne(new Query(Criteria.where("id").is(id)), Group.class);
    }

    @Override
    public List<Group> list(int currentPage, int pageSize) {
        return config.mongoTemplate().find(new Query().limit(pageSize).skip(currentPage), Group.class);
    }

    @Override
    public Group addOne(Group group) {
        group.setId(UUID.randomUUID().toString());
        config.mongoTemplate().insert(group);
        return group;
    }

    @Override
    public Group update(Group group) {
        config.mongoTemplate().save(group);
        return group;
    }

    @Override
    public DeleteResult delete(String id) {
        return config.mongoTemplate().remove(new Query(Criteria.where("id").is(id)), Group.class);
    }

    @Override
    public List<Group> getAllGroups() {
        return config.mongoTemplate().find(new Query(), Group.class);
    }

    @Override
    public Long count() {
        return config.mongoTemplate().count(new Query(), Group.class);
    }

    @Override
    public List<Group> qryChildGroups(String parentId) {
        return config.mongoTemplate().find(new Query(Criteria.where("parentId").is(parentId)), Group.class);
    }

    @Override
    public List<Group> qryChildGroupsByPage(int currentPage, int pageSize, String parentId) {
        return config.mongoTemplate().find(new Query(Criteria.where("parentId").is(parentId))
                .limit(pageSize)
                .skip(currentPage), Group.class);
    }

    @Override
    public Long getChildGroupCount(String parentId) {
        return config.mongoTemplate().count(new Query(Criteria.where("parentId").is(parentId)), Group.class);
    }

    @Override
    public List<Camera> getCameras(String id, String userId) {
        List<Camera> cameras = config.mongoTemplate().find(new Query(Criteria.where("groupId").is(id)), Camera.class);
        List<PermissionCamera> permissionCameras = config.mongoTemplate().find(new Query(Criteria.where("groupId").is(id)), PermissionCamera.class);
        Map<String, PermissionCamera> cameraMap = permissionCameras.stream()
                .collect(Collectors.toMap(PermissionCamera::getCameraId, value -> value, (k1, k2) -> k1));
        cameras = cameras.stream()
                .map(camera -> {
                    if (cameraMap.containsKey(camera.getId())) {
                        camera.setFlag(true);
                    } else {
                        camera.setFlag(false);
                    }
                    return camera;
                })
                .collect(Collectors.toList());
        return cameras;
    }

    @Override
    public List<PermissionGroup> getSelf(String id, String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("groupId").is(id));
        query.addCriteria(Criteria.where("userId").is(userId));
        return config.mongoTemplate().find(query, PermissionGroup.class);
    }
}
