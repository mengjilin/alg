package leetcode;

/*
 * tags: two pointers
 * Time(n), Space(1)
 */
public class Lc026RemoveDuplicatesfromSortedArray {
    public static int removeDuplicates(int[] nums) {
        int ret = nums.length == 0 ? 0 : 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[ret-1]) nums[ret++] = nums[i];
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,1,2};
        System.out.println(removeDuplicates(a) == 2);

        a = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(a) == 5);
    }
}
