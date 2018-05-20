using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: number
 */
namespace leetcode
{
    public class Lc068TextJustification
    {
        public IList<string> FullJustify(string[] words, int maxWidth)
        {
            var ret = new List<string>();
            for (int i = 0; i < words.Length;)
            {
                int j = i + 1; // ending index plus 1
                int lineWordLen = words[i].Length;
                for (; j < words.Length && lineWordLen + words[j].Length + (j - i) <= maxWidth; j++)
                    lineWordLen += words[j].Length;
                int avgSpace = j == words.Length ? 1 : j == i + 1 ? 0 : (maxWidth - lineWordLen) / (j - i - 1);
                int extSpace = j == words.Length ? 0 : maxWidth - lineWordLen - (j - i - 1) * avgSpace;

                string line = words[i];
                for (i++; i < j; i++)
                {
                    int ext = 0;
                    if (extSpace > 0) { extSpace--; ext = 1; }
                    line += new string(' ', avgSpace + ext);
                    line += words[i];
                }
                line += new string(' ', maxWidth - line.Length);
                ret.Add(line);
            }

            return ret;
        }

        public void Test()
        {
            var words = new string[] { "This", "is", "an", "example", "of", "text", "justification." };
            var exp = new List<string> {
                "This    is    an",
                "example  of text",
                "justification.  "};
            Console.WriteLine(exp.SequenceEqual(FullJustify(words, 16)));

            words = new string[] { "What", "must", "be", "acknowledgment", "shall", "be" };
            exp = new List<string> {
                "What   must   be",
                "acknowledgment  ",
                "shall be        "};
            Console.WriteLine(exp.SequenceEqual(FullJustify(words, 16)));

            words = new string[] { "Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do" };
            exp = new List<string> {
                "Science  is  what we",
                "understand      well",
                "enough to explain to",
                "a  computer.  Art is",
                "everything  else  we",
                "do                  "};
            Console.WriteLine(exp.SequenceEqual(FullJustify(words, 20)));
        }
    }
}


