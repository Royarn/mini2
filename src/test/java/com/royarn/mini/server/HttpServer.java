package com.royarn.mini.server;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-14
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        /*ServerSocket socket = new ServerSocket(80);
        Socket socket1 = socket.accept();
        hadlerRequest(socket1);*/
        int a = 5, b=6, c=7;
        a = b = c;
        System.out.println(b);
    }

    private static void hadlerRequest(Socket socket) {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            byte[] bytes = new byte[2048];
            inputStream.read(bytes);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
