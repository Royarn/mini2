package com.royarn.mini.service.impl;

import com.google.gson.Gson;
import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.entity.CameraVo;
import com.royarn.mini.entity.Group;
import com.royarn.mini.service.AbstractService;
import com.royarn.mini.service.CameraService;
import com.royarn.mini.util.PinYinUtil;
import com.royarn.mini.util.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public Camera selectOne(String cameraId) {
        MongoTemplate template = config.mongoTemplate();
        if (cameraId == null) {
            throw new RuntimeException("设备ID不能为空！");
        }
        //Query
        Camera camera = template.findOne(new Query(Criteria.where("id").
                is(cameraId)), Camera.class);
        String body = new Gson().toJson(camera);
        Group group = template.findOne(new Query(Criteria.where("id").is(camera.getGroupId())), Group.class);
        CameraVo cameraVo = new Gson().fromJson(body, CameraVo.class);
        cameraVo.setGroupName(group.getName());
        return cameraVo;
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
        if (StringUtils.isEmpty(camera.getName())) {
            camera.setPinYin(PinYinUtil.getPinYinString(camera.getName()).pinyin);
            camera.setPinYinAd(PinYinUtil.spellFirst(camera.getName()));
        }
        config.mongoTemplate().save(camera);
    }

    @Override
    public DeleteResult delete(List<String> ids) {
        DeleteResult result = config.mongoTemplate().remove(new Query(Criteria.where("id").in(ids)), Camera.class);
        return result;
    }

    @Override
    public String generateCameraId(String deviceIp, Long chanNo) {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<CameraVo> qryCameraOfGroupByPage(int currentPage, int pageSize, String groupId, String regex) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(regex)) {
            Pattern pattern = Pattern.compile("^.*"+regex+".*$", Pattern.CASE_INSENSITIVE);
            criteria.andOperator( Criteria.where("name").regex(regex));
        } else {
            criteria.andOperator(Criteria.where("groupId").is(groupId));
        }
        List<Camera> cameras = config.mongoTemplate().find(
                new Query(criteria)
                .limit(pageSize)
                .skip(currentPage), Camera.class);
        String groupName = config.mongoTemplate().findOne(new Query(Criteria.where("id").is(groupId)), Group.class).getName();
        List<CameraVo> cameraVos = cameras.stream()
                .map(camera -> {
                    String data = new Gson().toJson(camera);
                    CameraVo temp = new Gson().fromJson(data, CameraVo.class);
                    temp.setGroupName(groupName);
                    return temp;
                })
                .collect(Collectors.toList());
        return cameraVos;
    }

    @Override
    public Long qryCameraOfGroupCount(String groupId, String regex) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(regex)) {
            Pattern pattern = Pattern.compile("^.*"+regex+".*$", Pattern.CASE_INSENSITIVE);
            criteria.andOperator(Criteria.where("name").regex(pattern));
        }
        criteria.andOperator(Criteria.where("groupId").is(groupId));
        return config.mongoTemplate().count(new Query(criteria), Camera.class);
    }
}