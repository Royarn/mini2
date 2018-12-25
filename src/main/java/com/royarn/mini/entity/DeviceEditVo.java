package com.royarn.mini.entity;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-21
 */
public class DeviceEditVo extends Device{

    private String protocol;
    private String username;
    private String password;
    private String gbId;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGbId() {
        return gbId;
    }

    public void setGbId(String gbId) {
        this.gbId = gbId;
    }
}
