package com.royarn.mini.seriable;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 13:42
 */
public class SimpleFormatter {

    public static String format(Object object) {

        try {
            Class<?> clazz = object.getClass();
            StringBuilder builder = new StringBuilder();
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Label label = field.getAnnotation(Label.class);
                String name = label.value() != null ? label.value() : field.getName();
                Object value = field.get(object);
                if (value != null && field.getType() == Date.class) {
                    value = formatDate(field, value);
                }
                builder.append(name + " : " + value + "\n");
            }
            return builder.toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object formatDate(Field field, Object value) {
        Format format = field.getAnnotation(Format.class);
        if (format != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format.pattern());
            dateFormat.setTimeZone(TimeZone.getTimeZone(format.timeZone()));
            return dateFormat.format(value);
        }
        return value;
    }

    static class Student {

        @Label("姓名")
        String name;
        @Label("出生日期")
        @Format(pattern = "yyyy-MM-dd")
        Date born;
        @Label("分数")
        double score;

        public Student(String name, Date born, double score) {
            this.name = name;
            this.born = born;
            this.score = score;
        }
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Student userInfo = new Student("royarn", dateFormat.parse("1990-04-07"), 80.9d);
        System.out.println(SimpleFormatter.format(userInfo));
    }
}