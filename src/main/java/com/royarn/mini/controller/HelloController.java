package com.royarn.mini.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.royarn.api.intf.ClassifyService;
import com.royarn.api.intf.GoodService;
import com.royarn.api.intf.HelloService;
import com.royarn.api.intf.ShopService;
import com.royarn.mini.config.Result;
import com.royarn.mini.entity.LocalUser;
import com.royarn.mini.entity.MsgInfo;
import com.royarn.mini.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private UserService userService;

    @Resource
    private MongoTemplate template;

    @ApiOperation("Dubbo测试")
    @GetMapping("/get")
    public Result get() {
        return Result.ok()
                .property("goods", goodService.list())
                .property("classifies", classifyService.list())
                .property("shops", shopService.list());
    }

    @ApiOperation("MongoDB测试")
    @GetMapping("/list")
    public Result list() {
        List<MsgInfo> msgInfos = template.find(new Query(), MsgInfo.class);
        return Result.ok().property("info", msgInfos);
    }

    @ApiOperation("测试分布式事务")
    @PostMapping("/add")
    public Result add() {
        return Result.ok().property("data", userService.insertDTS());
    }
}
