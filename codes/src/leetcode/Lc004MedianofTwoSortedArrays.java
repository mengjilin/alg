package leetcode;

/*
 * tags: binary search, two pointers
 * Time(logn), Space(1)
 * divide two parts: (a[0..i-1], b[0..j-1]) and (a[i..n-1], b[j..m-1] where i can be 0..n
 */
public class Lc004MedianofTwoSortedArrays {
    public static double findMedianSortedArrays(int[] a, int[] b) {
        if (b.length < a.length) {
            int[] tmp = a;
            a = b;
            b = tmp;
        }

        for (int lo = 0, hi = a.length; lo <= hi; ) {
            int i = (lo + hi) / 2;
            int j = (b.length + a.length) / 2 - i;
            if (i > 0 && a[i-1] > b[j]) hi = i - 1;
            else if (i < a.length && j > 0 && a[i] < b[j-1]) lo = i + 1;
            else {
                double minR = i == a.length ? b[j] : j == b.length ? a[i] : Math.min(a[i], b[j]);
                if ((a.length + b.length) % 2 == 1) return minR;
                double maxL = i == 0 ? b[j-1] : j == 0 ? a[i-1] : Math.max(a[i-1], b[j-1]);
                return (maxL + minR) / 2.0;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2};
        int[] b = new int[] {3};
        System.out.println(findMedianSortedArrays(a, b) == 2);

        a = new int[] {1, 2};
        b = new int[] {3, 4};
        System.out.println(findMedianSortedArrays(a, b) == 2.5);

        a = new int[] {};
        b = new int[] {1};
        System.out.println(findMedianSortedArrays(a, b) == 1);

        a = new int[] {1};
        b = new int[] {1};
        System.out.println(findMedianSortedArrays(a, b) == 1);

        a = new int[] {1};
        b = new int[] {2, 3, 4};
        System.out.println(findMedianSortedArrays(a, b) == 2.5);
    }
}
