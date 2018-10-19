package com.royarn.mini.server4web;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class Response {

    private OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    /**
     * 向客户端回写响应数据
     * @param data
     */
    public void write(String data) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(data)
                .append("</body></html>");
        try {
            output.write(buffer.toString().getBytes());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
