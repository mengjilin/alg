package leetcode;

/*
 * tags: two pointers
 * Time(n), Space(1)
 */
public class Lc011MaxArea {
    public static int maxArea(int[] a) {
        int max = 0;
        for (int i = 0, j = a.length - 1; i < j; ) {
            max = Math.max(max, Math.min(a[i], a[j]) * (j - i));
            if (a[i] <= a[j]) i++;
            else j--;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(a) == 49);
    }
}
