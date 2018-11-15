package com.royarn.mini.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class BufferAccess {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        printBuffer(buffer);
        /**
         * write pattern
         */
        buffer.put((byte) 'H').put((byte) 'E').put((byte) 'L').put((byte) 'L').put((byte) 'O');
        printBuffer(buffer);

        /**
         * transfer read pattern
         */
        buffer.flip();
        printBuffer(buffer);

        /**
         * read two bytes
         */
        System.out.println("" + (char) buffer.get() + (char)buffer.get());
        printBuffer(buffer);

        /**
         * mark it and read two bytes
         */
        buffer.mark();
        System.out.println("" + (char) buffer.get() + (char)buffer.get());
        printBuffer(buffer);

        /**
         * reset mark
         */
         buffer.reset();
         System.out.println("" + (char) buffer.get() + (char)buffer.get());
        printBuffer(buffer);

        /**
         * compact means we transfer to write and read seems not end
         * and this will be put the data to head and offset the number and you can write
         */
        buffer.compact();
        printBuffer(buffer);

        /**
         * clear buffer and return to initial
         */
        buffer.clear();
        printBuffer(buffer);
    }

    private static void printBuffer(Buffer buffer) {
        System.out.println("[limit=" + buffer.limit()
        + ", position=" + buffer.position()
        + ", capacity=" + buffer.capacity()
        + ", array=" + buffer.toString() + "]");
    }
}
