package com.royarn.mini.annotation;

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
    }
}
