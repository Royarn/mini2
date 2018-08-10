package com.royarn.mini.tree;

import java.util.List;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/9 17:04
 */
public interface ITree {
    List<TreeNode> getTree();
    List<TreeNode> getRoot();
    TreeNode getTreeNode(String nodeId);
}
