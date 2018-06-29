package com.royarn.mini.controller;

import com.royarn.mini.entity.LocalUser;
import com.royarn.mini.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 16:52
 */
@RestController
@RequestMapping("/app")
public class UserController {

    @Resource
    private UserService service;

    @ApiOperation(value = "查询本地用户")
    @GetMapping("/list1")
    public List<LocalUser> list() {
        List<LocalUser> userList = service.findAll();
        return userList;
    }

    @ApiOperation(value = "保存本地用户")
    @PostMapping("/save1")
    public String save(@RequestBody LocalUser user) {
        if (user == null) {
            return "ERROR";
        }
        service.insertUser(user);
        return "SUCCESS";
    }
}
