package com.royarn.mini.controller;

import com.royarn.mini.config.IPConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 10:24
 */
@RestController
@RequestMapping("/app")
public class HostInfo {

    @Resource
    private IPConfig ipConfig;

    @ApiOperation(value = "IP信息")
    @GetMapping("/ip")
    public Map getIPInfo() {
        Map retMap = new HashMap<String, String>();
        retMap.put(ipConfig.getHost(), ipConfig.getPort());
        return retMap;
    }
}
