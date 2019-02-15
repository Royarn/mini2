package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.TDevice;
import com.royarn.mini.service.DevCameraService;
import com.royarn.mini.service.DevDeviceService;
import com.royarn.mini.service.impl.TaskServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-28
 */
@RestController
@RequestMapping("/import")
public class ImportToolController {

    @Resource
    private DevCameraService service;
    @Resource
    private DevDeviceService devDeviceService;
    @Resource
    private TaskServiceImpl taskService;
    private ExecutorService executor = Executors.newCachedThreadPool();

    @ApiOperation("摄像机列表")
    @GetMapping("/list")
    public Result list() {
        return Result.ok().property("list", service.devList());
    }

    @ApiOperation("设备查询")
    @GetMapping("/delist")
    public Result ok() {
        List<TDevice> deviceList = devDeviceService.list();
        return Result.ok().property("list", devDeviceService.list());
    }

    @ApiOperation("/导入数据")
    @GetMapping("/import")
    public Result insert() {
        new Thread(() -> devDeviceService.insertCameraBySort()).start();
        return Result.ok().property("info", "数据导入中，请耐心等待......");
    }

    @ApiOperation("/测试任务")
    @GetMapping("/test")
    public Result test() {
         devDeviceService.insertMain();
        return Result.ok();
    }
}