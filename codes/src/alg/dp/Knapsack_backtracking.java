package alg.dp;

/*
 * Time(2^n), Space(W)
 */
public class Knapsack_backtracking {

	// bounded
	static double knapsack_backtracking(double[][] items, boolean[] selectedItems, int nth, double capacity) {
		double maxF = 0;
		for (int i = nth; i < items.length; i++) {
			if (!selectedItems[i] && capacity >= items[i][1]) {
				selectedItems[i] = true;
				double newF = knapsack_backtracking(items, selectedItems, i+1, capacity - items[i][1]) + items[i][0];
				if (maxF < newF) maxF = newF;
				selectedItems[i] = false; // backtracking
			}
		}
		return maxF;
	}

	// unbound
	static double knapsack_backtracking_unbound(double[][] items, double capacity) {
		double maxF = 0;
		for (int i = 0; i < items.length; i++) {
			if (capacity >= items[i][1]) {
				double newF = knapsack_backtracking_unbound(items, capacity - items[i][1]) + items[i][0];
				if (maxF < newF) maxF = newF;
			}
		}
		return maxF;
	}
	
	public static void main(String[] args) {
		double[][] items = {{60,10}, {100,20}, {120,30}}; // {value, weight}
		System.out.println(220 == knapsack_backtracking(items, new boolean[items.length], 0, 50));
		System.out.println(300 == knapsack_backtracking_unbound(items, 50));
		
		items = new double[][] {{40,2}, {50,3.14}, {100,1.98}, {95,5}, {30,3}};
		System.out.println(235 == knapsack_backtracking(items, new boolean[items.length], 0, 10));
		System.out.println(500 == knapsack_backtracking_unbound(items, 10));
	}

}
