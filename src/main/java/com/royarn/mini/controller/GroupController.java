package com.royarn.mini.controller;

import com.google.gson.Gson;
import com.royarn.mini.config.Result;
import com.royarn.mini.entity.Group;
import com.royarn.mini.service.GroupService;
import com.royarn.mini.support.BusinessException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.List;

import static com.royarn.mini.config.Result.*;
import static com.royarn.mini.util.PageUtils.getOffset;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */

@RestController
@RequestMapping("/npconfig/v1")
public class GroupController {

    @Resource
    private GroupService service;

    @ApiOperation("添加分组")
    @PostMapping("group")
    public Result add(@RequestBody String body) {
        Group group = new Gson().fromJson(body, Group.class);
        service.addOne(group);
        return ok().property("group", group);
    }

    @ApiOperation("删除分组")
    @DeleteMapping("/group/{id}")
    public Result delete(@PathVariable String id) {
        service.delete(id);
        return ok().property("group", "");
    }

    @ApiOperation("修改分组")
    @PostMapping("/group/{id}")
    public Result update(@PathVariable String id,@RequestBody String body) {
        Group group = new Gson().fromJson(body, Group.class);
        group.setId(id);
        service.update(group);
        return ok().property("group", group);
    }

    @ApiOperation("查询所有分组")
    @GetMapping("/groups")
    public Result list(HttpServletRequest request) {
        List<Group> allGroups= Collections.emptyList();
        String pageSize = request.getParameter("pageSize");
        //查询全量数据
        if(pageSize==null) {
            return ok().property("list", service.getAllGroups());
        }
        //分页查询
        String currentPage=request.getParameter("currentPage");
        try {
            int offset = getOffset(currentPage,pageSize);

            if(offset>=0)
                allGroups= service.list(offset,Integer.parseInt(pageSize));
            return ok().property("currentPage",currentPage)
                    .property("pageSize",pageSize)
                    .property("totalSize",service.count())
                    .property("list", allGroups);
        } catch (NumberFormatException e) {
            throw new BusinessException("格式转换异常！");
        }
    }

    @ApiOperation("查询分组信息")
    @GetMapping("/group/{id}")
    public Result get(@PathVariable String id) {
        return ok().property("data", service.selectOne(id));
    }

    @ApiOperation("获取子分组")
    @GetMapping("/groups/{id}")
    public Result getChildren(@PathVariable String id,HttpServletRequest request) {
        List<Group> allGroups = null;
        String pageSize = request.getParameter("pageSize");
        if(pageSize==null) {
            allGroups = service.qryChildGroups(id);
            return ok().property("list", allGroups);
        } else {
            String currentPage=request.getParameter("currentPage");
            try {
                int offset = getOffset(currentPage,pageSize);
                String search = request.getParameter("search");
                if(offset>=0)
                    allGroups = service.qryChildGroupsByPage(offset,Integer.parseInt(pageSize),id);
                return ok().property("currentPage",currentPage)
                        .property("pageSize",pageSize)
                        .property("totalSize",service.getChildGroupCount(id))
                        .property("list", allGroups);
            } catch (NumberFormatException e) {
                throw new BusinessException("参数格式错误！");
            }
        }
    }

    @ApiOperation("查询分组下的摄像机以及权限")
    @GetMapping("/auth/{id}/{userId}")
    public Result getCameras(@PathVariable String id,
                             @PathVariable String userId) {
        return ok().property("list", service.getCameras(id, userId));
    }
}