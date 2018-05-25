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
    public class Lc078Subsets
    {
        public IList<string> RestoreIpAddresses(string s)
        {
            return RestoreIpAddressesRc(s, 4);
        }

        public IList<string> RestoreIpAddressesRc(string s, int n)
        {
            if (n == 1)
            {
                var li = new List<string>();
                var si = int.Parse(s);
                if (0 <= si && si <= 255) li.Add(s);
                return li;
            }

            for (int i = 1; i < 3 && i < s.Length; i++)
            {
                var pre = s.Substring(0, i);
                if (0 <= int.Parse(pre) && int.Parse(pre) <= 255)
                {
                    var res = RestoreIpAddressesRc(s.Substring(i), n - 1);
                    foreach (var item in res)
                    {

                    }
                }
            }
            return ret;
        }


        public void Test()
        {
            var res = RestoreIpAddresses("25525511135");
            var exp = new List<string> {"255.255.11.135", "255.255.111.35"};
            (res as List<string>).Sort();
            exp.Sort();
            Console.WriteLine(exp.SequenceEqual(res));
        }
    }
}

