package alg.array;

public class FindMajority {

	/*
	 * Time(n), Space(1)
	 * using Moore’s Voting Algorithm
	 */
	static int findMajority(int[] a) {
		int cand = getCandidate(a);
		int cnt = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == cand) cnt++;
		}
		return cnt > a.length/2 ? cand : Integer.MIN_VALUE;
	}
	
	static int getCandidate(int[] a) {
		int cand = Integer.MIN_VALUE;
		int cnt = 0;
		for (int i = 0; i < a.length; i++) {
			if (cnt == 0) {
				cand = a[i];
				cnt++;
			} else if (a[i] == cand) cnt++;
			else cnt--;
		}
		
		return cand;
	}

	public static void main(String[] args) {
		int[] a = new int[] {2, 2, 3, 5, 2, 2, 6};
		int r = findMajority(a);
		int expected = 2;
		System.out.println(r == expected);
	}
}
