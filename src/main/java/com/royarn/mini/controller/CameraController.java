package com.royarn.mini.controller;

import com.alibaba.fastjson.JSON;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.service.CameraService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 11:40
 */
@RestController
@RequestMapping("/app")
public class CameraController {

    @Resource
    private CameraService service;

    @ApiOperation("查询摄像机")
    @GetMapping("/get/{deviceId}")
    public Camera get(@PathVariable String deviceId) {
        if (deviceId == null) {
            return null;
        }
        return service.selectOne(deviceId);
    }

    @ApiOperation("摄像机列表")
    @PostMapping("/list")
    public List<Camera> list(@RequestParam Integer pageSize,
                             @RequestParam Integer pageNum) {
        if (pageNum == null || pageSize ==null) {
            return null;
        }
        return service.list(pageSize, pageNum);
    }

    @ApiOperation("新增摄像机")
    @PostMapping("/camera/save")
    public String save(@RequestBody Camera camera) {
        if (camera == null) {
            return "数据不能为空！";
        }
        service.addOne(camera);
        return "success";
    }

    @ApiOperation("修改摄像机")
    @PostMapping("/camera/update")
    public String update(@RequestBody Camera camera) {
        if (camera == null || camera.getId() == null) {
            return "Id不能为空！";
        }
        service.update(camera);
        return "success";
    }

    @ApiOperation("删除摄像机")
    @PostMapping("/camera/del")
    public String delete(@RequestParam String id) {
        if (id == null) {
            return "ID不能为空！";
        }
        return JSON.toJSONString(service.delete(id));
    }
}
