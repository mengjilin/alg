package alg;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static Comparator<List<Integer>> LISTLIST_COMP = new Comparator<List<Integer>>() {
        @Override
        public int compare(List<Integer> a, List<Integer> b) {
            Iterator<Integer> ia = a.iterator(), ib = b.iterator();
            while (ia.hasNext() && ib.hasNext()) {
                int res = ia.next() - ib.next();
                if (res != 0) return res;
            }
            return ia.hasNext() ? 1 : ib.hasNext() ? -1 : 0;
        }
    };
}
