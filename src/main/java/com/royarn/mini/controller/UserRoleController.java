package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.UserRole;
import com.royarn.mini.service.UserRoleService;
import com.royarn.mini.util.CollectionUtil;
import com.royarn.mini.util.StringUtils;
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
@RequestMapping(value = "/app/user_role")
public class UserRoleController {

    @Autowired
    private UserRoleService service;

    @ApiOperation(value = "查询用户角色列表")
    @GetMapping(value = "/list")
    public Result list() {
        return ok().property("userRoles", service.list());
    }

    @ApiOperation(value = "查询用户角色")
    @PostMapping(value = "/get")
    public Result get(@RequestBody List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return error("用户角色ID不能为空！");
        }
        return ok().property("userRoles", service.get(ids));
    }

    @ApiOperation(value = "添加用户角色")
    @PostMapping(value = "/add")
    public Result add(@RequestBody UserRole userRole) {
        if (StringUtils.isEmpty(userRole.getRoleId()) || StringUtils.isEmpty(userRole.getRoleId())) {
            return error("用户或角色ID为空！");
        }
        return ok().property("userRole", service.insert(userRole));
    }

    @ApiOperation(value = "批量添加")
    @PostMapping(value = "/batch")
    public Result batch(@RequestBody List<UserRole> userRoles) {
        if (CollectionUtil.isEmpty(userRoles)) {
            return error("用户角色信息不能为空！");
        }
        return ok().property("userRoles", service.batchInsert(userRoles));
    }

    @ApiOperation(value = "修改用户角色")
    @PostMapping(value = "/update")
    public Result update(@RequestBody UserRole userRole) {
        if (StringUtils.isEmpty(userRole.getRoleId()) || StringUtils.isEmpty(userRole.getUserId())) {
            return error("用户角色信息不能为空！");
        }
        return ok().property("useroRole", service.update(userRole));
    }

    @ApiOperation(value = "删除用户角色")
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return error("用户角色ID不能为空！");
        }
        service.delete(ids);
        return ok();
    }
}