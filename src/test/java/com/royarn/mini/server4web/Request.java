package com.royarn.mini.server4web;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class Request {

    private String url;
    private String method;

    /**
     * Request 对象装载来自客户端发送的网络流数据
     * @param input
     */
    public Request(InputStream input) throws IOException {

        String reqStr = "";
        byte[] reqStrBytes = new byte[1024];
        int length = 0;
        //读取客户端流数据
        if ((length = input.read(reqStrBytes)) > 0) {
            reqStr = new String(reqStrBytes, 0, length);
        }

        //获取头信息
        String reqHead = reqStr.split("\n")[0];
        //请求url
        url = reqHead.split("\\s")[1];
        //请求类型
        method = reqHead.split("\\s")[0];

        System.out.println("request : {url: " + url + ", method: " + method + "}");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}