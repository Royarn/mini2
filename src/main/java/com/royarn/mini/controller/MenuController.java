package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.Menu;
import com.royarn.mini.service.MenuService;
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
@RequestMapping("/app/menu")
public class MenuController {

    @Resource
    private MenuService service;

    @ApiOperation(value = "功能列表")
    @GetMapping("/list")
    public Result list() {
        List<Menu> menuList = service.list();
        return ok().property("menus", menuList);
    }

    @ApiOperation(value = "查询功能")
    @PostMapping(value = "/get")
    public Result get(@RequestBody List<String> ids) {
        if (CollectionUtil.isEmpty(ids))
            return error("功能标识不能为空");
        return ok().property("menus", service.get(ids));
    }

    @ApiOperation(value = "添加功能")
    @PostMapping("/add")
    public Result save(@RequestBody Menu menu) {
        if (menu == null) {
            return error("功能信息为空");
        }
        service.insert(menu);
        return ok();
    }

    @ApiOperation(value = "批量添加功能")
    @PostMapping(value = "/batch")
    public Result batch(@RequestBody List<Menu> menuList) {
        if (CollectionUtils.isEmpty(menuList))
            return error("功能信息为空");
        return ok().property("menus", service.batchInsert(menuList));
    }

    @ApiOperation(value = "更新功能")
    @PostMapping(value = "/update")
    public Result update(@RequestBody Menu menu) {
        if (menu == null || StringUtils.isEmpty(menu.getId()))
            return error("功能ID为空");
        return ok().property("menu", service.update(menu));
    }

    @ApiOperation(value = "删除功能")
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return error("菜单ID为空");
        }
        service.delete(ids);
        return ok();
    }
}