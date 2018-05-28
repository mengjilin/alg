using System;
using System.Collections.Generic;
using System.Text;

namespace leetcode
{
    public class TreeNode
    {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }

        public override bool Equals(object obj)
        {
            var that = obj as TreeNode;
            if (that == null) return false;
            return val == that.val &&
                (left == null && that.left == null || left != null && left.Equals(that.left)) &&
                (right == null && that.right == null || right != null && right.Equals(that.right));
        }
    }

}

