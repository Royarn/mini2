package com.royarn.mini.service;

import com.royarn.mini.entity.DevCamera;
import com.royarn.mini.entity.DevDevice;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-28
 */
public interface DevCameraService {

    List<DevCamera> list();

    void batchInsert();

    /**
     *
     */
    List<DevDevice> devList();
}
