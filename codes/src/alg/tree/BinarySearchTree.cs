using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: tree
 * BST: left.key < node.key < right.key, and left and right are both BST.
 */
namespace alg.tree
{
    public class BinarySearchTree
    {
        public IList<int> InorderTraversalDfs(Node root)
        {
            var ret = new List<int>();
            InorderTraversalRc(root, ret);
            return ret;
        }

        public void InorderTraversalRc(Node root, IList<int> res)
        {
            if (root == null) return;
            InorderTraversalRc(root.Lc, res);
            res.Add(root.Val);
            InorderTraversalRc(root.Rc, res);
        }

        public IList<int> InorderTraversalStack(Node root)
        {
            var ret = new List<int>();
            var stack = new Stack<Node>();
            var curr = root;
            while (curr != null || stack.Count > 0)
            {
                while (curr != null) // push all left
                {
                    stack.Push(curr);
                    curr = curr.Lc;
                }
                curr = stack.Pop();
                ret.Add(curr.Val); // visit
                curr = curr.Rc; // move to right
            }

            return ret;
        }

        /*
         * Time(n), Space(1)
         * modify the tree
         */
        public IList<int> InorderTraversalMorris(Node root)
        {
            var ret = new List<int>();
            var curr = root;
            while (curr != null)
            {
                if (curr.Lc == null)
                {
                    ret.Add(curr.Val); // visit
                    curr = curr.Rc;
                }
                else
                {
                    var pre = curr.Lc;
                    while (pre.Rc != null) pre = pre.Rc; // find rightmost
                    pre.Rc = curr; // make current root as its right child
                    var tmp = curr;
                    curr = curr.Lc;
                    tmp.Lc = null; // set original curr left to null, to avoid loop
                }
            }

            return ret;
        }

        /*
         * bfs
         */
        public IList<IList<int>> LevelOrder(Node root)
        {
            var ret = new List<IList<int>>();
            if (root == null) return ret;
            var q = new Queue<Node>();
            q.Enqueue(root);
            while (q.Count > 0)
            {
                var li = new List<int>();
                for (int i = q.Count; i > 0; i--) // the current level has q.Count nodes
                {
                    var node = q.Dequeue();
                    li.Add(node.Val); // visit
                    if (node.Lc != null) q.Enqueue(node.Lc);
                    if (node.Rc != null) q.Enqueue(node.Rc);
                }

                ret.Add(li);
            }

            return ret;
        }

        public IList<IList<int>> LevelOrderDfs(Node root)
        {
            var ret = new List<IList<int>>();
            LevelOrderDfsRc(root, 0, ret);
            return ret;
        }

        public void LevelOrderDfsRc(Node root, int level, IList<IList<int>> result)
        {
            if (root == null) return;
            if (level == result.Count) result.Add(new List<int>());
            result[level].Add(root.Val); // visit
            LevelOrderDfsRc(root.Lc, level + 1, result);
            LevelOrderDfsRc(root.Rc, level + 1, result);
        }

        /*
         * tag: dp
         * Time(n^2), Space(n)
         * dp[n] = sum(dp[i]*dp[n-i-1]), i=[1..n]
         * select i as root
         * Given n, return the amount of unique BST's (binary search trees) that store values 1 ... n.
         */
        int UniqueTrees(int n)
        {
            var dp = new int[n + 1];
            dp[0] = dp[1] = 1;
            for (int len = 2; len <= n; len++)
            {
                for (int i = 0; i < len; i++)
                    dp[len] += dp[i] * dp[len - i - 1];
            }
            return dp[n];
        }

        public class Node
        {
            public int Val;
            public Node Lc, Rc;
            public Node(int val) { Val = val; }
            public Node(Node n)
            {
                Val = n.Val;
                if (n.Lc != null) Lc = new Node(n.Lc);
                if (n.Rc != null) Rc = new Node(n.Rc);
            }
        }

        public void Test()
        {
            var root = new Node(2)
            {
                Lc = new Node(1),
                Rc = new Node(3)
                {
                    Rc = new Node(4)
                }
            };
            var exp = new List<int> { 1, 2, 3, 4 };
            Console.WriteLine(exp.SequenceEqual(InorderTraversalDfs(root)));
            Console.WriteLine(exp.SequenceEqual(InorderTraversalStack(root)));
            Console.WriteLine(exp.SequenceEqual(InorderTraversalMorris(new Node(root))));

            var exp1 = new List<IList<int>>
            {
                new List<int>{2 },
                new List<int>{1, 3 },
                new List<int>{4 },
            };
            Console.WriteLine(exp1.SameSet(LevelOrder(root)));
            Console.WriteLine(exp1.SameSet(LevelOrderDfs(root)));

            Console.WriteLine(UniqueTrees(3) == 5);
        }
    }
}

