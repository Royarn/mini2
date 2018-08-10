package com.royarn.mini.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/9 16:53
 */
public class TreeNode {

    private String nodeId;
    private String nodeName;
    private String parentNodeId;
    private int level;
    private int orderNum;
    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();
    private List<TreeNode> allChildren = new ArrayList<>();

    public TreeNode(ITreeNode treeNode) {
        nodeId = treeNode.getNodeId();
        nodeName = treeNode.getNodeName();
        parentNodeId = treeNode.getNodeParentId();
        orderNum = treeNode.getOrderNum();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public List<TreeNode> getAllChildren() {
        if (allChildren.isEmpty()) {
            for(TreeNode treeNode : children){
                allChildren.add(treeNode);
                allChildren.addAll(treeNode.getAllChildren());
            }
        }
        return allChildren;
    }

    public void addChild(TreeNode treeNode) {
        children.add(treeNode);
    }

    public void removeChild(TreeNode treeNode) {
        children.remove(treeNode);
    }
}