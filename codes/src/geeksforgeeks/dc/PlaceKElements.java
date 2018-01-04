package geeksforgeeks.dc;

import java.util.Arrays;

/*
 * Divide and Conquer, from 0 to max diff (=max(array)-min(array))
 * similar with StudentsReadConsecutiveBooks 
 */
public class PlaceKElements {

	static int maxDistance(int[] positions, int k) {
		Arrays.sort(positions);
		int r = 0;
		for (int s = 0, t = positions[positions.length-1]; s <= t; ) {
			int mid = s + (t-s)/2;
			if (isPossible(positions, k, mid)) {
				s = mid + 1;
				if (r < mid) r = mid;
			} else {
				t = mid - 1;
			}
		}
		return r;
	}
	
	static boolean isPossible(int[] positions, int k, int maxDistance) {
		int count = 1;
		for (int i = 1, prev = 0; i < positions.length; i++) {
			if (positions[i] - positions[prev] >= maxDistance) {
				prev = i;
				if (++count >= k) return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] positions = new int[]{1, 2, 8, 4, 9};
		System.out.println(3 == maxDistance(positions, 3));
		
		positions = new int[]{1, 2, 7, 5, 11, 12};
		System.out.println(5 == maxDistance(positions, 3));
		
		positions = new int[]{1, 5, 5, 11, 12};
		System.out.println(0 == maxDistance(positions, 5));
	}

}
