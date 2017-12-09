package geeksforgeeks.array;

import java.util.Arrays;

public class FindPair {

	public static void main(String[] args) {
		int[] a = new int[]{1, 4, 45, 6, 10, -8};
		boolean r = findPair(a, 16);
		boolean expected = true;
		System.out.println(r == expected);
	}

	// find a pair of number with sum as the given value
	static boolean findPair(int[] a, int sum) {
		Arrays.sort(a);
		for (int i = 0, j = a.length - 1; i < j; ) {
			if (a[i] + a[j] == sum) return true;
			if (a[i] + a[j] < sum) i++;
			else j--;
		}
		return false;
	}
}
