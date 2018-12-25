package com.royarn.mini.controller;

import com.google.gson.JsonObject;
import com.royarn.mini.config.Result;
import com.royarn.mini.service.DeviceService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static com.royarn.mini.config.Result.ok;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */

@RestController
@RequestMapping("/npconfig/v1")
public class DeviceController {

    @Resource
    private DeviceService service;

    @ApiOperation("添加设备")
    @PostMapping("/device/encoder")
    public Result addOne(@RequestBody String body) {
        String id = service.addOne(body).getId();
        return ok().property("data", MessageUtil.MSG(id));
    }

    @ApiOperation("编辑设备")
    @PostMapping("/device/{id}")
    public Result update(@PathVariable String id,@RequestBody String body) {
        service.update(id,body);
        return ok();
    }

    @ApiOperation("删除设备")
    @PostMapping("/devices/delete")
    public Result delete(@RequestBody List<String> ids) {
        service.delete(ids);
        return ok();
    }

    @ApiOperation("设备详情")
    @GetMapping("/device/encoder/{id}")
    public Result get(@PathVariable String id) {
        return ok().property("data", service.selectOne(id));
    }

    @ApiOperation("查询分组的设备")
    @GetMapping(value = "/{deviceType}/{id}")
    public Result queryDevicesOfGroup(@PathVariable String deviceType,
                                          @PathVariable String id,
                                          @RequestParam("pageSize")  String pageSize,
                                          @RequestParam("currentPage")String currentPage,
                                          @RequestParam(value = "search", required = false)  String search,
                                          @RequestParam(value = "isRecursion", defaultValue = "false") boolean isRecursion){
        List<?> deviceVos= Collections.emptyList();
        if (currentPage == null)
            throw new BusinessException("格式转换错误！");
        try {
            int offset = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(pageSize);
            if(offset>=0)
                deviceVos= service.getDevicesOfGroupByPage(offset,Integer.parseInt(pageSize),id, search);
            return ok().property("currentPage",currentPage)
                    .property("pageSize",pageSize)
                    .property("totalSize",service.getDeviceOfGroupCount(id,search))
                    .property("list", deviceVos);
        } catch (NumberFormatException e) {
            throw new BusinessException("格式转换错误！");
        }
    }
}
