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
    public class Lc127WordLadder
    {
        public int LadderLength26(string beginWord, string endWord, IList<string> wordList)
        {
            var ret = 2;
            var visitingWords = new HashSet<string>();
            visitingWords.Add(beginWord);
            var unvisitedWords = new HashSet<string>(wordList);

            while (visitingWords.Count > 0)
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
                                if (newWord == endWord) return ret;
                                unvisitedWords.Remove(newWord);
                                nextVisistingWords.Add(newWord);
                            }
                            wordChars[i] = c;
                        }
                    }
                }

                ret++;
                visitingWords = nextVisistingWords;
            }

            return 0;
        }

        public int LadderLength(string beginWord, string endWord, IList<string> wordList)
        {
            var ret = 1;
            int endIdx = -1;
            var visited = new bool[wordList.Count + 1];
            var graph = new List<List<List<int>>>();
            var map = new Dictionary<string, List<int>>();
            for (int i = 0; i <= wordList.Count; i++)
            {
                graph.Add(new List<List<int>>());
                var word = i < wordList.Count ? wordList[i] : beginWord;
                if (word == endWord) endIdx = i;
                for (int j = 0; j < word.Length; j++)
                {
                    var key = j + word.Substring(0, j) + word.Substring(j + 1);
                    if (map.ContainsKey(key)) map[key].Add(i);
                    else map[key] = new List<int> { i };
                    graph[i].Add(map[key]);
                }
            }

            if (endIdx == -1) return 0; // not found

            var q = new Queue<List<List<int>>>();
            q.Enqueue(graph.Last());
            visited[wordList.Count] = true;
            while (q.Count > 0)
            {
                ret++;
                for (int k = q.Count; k > 0; k--)
                {
                    var lis = q.Dequeue();
                    foreach (var li in lis)
                        foreach (var i in li)
                        {
                            if (i == endIdx) return ret;
                            if (!visited[i])
                            {
                                visited[i] = true;
                                q.Enqueue(graph[i]);
                            }
                        }
                }
            }

            return 0;
        }

        public void Test()
        {
            var words = new List<string> { "hot", "dot", "dog", "lot", "log", "cog" };
            Console.WriteLine(LadderLength("hit", "cog", words) == 5);
            Console.WriteLine(LadderLength26("hit", "cog", words) == 5);

            words = new List<string> { "hot", "dot", "dog", "lot", "log" };
            Console.WriteLine(LadderLength("hit", "cog", words) == 0);
            Console.WriteLine(LadderLength26("hit", "cog", words) == 0);

            words = new List<string> { "a", "b", "c" };
            Console.WriteLine(LadderLength("a", "c", words) == 2);
            Console.WriteLine(LadderLength26("a", "c", words) == 2);
        }
    }
}

