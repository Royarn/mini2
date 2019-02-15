package com.royarn.mini.service;

import com.royarn.mini.entity.DevDevice;
import com.royarn.mini.entity.TDevice;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-28
 */
public interface DevDeviceService {

    List<TDevice> list();

    void insert();

    void insertMain();

    void insertCameraBySort();
}
