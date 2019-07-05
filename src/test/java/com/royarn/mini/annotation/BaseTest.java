package com.royarn.mini.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 15:55
 */
public class BaseTest {

    static class User {

        private Integer id;
        private String name;
        private String remark;

        public User() {}

        public User(Integer id, String name, String remark) {
            this.id = id;
            this.name = name;
            this.remark = remark;
        }

        @UserTag(methodName = "addUser", description = "添加用户")
        public void add() {}

        @UserTag(methodName = "updateUser", description = "更新用户")
        public void update() {}

        @UserTag(methodName = "qryUser", description = "查询用户列表")
        public void select() {}

        @UserTag(methodName = "deleteUser", description = "删除用户")
        public void delete() {}

        @Override
        public String toString() {
            return "id : " + id + " " + " name : " + name + " " + " remark : " + remark;
        }
    }

    public static void main(String[] args) {
        User user = new User(1, "royarn", "763094810@qq.com");
        String userStr = Serieisable.parseObj2Str(user);
        System.out.println(userStr);
        User user1 = (User) Serieisable.parseStr2Obj(userStr);
        System.out.println(user1);
        for (Method method : User.class.getDeclaredMethods()) {
            UserTag userTag = method.getAnnotation(UserTag.class);
            for (Annotation annotation : method.getAnnotations()) {
                System.out.println(annotation.toString());
            }
            if (null != userTag) {
                System.out.println(userTag.methodName() + "#############" + userTag.description());
            }
        }
    }
}
