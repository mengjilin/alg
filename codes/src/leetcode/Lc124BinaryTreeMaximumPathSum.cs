using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: recursive
 */
namespace leetcode
{
    public class Lc124BinaryTreeMaximumPathSum
    {
        public int MaxPathSum(TreeNode root)
        {
            if (root == null) return 0;
            var ret = root.val;
            MaxPathSumRc(root, ref ret);
            return ret;
        }

        int MaxPathSumRc(TreeNode root, ref int max)
        {
            if (root == null) return 0;
            var left = MaxPathSumRc(root.left, ref max);
            var right = MaxPathSumRc(root.right, ref max);
            max = Math.Max(max, root.val + Math.Max(0, left) + Math.Max(0, right));
            return root.val + Math.Max(0, Math.Max(left, right)); ;
        }

        public void Test()
        {
            var root = new TreeNode(1)
            {
                left = new TreeNode(2),
                right = new TreeNode(3)
            };
            Console.WriteLine(MaxPathSum(root) == 6);

            root = new TreeNode(-10)
            {
                left = new TreeNode(9),
                right = new TreeNode(20)
                {
                    left = new TreeNode(15),
                    right = new TreeNode(7)
                }
            };
            Console.WriteLine(MaxPathSum(root) == 42);

            root = new TreeNode(1)
            {
                left = new TreeNode(-2),
                right = new TreeNode(3)
            };
            Console.WriteLine(MaxPathSum(root) == 4);

            root = new TreeNode(5)
            {
                left = new TreeNode(4)
                {
                    left = new TreeNode(11)
                    {
                        left = new TreeNode(7),
                        right = new TreeNode(2)
                    }
                },
                right = new TreeNode(8)
                {
                    left = new TreeNode(13),
                    right = new TreeNode(4)
                    {
                        right = new TreeNode(1)
                    }
                }
            };
            Console.WriteLine(MaxPathSum(root) == 48);
        }
    }
}

