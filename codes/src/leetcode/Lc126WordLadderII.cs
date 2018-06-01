using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: bfs
 * 1. create a graph
 * 2. like Dijkstra to divide nodes to two set: visited/unvisited.
 * 3. how to find the neighbors? we can try all 26 tranformations on each char, or we can create all hash of word deleted one char.
 */
namespace leetcode
{
    public class Lc126WordLadderII
    {
        public IList<IList<string>> FindLadders(string beginWord, string endWord, IList<string> wordList)
        {
            var visitingWords = new HashSet<string>(new string[] { beginWord });
            var unvisitedWords = new HashSet<string>(wordList);
            unvisitedWords.Remove(beginWord); // no parents of beginWord
            var paths = new Dictionary<string, IList<IList<string>>>();
            paths.Add(beginWord, new List<IList<string>> { new List<string> { beginWord } });

            while (visitingWords.Count > 0 && !visitingWords.Contains(endWord))
            {
                var nextVisistingWords = new HashSet<string>();
                foreach (var word in visitingWords)
                {
                    var wordChars = word.ToCharArray();
                    for (int i = 0; i < wordChars.Length; i++)
                    {
                        var c = wordChars[i];
                        for (int j = 0; j < 26; j++)
                        {
                            wordChars[i] = (char)('a' + j);
                            var newWord = new string(wordChars);
                            if (unvisitedWords.Contains(newWord))
                            {
                                nextVisistingWords.Add(newWord);
                                if (!paths.ContainsKey(newWord)) paths[newWord] = new List<IList<string>>();
                                foreach (var li in paths[word])
                                {
                                    var newli = li.ToList();
                                    newli.Add(newWord);
                                    paths[newWord].Add(newli);
                                }
                            }
                            wordChars[i] = c;
                        }
                    }
                }

                visitingWords = nextVisistingWords;
                foreach (var word in visitingWords) unvisitedWords.Remove(word);
            }

            return paths.ContainsKey(endWord) ? paths[endWord] : new List<IList<string>>();
        }

        public IList<IList<string>> FindLadders2(string beginWord, string endWord, IList<string> wordList)
        {
            var visitingWords = new HashSet<string>(new string[] { beginWord });
            var unvisitedWords = new HashSet<string>(wordList);
            unvisitedWords.Remove(beginWord); // make sure no dup
            var parents = new Dictionary<string, IList<string>>();

            while (visitingWords.Count > 0 && !visitingWords.Contains(endWord))
            {
                var nextVisistingWords = new HashSet<string>();
                foreach (var word in visitingWords)
                {
                    var wordChars = word.ToCharArray();
                    for (int i = 0; i < wordChars.Length; i++)
                    {
                        var c = wordChars[i];
                        for (int j = 0; j < 26; j++)
                        {
                            wordChars[i] = (char)('a' + j);
                            var newWord = new string(wordChars);
                            if (unvisitedWords.Contains(newWord))
                            {
                                nextVisistingWords.Add(newWord);
                                if (!parents.ContainsKey(newWord))
                                    parents.Add(newWord, new List<string> { word });
                                else parents[newWord].Add(word);
                            }
                            wordChars[i] = c;
                        }
                    }
                }

                visitingWords = nextVisistingWords;
                foreach (var word in visitingWords) unvisitedWords.Remove(word);
            }

            var ret = new List<IList<string>>();
            if (!parents.ContainsKey(endWord)) return ret;
            ConstructPathsDfs(parents, beginWord, endWord, ret, new LinkedList<string>());
            return ret;
        }

        void ConstructPathsDfs(Dictionary<string, IList<string>> parents, string beginWord, string next, IList<IList<string>> ret, LinkedList<string> selected)
        {
            if (next == beginWord)
            {
                var li = new List<string> { beginWord };
                li.AddRange(selected);
                ret.Add(li);
                return;
            }

            foreach (var w in parents[next])
            {
                selected.AddFirst(next);
                ConstructPathsDfs(parents, beginWord, w, ret, selected);
                selected.RemoveFirst();
            }
        }

        public void Test()
        {
            var words = new List<string> { "hot", "dot", "dog", "lot", "log", "cog" };
            var exp = new List<IList<string>>
            {
                new List<string>{ "hit", "hot", "dot", "dog", "cog" },
                new List<string>{ "hit", "hot", "lot", "log", "cog" }
            };
            Console.WriteLine(exp.SameSet(FindLadders("hit", "cog", words)));
            Console.WriteLine(exp.SameSet(FindLadders2("hit", "cog", words)));

            words = new List<string> { "hot", "dot", "dog", "lot", "log" };
            exp = new List<IList<string>>();
            Console.WriteLine(exp.SameSet(FindLadders("hit", "cog", words)));
            Console.WriteLine(exp.SameSet(FindLadders2("hit", "cog", words)));

            words = new List<string> { "ted", "tex", "red", "tax", "tad", "den", "rex", "pee" };
            exp = new List<IList<string>>
            {
                new List<string>{ "red", "ted", "tad", "tax" },
                new List<string>{ "red", "ted", "tex", "tax" },
                new List<string>{ "red", "rex", "tex", "tax" }
            };
            Console.WriteLine(exp.SameSet(FindLadders("red", "tax", words)));
            Console.WriteLine(exp.SameSet(FindLadders2("red", "tax", words)));
        }
    }
}

