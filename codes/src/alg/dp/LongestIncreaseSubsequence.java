package alg.dp;

import java.util.*;

public class LongestIncreaseSubsequence {

	public static void main(String[] args) {
		int[] a = new int[]{5,2,7,4,3,8};
		System.out.println(lis2(a) == 3);
		
		a = new int[]{15,27,14,38,26,55,46,65,85};
		System.out.println(lis2(a) == 6);
	}

	static int lis(int[] a) {
		int[] endings = new int[a.length];
		int len = 0;
		for (int i = 0; i < a.length; i++) {
			int idx = Arrays.binarySearch(endings, 0, len, a[i]);
			if (idx < 0) {
				endings[-idx-1] = a[i];
				if (-idx-1 > len-1) len = -idx;
			}			
		}
		return len;
	}
	
	/* let Ending(n) as the lenght of longest increase subsequence ending with a[n],
	 * Ending(n) = max(Ending(i) if a[i]>a[n], i=[0..n-1]) or 1
	 */
	static int dp(int[] a) {
		int[] lisEnd = new int[a.length];
		int[] lisCnt = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			lisEnd[i] = a[i];
			lisCnt[i] = 1;
			for (int j = 0; j < i; j++) {
				if (lisEnd[j] < a[i] && lisCnt[i] < lisCnt[j] + 1) {
					lisCnt[i] = lisCnt[j] + 1;
				}
			}
		}
		
		int max = 0;
		for (int i : lisCnt) {
			if (max < i) max = i;
		}
		
		return max;
	}

	static int lis2(int[] a) {
		int[] endings = new int[a.length];
		int len = 0;
		ArrayList<LinkedList<Integer>> subs = new ArrayList<LinkedList<Integer>>(a.length);
		for (int i = 0; i < a.length; i++) {
			subs.add(new LinkedList<Integer>());
			int idx = Arrays.binarySearch(endings, 0, len, a[i]);
			if (idx < 0) {
				endings[-idx-1] = a[i];
				if (-idx-1 > len-1) {
					len = -idx;
					if (-idx-1 > 0) subs.get(-idx-1).addAll(subs.get(-idx-2));
				}
				else subs.get(-idx-1).removeLast();
				subs.get(-idx-1).add(a[i]);
			}
		}
		for (Iterator<Integer> it = subs.get(len-1).iterator(); it.hasNext(); ) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		return len;
	}
}
