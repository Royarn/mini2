package com.royarn.mini.controller;

import com.royarn.mini.entity.TUser;
import com.royarn.mini.service.TUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 10:18
 */
@RestController
@RequestMapping("/app")
public class TUserController {

    @Resource
    private TUserService userService;

    @ApiOperation(value = "查询远程用户")
    @GetMapping("/list")
    public List<TUser> list() {
        List<TUser> userList = userService.findAll();
       return userList;
    }

    @ApiOperation(value = "保存远程用户")
    @PostMapping("/save")
    public String save(@RequestBody TUser tUser) {
        if (tUser == null) {
            return "ERROR";
        }
        userService.insertUser(tUser);
        return "SUCCESS";
    }
}