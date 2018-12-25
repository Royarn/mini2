package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import com.royarn.mini.entity.Server;
import com.royarn.mini.service.ServerService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.List;

import static com.royarn.mini.config.Result.*;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */
@RestController
@RequestMapping("/npconfig/v1")
public class ServerController {

    @Resource
    private ServerService service;

    @GetMapping(value = "/servers")
    public Result getAll(@RequestParam(value = "search", required = false)  String search,
                             @RequestParam(value = "sort[]", required = false) List<String> sort,
                             @RequestParam(value = "type[]", required = false) List<String> types,
                             HttpServletRequest request){
        List<Server> servers = Collections.emptyList();
        String pageSize = request.getParameter("pageSize");
        if(pageSize == null) {
            //根据类型查询
            servers = service.qryServers(null, null, search, types);
            //return ok().list(servers).json();
            return ok();
        }else {
            String currentPage=request.getParameter("currentPage");
            try {
                //偏移量
                int offset = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(pageSize);
                if(offset>=0)
                    servers= service.qryServers(offset, Integer.parseInt(pageSize), search, types);
                return ok().property("currentPage",currentPage)
                        .property("pageSize",pageSize);
                       // .property("totalSize",service.count(search, types))
                        //.list(servers).json();
            } catch (NumberFormatException e) {
                throw new BusinessException("500");
            }
        }
    }

    @ApiOperation("配置服务")
    @PostMapping("/one")
    public Result save(@RequestBody Server server) {
        if (StringUtils.isEmpty(server.getHost())) {
            return error("服务IP不能为空！");
        }
        service.addOne(server);
        return ok();
    }

    @ApiOperation("批量配置服务")
    @PostMapping("/server")
    public Result batch(@RequestBody String body) {
        if (null == body) {
            return error("数据为空！");
        }
        return ok().property("data", service.batch(body));
    }

    @ApiOperation("统计结果")
    @GetMapping("/count")
    public Result count(@RequestParam(value = "search", required = false)  String search,
                        @RequestParam(value = "type[]", required = false) List<String> types) {
        return ok().property("count", service.count(search, types));
    }
}
