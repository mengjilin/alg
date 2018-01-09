package alg.dp;

import java.util.Arrays;

/*
 * Time(nlogn), Space(1), only for fractional knapsack, not 0-1 knapsack
 * sort them in decreasing order of ratio of value per unit weight
 */
public class Knapsack_greedy {

	static double knapsack_greedy(double[][] items, double capacity) {
		Arrays.sort(items, (a, b) -> (int)(b[0]/b[1] - a[0]/a[1]));
		double maxF = 0;
		for (int i = 0; i < items.length && capacity > 0; i++) {
			maxF += items[i][0] * (capacity > items[i][1] ? 1 : capacity / items[i][1]);
			capacity -= items[i][1];
		}
		return maxF;
	}
	
	public static void main(String[] args) {
		double[][] items = {{60,10}, {100,20}, {120,30}}; // {value, weight}
		System.out.println(240 == knapsack_greedy(items, 50));
	}

}
