package com.royarn.mini.service.impl;

import com.google.gson.*;
import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.*;
import com.royarn.mini.service.CameraService;
import com.royarn.mini.service.DeviceService;
import com.royarn.mini.support.BusinessException;
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
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private MongoConfig config;

    @Resource
    private CameraService cameraService;

    @Override
    public DeviceEditVo selectOne(String id) {
        Device device = config.mongoTemplate().findOne(new Query(Criteria.where("id").is(id)), Device.class);
        SdkAccess sdkAccess = config.mongoTemplate().findOne(new Query(Criteria.where("id").is(id)), SdkAccess.class);
        String info = new Gson().toJson(device);
        DeviceEditVo deviceEditVo = new Gson().fromJson(info, DeviceEditVo.class);
        deviceEditVo.setGbId(sdkAccess.getGbId());
        deviceEditVo.setPassword(sdkAccess.getPassword());
        deviceEditVo.setProtocol(sdkAccess.getProtocol());
        deviceEditVo.setUsername(sdkAccess.getUsername());
        return deviceEditVo;
    }

    @Override
    public List<Device> list(int currentPage, int pageSize) {
        return null;
    }

    @Override
    public Device addOne(String body) {
        MongoTemplate template = config.mongoTemplate();
        Device device = new Gson().fromJson(body, Device.class);
        if (StringUtils.isEmpty(device.getHost())) { device.setHost(null);}
        Group group = template.findOne(new Query(Criteria.where("id").is(device.getGroupId())), Group.class);
        if (null == group) { throw new BusinessException("资源分组不存在！"); }
        Query query = new Query();
        query.addCriteria(Criteria.where("host").is(device.getHost()));
        query.addCriteria(Criteria.where("port").is(device.getPort()));
        List<Device> devices = template.find(query, Device.class);
        if (null != devices && devices.size() > 0) { throw new BusinessException("IP地址和端口重复！"); }
        device.setId(UUID.randomUUID().toString());
        device.setDeviceType("encoder");

        final JsonObject deviceJson = new JsonParser().parse(body).getAsJsonObject();
        String protocol = deviceJson.getAsJsonPrimitive("protocol").getAsString();
        if ("siphost".equals(protocol)) {
            GbAccess gbAccess = new GsonBuilder().create().fromJson(body, GbAccess.class);
            gbAccess.setPass(deviceJson.get("password").getAsString());
            gbAccess.setId(device.getId());
            JsonElement gbId = deviceJson.get("gbId");
            device.setName(gbId.getAsString());
            device.setPinYin(PinYinUtil.getPinYinString(device.getName()).pinyin);
            device.setPinYinAd(PinYinUtil.spellFirst(device.getName()));
            device.setAccess("gb");
            if (StringUtils.isEmpty(gbAccess.getGbId())) { throw new BusinessException("国标ID不能为空！"); }
            List<GbAccess> accesses = template.find(new Query(Criteria.where("gbId").is(gbAccess.getGbId())), GbAccess.class);
            if (accesses != null) { throw new BusinessException("国标ID已存在！"); }
            template.insert(device);
            template.insert(gbAccess);
        } else {
            SdkAccess sdkAccess = new GsonBuilder().create().fromJson(body, SdkAccess.class);
            sdkAccess.setId(device.getId());
            JsonElement host = deviceJson.get("host");
            device.setName(host.getAsString());
            device.setPinYin(PinYinUtil.getPinYinString(device.getName()).pinyin);
            device.setPinYinAd(PinYinUtil.spellFirst(device.getName()));
            device.setAccess("sdk");
            if (StringUtils.isEmpty(sdkAccess.getGbId())) { throw new BusinessException("SDK ID不能为空！"); }
            List<SdkAccess> accesses = template.find(new Query(Criteria.where("gbId").is(sdkAccess.getGbId())), SdkAccess.class);
            if (accesses != null && accesses.size() > 0) { throw new BusinessException("国标ID已存在！"); }
            template.insert(device);
            template.insert(sdkAccess);
            JsonElement channelNum = deviceJson.get("channelNum");
            if (channelNum == null || 0 == channelNum.getAsInt()) { throw new BusinessException("通道数为null"); }
            int channels = channelNum.getAsInt();

            //非国标的产生摄像机
            insertSdkChannel(body, device, channels);
        }
        return device;
    }

    @Override
    public Device update(String id, String body) {
        Device device = new Gson().fromJson(body, Device.class);
        if (StringUtils.isEmpty(device.getHost())) { device.setHost(null);}
        JsonObject deviceJson = new JsonParser().parse(body).getAsJsonObject();
        String protocol = deviceJson.getAsJsonPrimitive("protocol").getAsString();
        if (deviceJson.has("groupId")) {
            String groupId = deviceJson.get("groupId").getAsString();
            List<Group> groupList = config.mongoTemplate().find(new Query(Criteria.where("id").is(groupId)), Group.class);
            if (groupList.size() == 0) { throw new BusinessException("分组不存在！"); }
        }
        if ("siphost".equals(protocol)) {
            GbAccess gbAccess = new Gson().fromJson(body, GbAccess.class);
            if (deviceJson.has("password"))
                gbAccess.setPass(deviceJson.get("password").getAsString());
            gbAccess.setId(device.getId());
            config.mongoTemplate().save(gbAccess);
        } else {
            SdkAccess sdkAccess = new Gson().fromJson(body, SdkAccess.class);
            sdkAccess.setId(device.getId());
            config.mongoTemplate().save(sdkAccess);

            //后续忽略
        }

        if(StringUtils.isNotEmpty(device.getName())){
            device.setPinYin(PinYinUtil.getPinYinString(device.getName()).pinyin);
            device.setPinYinAd(PinYinUtil.spellFirst(device.getName()));
        }

        config.mongoTemplate().save(device);
        return device;
    }

    @Override
    public DeleteResult delete(List<String> ids) {
        return config.mongoTemplate().remove(new Query(Criteria.where("id").in(ids)), Device.class);
    }

    @Override
    public List<Device> getAllGroups() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public List<Device> qryChildGroups(String parentId) {
        return null;
    }

    @Override
    public List<Device> qryChildGroupsByPage(int currentPage, int pageSize, String parentId) {
        return null;
    }

    @Override
    public Long getChildGroupCount(String parentId) {
        return null;
    }

    @Override
    public List<DeviceVo> getDevicesOfGroupByPage(int currentPage, int pageSize, String groupId, String regex) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(regex)) {
            Pattern pattern = Pattern.compile("^.*"+regex+".*$", Pattern.CASE_INSENSITIVE);
            criteria.andOperator(Criteria.where("host").regex(pattern),
                    Criteria.where("port").regex(pattern));
        }
        criteria.andOperator(Criteria.where("groupId").is(groupId));
        List<Device> devices = config.mongoTemplate().find(new Query(criteria).limit(pageSize).skip(currentPage), Device.class);
        List<DeviceVo> deviceVos = devices.stream()
                .map(device -> {
                    DeviceVo deviceVo = new DeviceVo();
                    deviceVo.setAccess(device.getAccess());
                    deviceVo.setChannelNum(device.getChannelNum());
                    deviceVo.setGbId("11111111111111");
                    deviceVo.setHost(device.getHost());
                    deviceVo.setId(device.getId());
                    deviceVo.setName(device.getName());
                    deviceVo.setUseType(device.getUseType());
                    return deviceVo;
                })
                .collect(Collectors.toList());
        return deviceVos;
    }

    @Override
    public Long getDeviceOfGroupCount(String groupId, String regex) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(regex)) {
            Pattern pattern = Pattern.compile("^.*"+regex+".*$", Pattern.CASE_INSENSITIVE);
            criteria.andOperator(Criteria.where("host").regex(pattern),
                    Criteria.where("port").regex(pattern));
        }
        criteria.andOperator(Criteria.where("groupId").is(groupId));
        return config.mongoTemplate().count(new Query(criteria), Device.class);
    }

    private void insertSdkChannel(String channel, Device device, int channels) {
        MongoTemplate template = config.mongoTemplate();
        if ("camera".equals(device.getUseType())) {
            JsonObject jsonObject = new JsonParser().parse(channel).getAsJsonObject();
            int startChannelNum = jsonObject.get("startChannelNum").getAsInt();
            Camera camera = new Gson().fromJson(channel, Camera.class);
            DeviceChannel deviceChannel = new DeviceChannel();
            if (camera.getGroupId() == null) {
                throw new BusinessException("分组ID不能为空！");
            }
            List<Group> groupList = template.find(new Query(Criteria.where("id").is(camera.getGroupId())), Group.class);
            if (groupList.size() == 0) { throw new BusinessException("分组不存在！"); }
            if (device.getVoiceTalk() == 1){
                //默认生成一个语音通道
                //如果有一个语音通道，则默认给视频通道分配
            }
            for (Integer i = startChannelNum; i < channels+startChannelNum; i++) {
                camera.setId(UUID.randomUUID().toString());
                camera.setDeviceId(device.getId());
                camera.setChanNo(i + "");
                camera.setName(device.getName() + "_" + i);
                camera.setPinYin(PinYinUtil.getPinYinString(camera.getName()).pinyin);
                camera.setPinYinAd(PinYinUtil.spellFirst(camera.getName()));
                camera.setNodeId("");
                camera.setType("local");
                camera.setChanSort("");
                cameraService.addOne(camera);
            }
        }
    }
}
