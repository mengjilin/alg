using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: bfs
 */
namespace leetcode
{
    public class Lc105ConstructBinaryTreefromPreorderandInorderraversal
    {
        public TreeNode buildTree(int[] preorder, int[] inorder)
        {
            return buildTreeRc(preorder, 0, inorder, 0, preorder.Length);
        }

        TreeNode buildTreeRc(int[] preorder, int preStart, int[] inorder, int inStart, int len)
        {
            if (len <= 0) return null;
            int inRoot = inStart;
            while (inorder[inRoot] != preorder[preStart]) inRoot++; // can improve by using a map
            var root = new TreeNode(preorder[preStart]);
            root.left = buildTreeRc(preorder, preStart + 1, inorder, inStart, inRoot - inStart);
            root.right = buildTreeRc(preorder, preStart + 1 + inRoot - inStart, inorder, inRoot + 1, len - inRoot + inStart - 1);
            return root;
        }


        public void Test()
        {
            var preorder = new int[] { 3, 9, 20, 15, 7 };
            var inorder = new int[] { 9, 3, 15, 20, 7 };
            var exp = new TreeNode(3)
            {
                left = new TreeNode(9),
                right = new TreeNode(20)
                {
                    left = new TreeNode(15),
                    right = new TreeNode(7)
                }
            };
            Console.WriteLine(exp.Equals(buildTree(preorder, inorder)));
        }
    }
}

