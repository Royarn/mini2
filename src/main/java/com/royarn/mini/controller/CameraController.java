package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.service.CameraService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.PageUtils;
import com.royarn.mini.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static com.royarn.mini.config.Result.ok;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 11:40
 */
@RestController
@RequestMapping("/npconfig/v1")
public class CameraController {

    @Resource
    private CameraService service;

    @ApiOperation("查询摄像机")
    @GetMapping("/camera/{id:.+}")
    public Result get(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return Result.ok().property("object", service.selectOne(id));
    }

    @ApiOperation("新增摄像机")
    @PostMapping("/camera/save")
    public Result save(@RequestBody Camera camera) {
        if (camera == null) {
            return Result.error("数据不能为空！");
        }
        service.addOne(camera);
        return Result.ok();
    }

    @ApiOperation("修改摄像机")
    @PostMapping("/camera/{id:.+}")
    public Result update(@RequestBody Camera camera) {
        if (camera == null || camera.getId() == null) {
            return Result.error("Id不能为空！");
        }
        service.update(camera);
        return Result.ok();
    }

    @ApiOperation("删除摄像机")
    @PostMapping("/cameras/delete")
    public Result delete(@RequestBody List<String> ids) {
        if (ids == null) {
            return Result.error("ID不能为空！");
        }
        return Result.ok().property("ids", service.delete(ids));
    }

    @ApiOperation("摄像机列表")
    @GetMapping(value = "/cameras/{id}")
    public Result queryDevicesOfGroup(@PathVariable String id,
                                      @RequestParam("pageSize")  String pageSize,
                                      @RequestParam("currentPage")String currentPage,
                                      @RequestParam(value = "search", required = false)  String search,
                                      @RequestParam(value = "isRecursion", defaultValue = "false") boolean isRecursion){
        List<?> deviceVos= Collections.emptyList();
        if (currentPage == null)
            throw new BusinessException("格式转换错误！");
        try {
            int offset = PageUtils.getOffset(currentPage, pageSize);
            if(offset>=0)
                deviceVos= service.qryCameraOfGroupByPage(offset,Integer.parseInt(pageSize),id, search);
            return ok().property("currentPage",currentPage)
                    .property("pageSize",pageSize)
                    .property("totalSize",service.qryCameraOfGroupCount(id,search))
                    .property("list", deviceVos);
        } catch (NumberFormatException e) {
            throw new BusinessException("格式转换错误！");
        }
    }
}
