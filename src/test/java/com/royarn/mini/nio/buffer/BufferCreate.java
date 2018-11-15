package com.royarn.mini.nio.buffer;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class BufferCreate {

    public static void main(String[] args) {
        /**
         * java NIO
         *  对linux 多路复用I/O的封装，提高网络流量的读写性能
         *    NIO三大核心组件
         *      Buffer : 将网络流中的数据存储下来--发散读/数据发送网络流--聚集写
         *      Channel : 管道
         *      Selector : 多路复用器
         */

        /**
         * 堆内存缓冲区
         */
        ByteBuffer heapByteBuffer = ByteBuffer.allocate(10);
        if (heapByteBuffer.hasArray()) {
            System.out.println("heapByteBuffer array : " + heapByteBuffer.array());
            System.out.println("heapByteBuffer array offset : " + heapByteBuffer.arrayOffset());
        }
        System.out.println("heapByteBuffer capacity : " + heapByteBuffer.capacity());
        System.out.println("heapByteBuffer limit : " + heapByteBuffer.limit());
        System.out.println("heapByteBuffer position : " + heapByteBuffer.position());
        System.out.println("heapByteBuffer remaining : " + heapByteBuffer.remaining());
        System.out.println();

        /**
         * 用户态缓冲区
         */
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(10);
        if (directByteBuffer.hasArray()) {
            System.out.println("directByteBuffer array : " + directByteBuffer.array());
            System.out.println("directByteBuffer array offset : " + directByteBuffer.arrayOffset());
        }
        System.out.println("directByteBuffer capacity : " + directByteBuffer.capacity());
        System.out.println("directByteBuffer limit : " + directByteBuffer.limit());
        System.out.println("directByteBuffer position : " +directByteBuffer.position());
        System.out.println("directByteBuffer remaining : " + directByteBuffer.remaining());
        System.out.println();

        /**
         * 堆内缓冲区
         */
        ByteBuffer wrapByteBuffer = ByteBuffer.wrap(new byte[10], 2, 3);
        if (wrapByteBuffer.hasArray()) {
            System.out.println("wrapByteBuffer array : " + wrapByteBuffer.array());
            System.out.println("wrapByteBuffer array offset : " + wrapByteBuffer.arrayOffset());
        }
        System.out.println("wrapByteBuffer capacity : " + wrapByteBuffer.capacity());
        System.out.println("wrapByteBuffer limit : " + wrapByteBuffer.limit());
        System.out.println("wrapByteBuffer position : " + wrapByteBuffer.position());
        System.out.println("wrapByteBuffer remaining : " + wrapByteBuffer.remaining());
    }
}
