package com.royarn.mini.service.impl;

import com.royarn.mini.dao.DevCameraMapper;
import com.royarn.mini.dao.GroupMapper;
import com.royarn.mini.dao.TDeviceMapper;
import com.royarn.mini.entity.*;
import com.royarn.mini.service.DevDeviceService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.PinYinUtil;
import com.royarn.mini.util.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-28
 */
@Service
public class DevDeviceServiceImpl implements DevDeviceService {

    @Resource
    TDeviceMapper mapper;
    @Resource
    DevCameraMapper cameraMapper;
    @Resource
    GroupMapper groupMapper;

    @Override
    public List<TDevice> list() {
        return mapper.selectByExample(null);
    }

    @Override
    public void insert() {

        List<TDevice> list = list();
        for (TDevice device : list) {
//            List<DevCamera> cameras = new ArrayList<>();
//            for (int i = 0;i<10000;i++) {
//                DevCamera camera = new DevCamera();
//                camera.setId(UUID.randomUUID().toString());
//                camera.setDeviceId(device.getId());
//                camera.setChanNo(i + "");
//                camera.setName(device.getName() + "_" + i);
//                camera.setPinYin(PinYinUtil.getPinYinString(device.getName()).pinyin);
//                camera.setPinYinAd(PinYinUtil.spellFirst(device.getName()));
//                camera.setGroupId(device.getGroupId());
//                camera.setNodeId("e83490bd-4ce5-4afc-9644-641c32fc0866");
//                camera.setType("local");
//                camera.setChanSort(String.valueOf(i));
//                cameras.add(camera);
        //    }
            //cameraMapper.batchInsert(cameras);
        }
    }

    @Override
    public void insertMain() {
        //先插入分组
        GroupExample example = new GroupExample();
        GroupExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdIsNull();
        Group group = groupMapper.selectByExample(example).get(0);
        List<Group> groupList = new ArrayList<>();
        if (null == group) { throw new BusinessException("缺少根分组！"); }
        for (int i=1;i<=1000;i++) {
            Group tmp = new Group();
            tmp.setId(UUID.randomUUID().toString());
            tmp.setParentId(group.getId());
            tmp.setName("派出所00" + String.valueOf(i));
            tmp.setAreaCode("100" + String.valueOf(i));
            tmp.setMappingCode("100" + String.valueOf(i));
            tmp.setSort(i);
            tmp.setDescription("派出所00" + String.valueOf(i));
            tmp.setCode("100" + String.valueOf(i));
            groupList.add(tmp);
        }
        groupMapper.batchInsert(groupList);

        //插入设备
        List<DevDevice> devDevices = new ArrayList<>();
        for (int j=0;j<groupList.size();j++) {
            DevDevice devDevice = new DevDevice();
            devDevice.setId(UUID.randomUUID().toString());
            //devDevice.se
        }
    }

    @Override
    public void insertCameraBySort() {
        //设备排序
        List<TDevice> list = orderBy();
        //分组排序
        List<Group> groupList = orderByGroup();
        int index = 1;
        int chanSort = 1;
        for (TDevice device: list) {
            for (int k=0;k<10;k++) {
                List<DevCamera> cameras = new ArrayList<>();
                for (int i = 0;i<10000;i++) {
                    DevCamera camera = new DevCamera();
                    camera.setId(UUID.randomUUID().toString());
                    camera.setDeviceId(device.getId());
                    camera.setChanNo(chanSort + "");
                    camera.setName(device.getName() + "_" + i);
                    camera.setPinYin(PinYinUtil.getPinYinString(device.getName()).pinyin);
                    camera.setPinYinAd(PinYinUtil.spellFirst(device.getName()));
                    camera.setGroupId(groupList.get(index).getId());
                    camera.setNodeId("bc2fc80e-ff2f-494a-9234-b2350dc6cf34");
                    camera.setType("local");
                    camera.setChanSort(String.valueOf(chanSort));
                    chanSort++;
                    cameras.add(camera);
                }
                cameraMapper.batchInsert(cameras);
                index++;
            }
        }
    }

    private List<TDevice> orderBy() {
        return mapper.orderBySort();
    }
    private List<Group> orderByGroup() {
        return groupMapper.orderByGroup();
    }
}
