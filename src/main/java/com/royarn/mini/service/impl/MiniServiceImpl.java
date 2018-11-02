package com.royarn.mini.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.royarn.api.intf.MiniService;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-02
 */
@Service
public class MiniServiceImpl implements MiniService {
    @Override
    public String sayHello(String name) {
        return "This is a message form Mini";
    }
}
