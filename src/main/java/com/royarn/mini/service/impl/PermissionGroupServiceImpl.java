package com.royarn.mini.service.impl;

import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.Group;
import com.royarn.mini.entity.PermissionCamera;
import com.royarn.mini.entity.PermissionGroup;
import com.royarn.mini.service.PermissionGroupService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-24
 */
@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {

    private static final String ROOT_ID = "00000000-0000-0000-0000-000000000000";

    private static List<String> parentIds = new ArrayList<>();

    @Resource
    private MongoConfig config;

    @Override
    public List<PermissionCamera> self(String groupId) {
        return config.mongoTemplate().find(new Query(Criteria.where("groupId").is(groupId)), PermissionCamera.class);
    }

    @Override
    public List<PermissionGroup> group() {
        return config.mongoTemplate().find(new Query(Criteria.where("type").is("group")), PermissionGroup.class);
    }

    @Override
    public void commit(String groupId, boolean flag, List<String> ids, String userId) {
        if (flag) {
            String parentId = config.mongoTemplate()
                    .findOne(new Query(Criteria.where("id").is(groupId)), Group.class)
                    .getParentId();
            PermissionGroup permissionGroup = new PermissionGroup();
            permissionGroup.setId(UUID.randomUUID().toString());
            permissionGroup.setGroupId(groupId);
            permissionGroup.setParentId(parentId);
            permissionGroup.setType("group");
            permissionGroup.setUserId(userId);
            config.mongoTemplate().insert(permissionGroup);
        } else {
            //删除分组权限
            Group current = config.mongoTemplate().findOne(new Query(Criteria.where("id").is(groupId)), Group.class);
            config.mongoTemplate().remove(new Query(Criteria.where("id").is(groupId)), PermissionGroup.class);
            //更改权限为self 逐级向上更改
            parentIds = null;
            List<String> result = recursive(current, parentIds);
            List<Group> addIds = new ArrayList<>();
            result.stream()
                    .forEach(parentId -> {
                        List<Group> groups = config.mongoTemplate().find(new Query(Criteria.where("parentId").is(parentId)), Group.class);
                        addIds.addAll(groups);
                        config.mongoTemplate().remove(new Query(Criteria.where("groupId").is(parentId)), PermissionGroup.class); }
                        );
            List<PermissionGroup> permissionGroups = addIds.stream()
                    .filter(group -> group.getId().equals(current.getId()))
                    .map(group -> {
                        PermissionGroup permissionGroup = new PermissionGroup();
                        permissionGroup.setId(UUID.randomUUID().toString());
                        permissionGroup.setGroupId(group.getId());
                        permissionGroup.setParentId(group.getParentId());
                        permissionGroup.setUserId(userId);
                        permissionGroup.setType("self");
                        return permissionGroup;
                    })
                    .collect(Collectors.toList());
            config.mongoTemplate().insert(permissionGroups, PermissionGroup.class);

            //删除之前勾选的摄像机
            config.mongoTemplate().remove(new Query(Criteria.where("groupId").is(current.getId())), PermissionCamera.class);
            //新增摄像机权限
            if (null != ids && ids.size() > 0) {
                List<PermissionCamera> permissionCameras = ids.stream()
                        .map(id -> {
                            PermissionCamera permissionCamera = new PermissionCamera();
                            permissionCamera.setId(UUID.randomUUID().toString());
                            permissionCamera.setCameraId(id);
                            permissionCamera.setGroupId(current.getId());
                            permissionCamera.setParentId(current.getParentId());
                            permissionCamera.setUserId(userId);
                            return permissionCamera; })
                        .collect(Collectors.toList());
                config.mongoTemplate().insert(permissionCameras, PermissionCamera.class);
            }
        }
    }

    private List<String> recursive(Group current, List<String> parentIds) {
        if (null == current || ROOT_ID.equals(current.getParentId())) {
            return parentIds;
        } else {
            Group group = config.mongoTemplate().findOne(new Query(Criteria.where("id").is(current.getParentId())), Group.class);
            if (null != group) { parentIds.add(group.getId()); }
            recursive(group, parentIds);
        }
        return parentIds;
    }
}
