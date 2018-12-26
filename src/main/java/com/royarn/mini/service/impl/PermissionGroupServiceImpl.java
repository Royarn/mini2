package com.royarn.mini.service.impl;

import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.entity.Group;
import com.royarn.mini.entity.PermissionCamera;
import com.royarn.mini.entity.PermissionGroup;
import com.royarn.mini.entity.ReqCamera;
import com.royarn.mini.service.PermissionGroupService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.CollectionUtil;
import com.royarn.mini.util.StringUtils;
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
    public void groupCommit(String groupId, boolean flag, List<String> ids, String userId) {
        if (flag) {
            //删除该分组下的所有子分组
            List<Group> childGroups = config.mongoTemplate().find(new Query(Criteria.where("parentId").is(groupId)), Group.class);
            List<String> childIds = childGroups.stream()
                    .map(Group::getId)
                    .collect(Collectors.toList());
            if (null != childIds && childIds.size() > 0) {
                config.mongoTemplate().remove(new Query(Criteria.where("id").in(childIds)), PermissionGroup.class);
            }
            //保存当前分组权限
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
            //当前分组信息
            Group current = config.mongoTemplate().findOne(new Query(Criteria.where("id").is(groupId)), Group.class);
            //更改权限为self
            if (null == current || StringUtils.isEmpty(current.getId())) { throw new BusinessException("分组不存在！"); }
//            PermissionGroup permissionGroup = config.mongoTemplate().findOne(new Query(Criteria.where("groupId").is(groupId)), PermissionGroup.class);
//            permissionGroup.setType("self");
//            config.mongoTemplate().save(permissionGroup);
            config.mongoTemplate().remove(new Query(Criteria.where("groupId").is(groupId)), PermissionGroup.class);
            //保存子分组权限为group
            List<Group> childGroups = config.mongoTemplate().find(new Query(Criteria.where("parentId").is(groupId)), Group.class);
            if (null != childGroups && childGroups.size() > 0) {
                List<PermissionGroup> childPermissions = childGroups.stream()
                        .map(group -> {
                            PermissionGroup temp = new PermissionGroup();
                            temp.setId(UUID.randomUUID().toString());
                            temp.setGroupId(group.getId());
                            temp.setParentId(groupId);
                            temp.setUserId(userId);
                            temp.setType("group");
                            return temp;
                        })
                        .collect(Collectors.toList());
                config.mongoTemplate().insert(childPermissions, PermissionGroup.class);
            }
            //删除分组下的摄像机
            config.mongoTemplate().remove(new Query(Criteria.where("groupId").is(groupId)), PermissionCamera.class);
        }
    }

    @Override
    public void cameraCommit(ReqCamera reqCamera) {
        if (StringUtils.isNotEmpty(reqCamera.getAddSelf())) {
            //保存self权限
            PermissionGroup permissionGroup = config.mongoTemplate()
                    .findOne(new Query(Criteria.where("groupId").is(reqCamera.getAddSelf())),
                            PermissionGroup.class);
            if (null == permissionGroup || StringUtils.isEmpty(permissionGroup.getId())) {
                PermissionGroup temp = new PermissionGroup();
                temp.setId(UUID.randomUUID().toString());
                temp.setType("self");
                temp.setUserId(reqCamera.getUserId());
                temp.setGroupId(reqCamera.getAddSelf());
                Group group = config.mongoTemplate()
                        .findOne(new Query(Criteria.where("id").is(reqCamera.getAddSelf())), Group.class);
                temp.setParentId(group.getParentId());
                config.mongoTemplate().insert(temp);
            }
            //删除摄像机权限
            config.mongoTemplate().remove(new Query(Criteria.where("groupId").in(reqCamera.getAddSelf())), PermissionCamera.class);
        } else if (StringUtils.isNotEmpty(reqCamera.getDelSelf())) {
            //删除self权限
            PermissionGroup temp = config.mongoTemplate().findOne(new Query(Criteria.where("groupId").is(reqCamera.getDelSelf())), PermissionGroup.class);
            config.mongoTemplate().remove(new Query(Criteria.where("groupId").is(reqCamera.getDelSelf())), PermissionGroup.class);
            //添加摄像机权限
            List<String> ids = reqCamera.getAddCameras();
            if (null != ids && ids.size() > 0) {
                List<PermissionCamera> cameraList = ids.stream()
                        .map(id -> {
                            PermissionCamera permissionCamera = new PermissionCamera();
                            permissionCamera.setId(UUID.randomUUID().toString());
                            permissionCamera.setCameraId(id);
                            permissionCamera.setGroupId(reqCamera.getDelSelf());
                            permissionCamera.setParentId(temp.getParentId());
                            permissionCamera.setUserId(reqCamera.getUserId());
                            return permissionCamera;
                        })
                        .collect(Collectors.toList());
                config.mongoTemplate().insert(cameraList, PermissionCamera.class);
            }
        } else {
            List<String> addIds = reqCamera.getAddCameras();
            List<String> delIds = reqCamera.getDelCameras();
            List<PermissionCamera> result = null;
            if (CollectionUtil.isNotEmpty(addIds)) {
                result = getPermissionCameras(addIds, reqCamera.getUserId());
                if (CollectionUtil.isNotEmpty(result)) {
                    config.mongoTemplate().insert(result, PermissionCamera.class);
                }
            }
            if (CollectionUtil.isNotEmpty(delIds)) {
                result = getPermissionCameras(delIds, reqCamera.getUserId());
                if (CollectionUtil.isNotEmpty(result)) {
                    result.stream()
                            .forEach(camera -> config.mongoTemplate().remove(camera));
                }
            }
        }
    }

    private List<PermissionCamera> getPermissionCameras(List<String> ids, String userId) {
        List<PermissionCamera> returnList = null;
        if (CollectionUtil.isNotEmpty(ids)) {
            Camera camera = config.mongoTemplate().findOne(new Query(Criteria.where("id").is(ids.get(0))), Camera.class);
            returnList = ids.stream()
                    .map(id -> {
                        PermissionCamera permissionCamera = new PermissionCamera();
                        permissionCamera.setId(UUID.randomUUID().toString());
                        permissionCamera.setCameraId(id);
                        permissionCamera.setGroupId(camera.getGroupId());
                        permissionCamera.setUserId(userId);
                        return permissionCamera;
                    })
                    .collect(Collectors.toList());
        }
        return returnList;
    }
}
