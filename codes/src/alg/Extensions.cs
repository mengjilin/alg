using System;
using System.Collections.Generic;
using System.Text;

namespace alg
{
    public static class Extensions
    {
        public static int Compare<T>(this IEnumerable<T> a, IEnumerable<T> b)
        {
            return Compare(a, b, Comparer<T>.Default);
        }

        public static int Compare<T>(this IEnumerable<T> a, IEnumerable<T> b, Comparer<T> comparer)
        {
            if (a == null && b == null) return 0;
            if (a == null) return -1;
            if (b == null) return 1;

            using (var ia = a.GetEnumerator())
            using (var ib = b.GetEnumerator())
            {
                while (ia.MoveNext())
                {
                    if (!ib.MoveNext()) return -1;
                    int c = comparer.Compare(ia.Current, ib.Current);
                    if (c != 0) return c;
                }
                return ib.MoveNext() ? -1 : 0;
            }
        }
    }
}
