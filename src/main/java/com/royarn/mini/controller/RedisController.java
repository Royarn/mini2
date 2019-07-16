package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.service.RedisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.royarn.mini.config.Result.ok;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-16
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisService service;

    @ApiOperation("插入字符串")
    @PostMapping("/string/set")
    public Result setString(@RequestParam String key,
                            @RequestParam String value) {
        service.setString(key, value);
        return ok();
    }

    @ApiOperation("查询字符串")
    @PostMapping("/string/get")
    public Result setString(@RequestParam String key) {
        return ok().property(key, service.getString(key));
    }
}
