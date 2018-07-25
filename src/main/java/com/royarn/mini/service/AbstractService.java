package com.royarn.mini.service;

import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.Camera;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 14:54
 */
public class AbstractService<T> {

    @Resource
    private MongoConfig config;
}
