package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.LocalUser;
import com.royarn.mini.service.UserService;
import com.royarn.mini.util.CollectionUtil;
import com.royarn.mini.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import static com.royarn.mini.config.Result.*;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 16:52
 */
@RestController
@RequestMapping("/app/local")
public class UserController {

    @Resource
    private UserService service;

    @ApiOperation(value = "查询本地用户")
    @GetMapping("/list")
    public Result list() {
        List<LocalUser> userList = service.list();
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
    public Result save(@RequestBody LocalUser user) {
        if (user == null) {
            return error("用户信息为空");
        }
        service.insert(user);
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
