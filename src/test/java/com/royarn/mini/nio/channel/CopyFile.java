package com.royarn.mini.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class CopyFile {

    static public void main(String[] args) throws Exception {
        String inFile = "src/main/resources/CopyFile.java";
        String outFile = "src/main/resources/CopyFile.java.copy";

        //从流中获取管道
        FileInputStream inputStream = new FileInputStream(inFile);
        FileOutputStream outputStream = new FileOutputStream(outFile);

        //获取管道
        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();

        //分配堆缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            //读入之前要清空
            buffer.clear();

            //从管道中读取
            int r = inChannel.read(buffer);

            if (r == -1) { break;}

            //切换写模式
            buffer.flip();
            outChannel.write(buffer);
        }
    }
}
