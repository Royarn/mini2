package com.royarn.mini.tree;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/9 17:37
 */
public class Org implements ITreeNode {

    private String uuid;
    private String parentId;
    private String name;
    private Integer orderNum;
    private String code;
    private String type;

    public Org(){

    }
    public Org(String uuid, String parentId, String name, Integer orderNum, String code, String type){
        this.uuid = uuid;
        this.parentId = parentId;
        this.name = name;
        this.orderNum = orderNum;
        this.code = code;
        this.type = type;
    }

    @Override
    public String getNodeId() {
        return uuid;
    }

    @Override
    public String getNodeName() {
        return name;
    }

    @Override
    public String getNodeParentId() {
        return parentId;
    }

    @Override
    public Integer getOrderNum() {
        return orderNum;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
