using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: tree, inorder traversal
 */
namespace leetcode
{
    public class Lc099RecoverBinarySearchTree
    {
        public void RecoverTree(TreeNode root)
        {
            TreeNode first = null, second = null, prev = null;
            var stack = new Stack<TreeNode>();
            var curr = root;
            while (curr != null || stack.Count > 0)
            {
                while (curr != null)
                {
                    stack.Push(curr);
                    curr = curr.left;
                }
                curr = stack.Pop();

                if (prev != null && prev.val > curr.val)
                {
                    if (first == null) first = prev;
                    second = curr;
                }

                prev = curr;
                curr = curr.right;
            }

            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }


        public void Test()
        {
            var root = new TreeNode(1)
            {
                left = new TreeNode(3)
                {
                    right = new TreeNode(2)
                }
            };
            var exp = new TreeNode(3)
            {
                left = new TreeNode(1)
                {
                    right = new TreeNode(2)
                }
            };
            RecoverTree(root);
            Console.WriteLine(exp.Equals(root));

            root = new TreeNode(3)
            {
                left = new TreeNode(1),
                right = new TreeNode(4)
                {
                    left = new TreeNode(2)
                }
            };
            exp = new TreeNode(2)
            {
                left = new TreeNode(1),
                right = new TreeNode(4)
                {
                    left = new TreeNode(3)
                }
            };
            RecoverTree(root);
            Console.WriteLine(exp.Equals(root));
        }
    }
}

