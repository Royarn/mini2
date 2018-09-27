package com.royarn.mini.java8;

import com.royarn.mini.util.Collectionutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class IOperation {

    public static void main(String[] args) throws IOException {
        /**IOperation.processFile();
        System.out.println(System.getProperty("user.dir"));
        IOperation.processWithLamda((BufferedReader reader) ->
        {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        });*/
        IOperation.processNumber(Arrays.asList(1, 2, 4, 5, 6), (Integer i) -> System.out.println(i));
    }

    /**
     * jdk1.7新增try()语句来避免自己关闭资源
     * 文件读操作
     * @throws IOException
     */
    public static void processFile() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/royarn/project/mini2/src/main/resources/logback.xml"))){
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        }
    }

    /**
     * lamda简化函数实现
     * @param param
     * @throws IOException
     */
    public static void processWithLamda(MethodParam param) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/royarn/project/mini2/src/main/resources/logback.xml"))) {
            param.processFile(reader);
        }
    }

    /**
     * jdk 1.8 native lamda function
     * @param list
     * @param consumer
     * @param <T>
     */
    public static <T> void processNumber(List<T> list, Consumer<T> consumer) {
        if (Collectionutil.isEmpty(list)) return;
        for (T t : list) {
            consumer.accept(t);
        }
    }
}

@FunctionalInterface
interface MethodParam {
    void processFile(BufferedReader reader) throws IOException;
}