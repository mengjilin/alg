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
    public class Lc056MergeIntervals
    {
        public IList<Interval> Merge(IList<Interval> intervals)
        {
            var ret = new List<Interval>();
            var nums = intervals as List<Interval>;
            if (nums == null) nums = intervals.ToList();
            nums.Sort((a, b) => a.start != b.start ? a.start - b.start : a.end - b.end);

            foreach (var it in nums)
            {
                if (ret.Count == 0 || ret.Last().end < it.start) ret.Add(it);
                else ret.Last().end = Math.Max(ret.Last().end, it.end);
            }
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
            var res = Merge(new List<Interval> {
                new Interval(8, 10),
                new Interval(2, 6),
                new Interval(1, 3),
                new Interval(15, 18) });
            var exp = new List<Interval> {
                new Interval(1, 6),
                new Interval(8, 10),
                new Interval(15, 18) };
            Console.WriteLine(exp.SequenceEqual(res));

            res = Merge(new List<Interval> {
                new Interval(1, 4),
                new Interval(4, 5) });
            exp = new List<Interval> {
                new Interval(1, 5) };
            Console.WriteLine(exp.SequenceEqual(res));
        }


    }
}

