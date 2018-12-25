package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.service.PVMUserService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.PageUtils;
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
 * @date 2018-12-24
 */
@RestController
@RequestMapping("/npauth")
public class PVMUserController {

    @Resource
    private PVMUserService service;

    @ApiOperation("添加用户")
    @PostMapping("/{id}/v1/config/user")
    public Result addOne(@PathVariable String id, @RequestBody String body) {
        return Result.ok().property("user", service.addOne(body));
    }

    @ApiOperation("更新用户")
    @PutMapping("/{groupId}/v1/user")
    public Result update(@PathVariable String groupId, @RequestBody String body) {
        return Result.ok().property("user", service.update(groupId, body));
    }

    @ApiOperation("删除用户")
    @PostMapping("/{groupId}/v1/delete/users")
    public Result delete(@RequestBody List<String> ids) {
        return Result.ok().property("ids", service.delete(ids));
    }

    @ApiOperation("用户详情")
    @GetMapping("/v1/user/{id}")
    public Result get(@PathVariable String id) {
        return Result.ok().property("object", service.selectOne(id));
    }

    @ApiOperation("用户列表")
    @GetMapping(value = "/v1/recursion/organization/users/{id}")
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
                deviceVos= service.qryUsersOfGroupByPage(offset,Integer.parseInt(pageSize),id, search);
            return ok().property("currentPage",currentPage)
                    .property("pageSize",pageSize)
                    .property("totalSize",service.qryUsersOfGroupCount(id,search))
                    .property("list", deviceVos);
        } catch (NumberFormatException e) {
            throw new BusinessException("格式转换错误！");
        }
    }
}