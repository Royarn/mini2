package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.Role;
import com.royarn.mini.service.RoleService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.CollectionUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.royarn.mini.config.Result.error;
import static com.royarn.mini.config.Result.ok;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-11
 */
@RestController
@RequestMapping(value = "/app/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @ApiOperation(value = "角色列表")
    @GetMapping(value = "/list")
    public Result list() {
        return ok().property("roles", service.list());
    }

    @ApiOperation("查询角色")
    @PostMapping(value = "/get")
    public Result get(@RequestBody List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return error("角色ID不能为空！");
        }
        return ok().property("roles", service.get(ids));
    }

    @ApiOperation("添加角色")
    @PostMapping(value = "add")
    public Result add(@RequestBody List<Role> roles) {
        if (CollectionUtil.isEmpty(roles)) {
            return error("角色信息不能为空");
        }
        service.batchInsert(roles);
        return ok();
    }

    @ApiOperation("修改角色")
    @PostMapping(value = "/update")
    public Result update(@RequestBody Role role) {
        service.update(role);
        return ok();
    }

    @ApiOperation("删除角色")
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return error("角色列表不能为空！");
        }
        service.delete(ids);
        return ok();
    }
}