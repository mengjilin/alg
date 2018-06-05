using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: union find?
 */
namespace leetcode
{
    public class Lc140WordBreakII
    {
        public IList<string> WordBreak(string s, IList<string> wordDict)
        {
            if (string.IsNullOrEmpty(s)) return null;
            var dp = new List<string>[s.Length];
            var words = new HashSet<string>(wordDict);

            for (int i = 0; i < s.Length; i++)
            {
                dp[i] = new List<string>();
                for (int j = 0; j <= i; j++)
                {
                    var subs = s.Substring(j, i - j + 1);
                    if (words.Contains(subs))
                    {
                        if (j == 0) dp[i].Add(subs);
                        else foreach (var ps in dp[j - 1])
                            dp[i].Add(ps + " " + subs);
                    }
                }
            }

            return dp[s.Length - 1];
        }

        public void Test()
        {
            var s = "catsanddog";
            var words = new List<string> { "cat", "cats", "and", "sand", "dog" };
            var exp = new List<string> { "cats and dog", "cat sand dog" };
            Console.WriteLine(exp.SameSet(WordBreak(s, words)));

            s = "pineapplepenapple";
            words = new List<string> { "apple", "pen", "applepen", "pine", "pineapple" };
            exp = new List<string> { "pine apple pen apple", "pineapple pen apple", "pine applepen apple" };
            Console.WriteLine(exp.SameSet(WordBreak(s, words)));

            s = "catsandog";
            words = new List<string> { "cats", "dog", "sand", "and", "cat" };
            exp = new List<string> { };
            Console.WriteLine(exp.SameSet(WordBreak(s, words)));

            s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            words = new List<string> { "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa" };
            exp = new List<string> { };
            Console.WriteLine(exp.SameSet(WordBreak(s, words)));
        }
    }
}

