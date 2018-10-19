package com.royarn.mini.server4web;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class MvcMapping {

    private String url;
    private String clazz;

    public MvcMapping(String url, String clazz) {
        this.url = url;
        this.clazz = clazz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
