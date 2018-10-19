package com.royarn.mini.server4web;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public abstract class Servlet {

    public abstract void doGet(Request request, Response response);

    public abstract void doPost(Request request, Response response);

    /**
     * 采用模板模式  --指定交互规则，具体处理细节由子类扩展
     * @param request
     * @param response
     */
    public void service(Request request, Response response) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            doPost(request, response);
        }
    }
}
