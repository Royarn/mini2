package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.MenuRole;
import com.royarn.mini.service.MenuRoleService;
import com.royarn.mini.util.CollectionUtil;
import com.royarn.mini.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
@RequestMapping(value = "/app/menu_role")
public class MenuRoleController {

    @Resource
    private MenuRoleService service;

    @ApiOperation(value = "查询所有功能")
    @GetMapping("/list")
    public Result list() {
        List<MenuRole> userList = service.list();
        return ok().property("users", userList);
    }

    @ApiOperation(value = "查询角色对应功能列表")
    @PostMapping(value = "/get")
    public Result get(@RequestBody List<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds))
            return error("角色ID不能为空");
        return ok().property("roles", service.get(roleIds));
    }

    @ApiOperation(value = "角色添加功能")
    @PostMapping("/add")
    public Result save(@RequestBody MenuRole menuRole) {
        if (null == menuRole || StringUtils.isEmpty(menuRole.getRoleId())) {
            return error("角色信息为空");
        }
        service.insert(menuRole);
        return ok();
    }

    @ApiOperation(value = "批量添加功能")
    @PostMapping(value = "/batch")
    public Result batch(@RequestBody List<MenuRole> menuRoles) {
        if (CollectionUtils.isEmpty(menuRoles))
            return error("角色信息为空");
        return ok().property("roles", service.batchInsert(menuRoles));
    }

    @ApiOperation(value = "更新角色功能")
    @PostMapping(value = "/update")
    public Result update(@RequestBody MenuRole menuRole) {
        if (menuRole == null || StringUtils.isEmpty(menuRole.getId()))
            return error("角色ID为空");
        return ok().property("role", service.update(menuRole));
    }

    @ApiOperation(value = "删除角色对应功能")
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody List<String> roles) {
        if (CollectionUtil.isEmpty(roles))
            return error("角色ID为空");
        service.delete(roles);
        return ok();
    }
}