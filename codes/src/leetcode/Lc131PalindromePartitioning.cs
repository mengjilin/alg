using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: dp
 * dp[i] = U{dp[j-1] + s[j..i], if s[j..i] is palindrome, j=[0..i]}
 */
namespace leetcode
{
    public class Lc131PalindromePartitioning
    {
        public IList<IList<string>> Partition(string s)
        {
            if (string.IsNullOrEmpty(s)) return new List<IList<string>>();
            var dp = new IList<IList<string>>[s.Length];
            var isPalindrome = new bool[s.Length, s.Length];

            for (int i = 0; i < s.Length; i++)
            {
                dp[i] = new List<IList<string>>();
                for (int j = 0; j <= i; j++)
                {
                    if (s[i] == s[j] && (i - j <= 1 || isPalindrome[j + 1, i - 1]))
                    {
                        isPalindrome[j, i] = true;
                        if (j == 0) dp[i].Add(new List<string> { s.Substring(j, i - j + 1) });
                        else foreach (var li in dp[j - 1])
                        {
                            var newli = li.ToList();
                            newli.Add(s.Substring(j, i - j + 1));
                            dp[i].Add(newli);
                        }
                    }
                }
            }

            return dp[dp.Length - 1];
        }

        public void Test()
        {
            var exp = new List<IList<string>> {
                new List<string>{ "aa", "b"},
                new List<string>{ "a", "a", "b"}, };
            Console.WriteLine(exp.SameSet(Partition("aab")));

            exp = new List<IList<string>> {
                new List<string>{ "efe"},
                new List<string>{ "e", "f", "e"}, };
            Console.WriteLine(exp.SameSet(Partition("efe")));
        }
    }
}
