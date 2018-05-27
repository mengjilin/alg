using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: recursive
 * each part of IP address can't start with 0 if it is not 0.
 */
namespace leetcode
{
    public class Lc093RestoreIPAddresses
    {
        public IList<string> RestoreIpAddresses(string s)
        {
            //return RestoreIpAddressesBt(s);
            return RestoreIpAddressesDirect(s);
        }

        public IList<string> RestoreIpAddressesDirect(string s)
        {
            var ret = new List<string>();
            for (int a = 1; a <= 3; a++)
                for (int b = 1; b <= 3; b++)
                    for (int c = 1; c <= 3; c++)
                    {
                        int ai = Parse(s, 0, a);
                        int bi = Parse(s, a, b);
                        int ci = Parse(s, a + b, c);
                        int di = Parse(s, a + b + c, s.Length - a - b - c);
                        if (ai >= 0 && bi >= 0 && ci >= 0 && di >= 0)
                            ret.Add(ai + "." + bi + "." + ci + "." + di);
                    }
            return ret;
        }

        int Parse(string s, int i, int len)
        {
            if (i + len > s.Length || len <= 0 || len > 3 || (len > 1 && s[i] == '0'))
                return -1;
            var ret = int.Parse(s.Substring(i, len));
            if (ret > 255) return -1;
            return ret;
        }

        public IList<string> RestoreIpAddressesBt(string s)
        {
            var ret = new List<string>();
            RestoreIpAddressesRc(s, 0, ret, "");
            return ret;
        }

        public void RestoreIpAddressesRc(string s, int next, IList<string> result, string selected)
        {
            if (next == 4)
            {
                if (string.IsNullOrEmpty(s)) result.Add(selected);
                return;
            }

            for (int i = 1; i <= Math.Min(3, s.Length); i++)
            {
                var pre = s.Substring(0, i);
                if (int.Parse(pre) > 255 || (i > 1 && s[0] == '0')) continue;
                RestoreIpAddressesRc(s.Substring(i), next + 1, result, selected + (next == 0 ? "" : ".") + pre);
            }
        }


        public void Test()
        {
            var res = RestoreIpAddresses("25525511135");
            var exp = new List<string> { "255.255.11.135", "255.255.111.35" };
            Console.WriteLine(exp.SameSet(res));

            res = RestoreIpAddresses("010010");
            exp = new List<string> { "0.10.0.10", "0.100.1.0" };
            Console.WriteLine(exp.SameSet(res));
        }
    }
}

