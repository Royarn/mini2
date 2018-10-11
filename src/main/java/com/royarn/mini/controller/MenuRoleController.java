package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.MenuRole;
import com.royarn.mini.service.MenuRoleService;
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

    @ApiOperation(value = "查询指定用户")
    @PostMapping(value = "/get")
    public Result get(@RequestBody List<String> ids) {
        if (CollectionUtils.isEmpty(ids))
            return error("用户标识不能为空");
        return ok().property("users", service.get(ids));
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    public Result save(@RequestBody MenuRole menuRole) {
        if (menuRole == null) {
            return error("用户信息为空");
        }
        service.insert(menuRole);
        return ok();
    }

    @ApiOperation(value = "批量添加用户")
    @PostMapping(value = "/batch")
    public Result batch(@RequestBody List<LocalUser> users) {
        if (CollectionUtils.isEmpty(users))
            return error("用户信息为空");
        return ok().property("users", service.batchInsert(users));
    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping(value = "/update")
    public Result update(@RequestBody LocalUser user) {
        if (user == null || StringUtils.isEmpty(user.getId()))
            return error("用户ID为空");
        return ok().property("user", service.update(user));
    }

    @ApiOperation(value = "删除用户")
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody List<String> ids) {
        if (CollectionUtil.isEmpty(ids))
            return error("用户ID为空");
        service.delete(ids);
        return ok();
    }
}
