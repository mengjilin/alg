package leetcode;

/*
 * tags: counting sort
 * Time(n), Space(n)
 */
public class Lc041FirstMissingPositive {
    public static int firstMissingPositive(int[] nums) {
        boolean[] used = new boolean[nums.length + 2];
        for (int i : nums) {
            if (0 < i && i < used.length) used[i] = true;
        }
        for (int i = 1; i < used.length; i++) {
            if (!used[i]) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 0};
        System.out.println(firstMissingPositive(nums) == 3);

        nums = new int[]{3, 4, -1, 1};
        System.out.println(firstMissingPositive(nums) == 2);

        nums = new int[]{7, 8, 9, 11, 12};
        System.out.println(firstMissingPositive(nums) == 1);
    }
}
