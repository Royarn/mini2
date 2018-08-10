package com.royarn.mini.seriable;

import com.alibaba.fastjson.JSON;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/3 13:59
 */
public class SimpleMapperDemo {

    public static void main(String[] args) {
        User user = new User(1, "royarn", "lizhiqiang@netposa.com");
        //String userStr = SimpleMapper.toString(user);
        String userStr = Serializable.serializable(user);
        System.out.println("seriable user : " + userStr);
        //User user1 = (User) SimpleMapper.fromString(userStr);
        User user1 = (User) Serializable.desrializable(userStr);
        System.out.println(JSON.toJSON(user1));
    }

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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
