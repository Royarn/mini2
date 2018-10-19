package com.royarn.mini.server4web;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class HelloServlet extends Servlet {
    @Override
    public void doGet(Request request, Response response) {
        response.write("get hello response ... ");
    }

    @Override
    public void doPost(Request request, Response response) {
        response.write("post hello response ... ");
    }
}
