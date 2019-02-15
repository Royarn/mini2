package com.royarn.mini.service.impl;

import com.royarn.mini.dao.DevCameraMapper;
import com.royarn.mini.dao.DevDeviceMapper;
import com.royarn.mini.entity.DevCamera;
import com.royarn.mini.entity.DevDevice;
import com.royarn.mini.service.DevCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-28
 */
@Service
public class DevCameraServiceImpl implements DevCameraService {

    @Resource
    private DevCameraMapper mapper;

    @Autowired
    private DevDeviceMapper devDeviceMapper;

    @Override
    public List<DevCamera> list() {
        return mapper.selectByExample(null);
    }

    @Override
    public void batchInsert() {

    }

    @Override
    public List<DevDevice> devList() {
        return devDeviceMapper.selectByExample(null);
    }
}
