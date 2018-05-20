using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: array
 */
namespace leetcode
{
    public class Lc057InsertInterval
    {
        public IList<Interval> Insert(IList<Interval> intervals, Interval newInterval)
        {
            var ret = new List<Interval>();

            foreach (var it in intervals)
            {
                if (it.end < newInterval.start)
                {
                    ret.Add(it);
                }
                else if (newInterval.end < it.start)
                {
                    if (ret.Count == 0 || ret.Last().end < newInterval.start) ret.Add(newInterval);
                    ret.Add(it);
                }
                else
                {
                    var n = new Interval(Math.Min(newInterval.start, it.start), Math.Max(newInterval.end, it.end));
                    if (ret.Count == 0 || ret.Last().end < n.start) ret.Add(n);
                    else ret.Last().end = n.end;
                }
            }

            if (ret.Count == 0 || ret.Last().end < newInterval.start) ret.Add(newInterval);

            return ret;
        }

        public class Interval
        {
            public int start;
            public int end;
            public Interval() { start = 0; end = 0; }
            public Interval(int s, int e) { start = s; end = e; }
            public override bool Equals(object obj)
            {
                var that = obj as Interval;
                return that != null && this.start == that.start && this.end == that.end;
            }
        }

        public void Test()
        {
            var res = Insert(new List<Interval> {
                new Interval(1, 3),
                new Interval(6, 9) },
                new Interval(2, 5));
            var exp = new List<Interval> {
                new Interval(1, 5),
                new Interval(6, 9) };
            Console.WriteLine(exp.SequenceEqual(res));

            res = Insert(new List<Interval> {
                new Interval(1, 2),
                new Interval(3, 5),
                new Interval(6, 7),
                new Interval(8, 10),
                new Interval(12, 16) },
                new Interval(4, 8));
            exp = new List<Interval> {
                new Interval(1, 2),
                new Interval(3, 10),
                new Interval(12, 16) };
            Console.WriteLine(exp.SequenceEqual(res));
        }


    }
}

