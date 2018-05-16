package leetcode;

/*
 * tags: two pointers
 * Time(n), Space(1)
 */
public class Lc042TrappingRainWater {
    public static int trap(int[] height) {
        int ret = 0;

        for (int i = 0, j = height.length - 1, maxL = 0, maxR = height.length - 1; i < j; ) {
            if (height[i] < height[j]) {
                if (height[maxL] > height[i]) ret += height[maxL] - height[i];
                else maxL = i;
                i++;
            } else {
                if (height[maxR] > height[j]) ret += height[maxR] - height[j];
                else maxR = j;
                j--;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(nums) == 6);
    }
}
