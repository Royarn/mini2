package com.royarn.mini.server4web;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class HandlerMappingConfig {

    public static List<MvcMapping> mvcMappings = new ArrayList<>();

    static {
        mvcMappings.add(new MvcMapping("/hello", "com.royarn.mini.server4web.HelloServlet"));
    }
}
