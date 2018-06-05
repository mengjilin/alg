using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: finite state machine, circuit design
 * Given an array of integers, every element appears p times except for one, which appears q times. Find that single one.
 * We need mp [=ceil(logp)] wires/variables to represent all p states. 
 * When incoming [number i] is 0, states are not changed, when incoming is 1, states move forward 1.
 * We use a mask to control states only roll p times. When states reach p-th state, they should be reset to 0. 
 * If p = 2^mp, then we don't need a mask. They can be reset naturally.
 * wires[mp-1] ^= wires[mp-2] & ... & wires[0] & i;
 * ...
 * wires[0] ^= i;
 * 
 * umask is p representing by wires bits/states. e.g. 
 * when p = 2, don't need mask and reset explicitly;
 * when p = 3 = 11B, then umask = w[1] & w[0];
 * when p = 5 = 101B, then umask = w[2] & ~w[1] & w[0];
 * 
 * then reset wires if needed:
 * wires[j] &= ~umask;
 */
namespace leetcode
{
    public class Lc137SingleNumberII
    {
        public int SingleNumber2(int[] nums)
        {
            int w = 0;
            foreach (var i in nums) w ^= i;

            return w;
        }

        public int SingleNumber3(int[] nums)
        {
            int a = 0, b = 0;
            foreach (var i in nums)
            {
                b ^= a & i;
                a ^= i;
                int umask = b & a;
                b &= ~umask;
                a &= ~umask;
            }

            return a | b;
        }

        public int SingleNumber5(int[] nums)
        {
            int a = 0, b = 0, c = 0;
            foreach (var i in nums)
            {
                c ^= b & a & i;
                b ^= a & i;
                a ^= i;
                int umask = c & ~b & a;
                c &= ~umask;
                b &= ~umask;
                a &= ~umask;
            }

            return a | b | c;
        }

        public int SingleNumberP(int[] nums, int p)
        {
            int mp = (int)Math.Ceiling(Math.Log(p));
            var wires = new int[mp];
            foreach (var i in nums)
            {
                int mask = ~0;
                for (int pw = ~0, k = 0; k < mp; k++)
                {
                    int t = pw & wires[k];
                    wires[k] ^= pw & i;
                    pw = t;
                    mask &= ((p >> k) & 1) == 1 ? wires[k] : ~wires[k];
                }

                for (int k = 0; k < mp; k++)
                    wires[k] &= ~mask;
            }

            int ret = 0;
            for (int k = 0; k < mp; k++) ret |= wires[k];
            return ret;
        }

        public void Test()
        {
            var nums = new int[] { 2, 2, 3, 2 };
            Console.WriteLine(SingleNumber3(nums) == 3);
            Console.WriteLine(SingleNumberP(nums, 3) == 3);

            nums = new int[] { 0, 1, 0, 1, 0, 1, 99 };
            Console.WriteLine(SingleNumber3(nums) == 99);
            Console.WriteLine(SingleNumberP(nums, 3) == 99);
        }
    }
}
