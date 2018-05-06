package geeksforgeeks.greedy;

import java.util.Arrays;

public class ActivitySelection {

	public static void main(String[] args) {
		int s[] =  {1, 3, 0, 5, 8, 5};
	    int t[] =  {2, 4, 6, 7, 9, 9};
	    int[] r = activitySelection(s, t);
	    int[] exp = {0, 1, 3, 4};
	    System.out.println(Arrays.equals(r, exp));
	}
	
	static int[] activitySelection(int[] s, int[] t) {
		int[] r = new int[s.length];
		int rc = 1;
		for (int i = 1; i < s.length; i++) {
			if (s[i] >= t[r[rc-1]]) r[rc++] = i;
		}
		return Arrays.copyOf(r, rc);
	}

}
