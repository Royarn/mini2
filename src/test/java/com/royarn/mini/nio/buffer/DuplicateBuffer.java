package com.royarn.mini.nio.buffer;

import java.nio.CharBuffer;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class DuplicateBuffer {

    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(8);
        for (int i=0;i<buffer.capacity();i++) {
            buffer.put(String.valueOf(i).charAt(0));
        }
        printBuffer(buffer);

        /**
         * transfer pattern
         */
        buffer.flip();
        printBuffer(buffer);

        /**
         * reset position
         */
        buffer.position(3).limit(6).mark().position(5);
        CharBuffer copyBuffer = buffer.duplicate();
        buffer.clear();
        printBuffer(buffer);

        printBuffer(copyBuffer);
    }

    private static void printBuffer(CharBuffer buffer) {
        System.out.println("[position=" + buffer.position()
                + ", limit=" + buffer.limit()
                + ", capacity=" + buffer.capacity()
                + ", buffer=" + buffer.toString());
    }
}
