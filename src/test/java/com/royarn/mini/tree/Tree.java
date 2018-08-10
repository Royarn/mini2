package com.royarn.mini.tree;

import java.util.*;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/9 17:06
 */
public class Tree implements ITree {

    private Map<String, TreeNode> treeNodeMap = new HashMap<>();
    private List<TreeNode> treeNodeList = new ArrayList<>();

    public Tree(List<ITreeNode> list) {
        initTreeNodeMap(list);
    }

    private void initTreeNodeMap(List<ITreeNode> list) {
        TreeNode treeNode = null;
        for (ITreeNode item : list) {
            treeNode = new TreeNode(item);
            treeNodeMap.put(item.getNodeId(), treeNode);
        }

        Iterator<TreeNode> iterator = treeNodeMap.values().iterator();
        TreeNode parentTreeNode = null;
        while (iterator.hasNext()) {
            treeNode = iterator.next();
            if (treeNode.getParentNodeId() == null || treeNode.getParentNodeId() == "") {
                continue;
            }
            parentTreeNode = treeNodeMap.get(treeNode.getParentNodeId());
            if (parentTreeNode != null) {
                treeNode.setParent(parentTreeNode);
                parentTreeNode.addChild(treeNode);
            }
        }
    }

    @Override
    public List<TreeNode> getTree() {
        return treeNodeList;
    }

    @Override
    public List<TreeNode> getRoot() {
        List<TreeNode> rootList = new ArrayList<>();
        if (treeNodeList.size() > 0) {
            for (TreeNode treeNode : treeNodeList) {
                if (treeNode.getParent() == null) {
                    rootList.add(treeNode);
                }
            }
        }
        return rootList;
    }

    @Override
    public TreeNode getTreeNode(String nodeId) {
        return treeNodeMap.get(nodeId);
    }
}
