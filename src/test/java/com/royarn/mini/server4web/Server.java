package com.royarn.mini.server4web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class Server {

    private int port;

    /**
     * 存储请求映射关系
     */
    private Map<String, String> handlerMapping = new HashMap<>();

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        //加载请求映射
        initHandlerMapping();

        //读取客户端请求
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("custom server has been started ... ");
            while (true) {
                //接收客户端请求
                Socket socket = serverSocket.accept();

                //将socket的流封装到对应的请求-响应对象中
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                Request request = new Request(input);
                Response response = new Response(output);

                //请求分发
                dispatch(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initHandlerMapping() {
        for (MvcMapping mvcMapping : HandlerMappingConfig.mvcMappings) {
            handlerMapping.put(mvcMapping.getUrl(), mvcMapping.getClazz());
        }
    }

    private void dispatch(Request request, Response response) {
        //请求处理类
        String clazzName = handlerMapping.get(request.getUrl());

        try {
            Class<?> clazz = Class.forName(clazzName);
            //根据请求类提供的包路径生成实例
            Servlet servlet = (Servlet) clazz.newInstance();
            servlet.service(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(8083).start();
    }
}