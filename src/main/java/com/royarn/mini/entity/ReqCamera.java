package com.royarn.mini.entity;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-25
 */
public class ReqCamera {

    private String addSelf;
    private String delSelf;
    private List<String> addCameras;
    private List<String> delCameras;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddSelf() {
        return addSelf;
    }

    public void setAddSelf(String addSelf) {
        this.addSelf = addSelf;
    }

    public String getDelSelf() {
        return delSelf;
    }

    public void setDelSelf(String delSelf) {
        this.delSelf = delSelf;
    }

    public List<String> getAddCameras() {
        return addCameras;
    }

    public void setAddCameras(List<String> addCameras) {
        this.addCameras = addCameras;
    }

    public List<String> getDelCameras() {
        return delCameras;
    }

    public void setDelCameras(List<String> delCameras) {
        this.delCameras = delCameras;
    }
}
