package com.royarn.mini.controller;

import com.google.gson.Gson;
import com.royarn.mini.config.Result;
import com.royarn.mini.entity.ReqCamera;
import com.royarn.mini.service.PermissionGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-24
 */
@RestController
@RequestMapping(value = "/npauth", consumes = MediaType.ALL_VALUE)
public class PermissionController {

    @Resource
    private PermissionGroupService groupService;

    @ApiOperation("查询所有权限")
    @GetMapping("/v1/list")
    public Result group() {
        return Result.ok().property("group", groupService.group());
    }

    @ApiOperation("查询本部权限")
    @GetMapping("/v1/{id}")
    public Result self(@PathVariable String id) {
        return Result.ok().property("group", groupService.self(id));
    }

    @ApiOperation("分组权限提交")
    @PutMapping("/v1/{id}/{userId}/{checked}")
    public Result groupPermission(@PathVariable String id,
                         @PathVariable String userId,
                         @PathVariable boolean checked) {
//        if (null == ids || ids.size() == 0) {
//            groupService.commit(id, checked, null, userId);
//        }
        groupService.groupCommit(id, checked, null, userId);
        return Result.ok();
    }

    @ApiOperation("摄像机权限提交")
    @PostMapping(value = "/v1/camera/permission")
    public Result cameraPermission(@RequestBody String body) {
        ReqCamera reqCamera = new Gson().fromJson(body, ReqCamera.class);
        groupService.cameraCommit(reqCamera);
        return Result.ok();
    }
}