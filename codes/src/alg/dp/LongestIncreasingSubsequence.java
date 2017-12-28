package alg.dp;

import java.util.*;

public class LongestIncreasingSubsequence {

	/* 
	 * let Ending(n) as the length of longest increasing subsequence ending with a[n],
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

	// bs: replace the inactive list
	static int lis(int[] a) {
		int[] endings = new int[a.length];
		int len = 0;
		for (int i = 0; i < a.length; i++) {
			int idx = Arrays.binarySearch(endings, 0, len, a[i]);
			if (idx < 0) {
				endings[-idx-1] = a[i];
				if (-idx-1 > len-1) len++;
			}			
		}
		return len;
	}

	static int lisWithLogs(int[] a) {
		int[] endings = new int[a.length];
		int len = 0;
		LinkedList[] logs = new LinkedList[a.length];
		for (int i = 0; i < a.length; i++) {
			logs[i] = new LinkedList<Integer>();
			int idx = Arrays.binarySearch(endings, 0, len, a[i]);
			if (idx < 0) {
				endings[-idx-1] = a[i];
				if (-idx-1 > len-1) {
					len++;
					if (len > 1) logs[len-1].addAll(logs[len-2]);
				}
				else logs[-idx-1].removeLast();
				logs[-idx-1].add(a[i]);
			}
		}
		for (Object i : logs[len-1]) {
			System.out.print(i + " ");
		}
		System.out.println();
		return len;
	}

	static int lisWithLogs2(int[] a) {
		int[] endingIdx = new int[a.length];
		int[] pre = new int[a.length];
		int len = 0;
		for (int i = 0; i < a.length; i++) {
			int lo = 0, hi = len-1;
			while (lo <= hi) {
				int mid = (lo + hi) / 2;
				if (a[endingIdx[mid]] < a[i]) lo = mid + 1;
				else hi = mid - 1;
			}
			endingIdx[lo] = i;
			if (lo > 1) pre[i] = endingIdx[lo-1];
			if (len < lo + 1) len = lo + 1;
		}
		
		int[] logs = new int[len];
		for (int i = len-1, k = endingIdx[len-1]; i >= 0; i--) {
			logs[i] = a[k];
			k = pre[k];
		}
		for (int i : logs) {
			System.out.print(i + " ");
		}
		System.out.println();
		return len;
	}

	public static void main(String[] args) {
		int[] a = new int[]{5,2,7,4,3,8};
		System.out.println(lisWithLogs2(a) == 3);
		
		a = new int[]{15,27,14,38,26,55,46,65,85};
		System.out.println(lisWithLogs2(a) == 6);
	}
}
