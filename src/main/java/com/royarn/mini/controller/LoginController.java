package com.royarn.mini.controller;

import com.royarn.mini.config.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-24
 */
@RestController
@RequestMapping("/api")
public class LoginController {


    @ApiOperation("授权信息")
    @GetMapping("/GetLicenseInfo")
    public Result login(@RequestBody String body, HttpServletRequest request) {
        return null;
    }
}
