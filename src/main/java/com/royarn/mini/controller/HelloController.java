package com.royarn.mini.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.royarn.api.intf.ClassifyService;
import com.royarn.api.intf.GoodService;
import com.royarn.api.intf.HelloService;
import com.royarn.api.intf.ShopService;
import com.royarn.mini.config.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-01
 */

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Reference
    private HelloService service;

    @Reference
    private GoodService goodService;

    @Reference
    private ClassifyService classifyService;

    @Reference
    private ShopService shopService;

    @ApiOperation("Dubbo测试")
    @GetMapping("/get")
    public Result get() {
        return Result.ok()
                .property("goods", goodService.list())
                .property("classifies", classifyService.list())
                .property("shops", shopService.list());
    }

    public Result list() {
        return null;
    }
}
