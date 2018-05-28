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
    public class Lc102BinaryTreeLevelOrderTraversal
    {
        public IList<IList<int>> LevelOrder(TreeNode root)
        {
            var ret = new List<IList<int>>();
            if (root == null) return ret;
            var q = new Queue<TreeNode>();
            q.Enqueue(root);
            while (q.Count > 0)
            {
                var li = new List<int>();
                for (int i = q.Count; i > 0; i--)
                {
                    var node = q.Dequeue();
                    li.Add(node.val);
                    if (node.left != null) q.Enqueue(node.left);
                    if (node.right != null) q.Enqueue(node.right);
                }

                ret.Add(li);
            }

            return ret;
        }


        public void Test()
        {
            var root = new TreeNode(3)
            {
                left = new TreeNode(9),
                right = new TreeNode(20)
                {
                    left = new TreeNode(15),
                    right = new TreeNode(7)
                }
            };
            var exp = new List<IList<int>>
            {
                new List<int>{3 },
                new List<int>{9,20 },
                new List<int>{15,7 },
            };
            Console.WriteLine(exp.SameSet(LevelOrder(root)));
        }
    }
}

