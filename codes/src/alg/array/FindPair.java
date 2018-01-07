package alg.array;

public class FindPair {

	/*
	 * Time(n), Space(1)
	 * find a pair of number with sum as the given value in a sorted array
	 * check sum of two ends.
	 */
	static boolean findPair(int[] a, int sum) {
		for (int i = 0, j = a.length - 1; i < j; ) {
			if (a[i] + a[j] == sum) return true;
			if (a[i] + a[j] < sum) i++;
			else j--;
		}
		return false;
	}

	public static void main(String[] args) {
		int[] a = new int[]{-8, 1, 4, 6, 10, 45};
		boolean r = findPair(a, 16);
		boolean expected = true;
		System.out.println(r == expected);
	}
}
