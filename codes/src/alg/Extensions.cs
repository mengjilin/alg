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

        public static int Compare<T>(this T[,] a, T[,] b)
        {
            if (a == null && b == null) return 0;
            if (a == null) return -1;
            if (b == null) return 1;
            if (a.GetLength(0) != b.GetLength(0)) return a.GetLength(0) - b.GetLength(0);
            if (a.GetLength(1) != b.GetLength(1)) return a.GetLength(1) - b.GetLength(1);

            var comparer = Comparer<T>.Default;
            var ia = a.GetEnumerator();
            var ib = b.GetEnumerator();
            ia.Reset();
            ib.Reset();
            while (ia.MoveNext())
            {
                if (!ib.MoveNext()) return -1;
                int c = comparer.Compare((T)ia.Current, (T)ib.Current);
                if (c != 0) return c;
            }
            return ib.MoveNext() ? -1 : 0;
        }
    }
}
