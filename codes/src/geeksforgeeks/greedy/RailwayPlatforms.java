package geeksforgeeks.greedy;

import java.util.Arrays;

public class RailwayPlatforms {

	public static void main(String[] args) {
		int[] arr = {900, 940, 950, 1100, 1500, 1800};
	    int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
	    int r = railwayPlatforms(arr, dep);
	    System.out.print(r == 3);
	}
	
	static int railwayPlatforms(int[] arr, int[] dep) {
		int r = 0;
		
		Arrays.sort(arr);
		Arrays.sort(dep);
		int plat = 0;
		for (int i = 0, j = 0; i < arr.length; ) {
			if (arr[i] < dep[j]) {
				i++;
				plat++;
				if (r < plat) r = plat;
			} else {
				j++;
				plat--;
			}
		}
		return r;
	}

}
