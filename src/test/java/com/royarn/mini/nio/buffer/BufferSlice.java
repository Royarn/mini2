package com.royarn.mini.nio.buffer;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class BufferSlice {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        /**
         * write pattern
         */
        for (int i=0;i<buffer.capacity();i++) {
            buffer.put((byte) i);
        }
        printBuffer(buffer);

        buffer.position(3).limit(7);
        printBuffer(buffer);

        /**
         * this means you can get the buffer reference
         * shallow copy
         * and you can put data
         */
        ByteBuffer sliceBuffer = buffer.slice();
        for (int i=0;i<sliceBuffer.capacity();i++) {
            byte b = sliceBuffer.get();
            b *= 11;
            sliceBuffer.put(i, b);
        }
        printBuffer(sliceBuffer);
    }

    private static void printBuffer(ByteBuffer buffer) {
        System.out.println("[position=" + buffer.position()
        + ", limit=" + buffer.limit()
        + ", capacity=" + buffer.capacity()
        + ", buffer=" + buffer.toString());
    }
}
