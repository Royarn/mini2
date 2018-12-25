package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.service.PermissionGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-24
 */
@RestController
@RequestMapping("/npauth")
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

    @ApiOperation("权限提交")
    @PutMapping("/v1/{id}/{userId}/{checked}")
    public Result commit(@PathVariable String id,
                         @PathVariable String userId,
                         @PathVariable boolean checked) {
//        if (null == ids || ids.size() == 0) {
//            groupService.commit(id, checked, null, userId);
//        }
        groupService.commit(id, checked, null, userId);
        return Result.ok();
    }
}