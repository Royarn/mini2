package com.royarn.mini.java8;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo3 {

    public static void main(String[] args) {
        //read file by stream
        long uniquewords = 0;
        try(Stream<String> lines
                    = Files.lines(Paths.get("D:\\project\\gitpro\\mini2\\src\\main\\resources\\application.yml"), Charset.defaultCharset())) {
            uniquewords = lines.flatMap(line -> Arrays.stream(line.split("" )))
                    .distinct()
                    .count();
            System.out.println(uniquewords);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**unlimited stream
        Stream.iterate(0, n -> n + 2)
                .forEach(System.out::println);*/

        //Fibonacci sequence
        Stream.iterate(new int[] {0, 1}, t -> new int[] {t[1], t[0] + t[1]})
                .limit(20)
                .collect(Collectors.toList());
    }
}
