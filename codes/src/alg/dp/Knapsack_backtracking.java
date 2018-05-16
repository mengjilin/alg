package alg.dp;

/*
 * Time(2^n), Space(W)
 */
public class Knapsack_backtracking {

	// bounded
	static double knapsack_backtracking(double[] values, double[] weights, int nth, double capacity) {
		double maxF = 0;
		for (int i = nth; i < values.length; i++) {
			if (capacity >= weights[i]) {
				double newF = knapsack_backtracking(values, weights, i + 1, capacity - weights[i]) + values[i];
				if (maxF < newF) maxF = newF;
			}
		}
		return maxF;
	}

	// unbound
	static double knapsack_backtracking_unbound(double[] values, double[] weights, double capacity) {
		double maxF = 0;
		for (int i = 0; i < values.length; i++) {
			if (capacity >= weights[i]) {
				double newF = knapsack_backtracking_unbound(values, weights, capacity - weights[i]) + values[i];
				if (maxF < newF) maxF = newF;
			}
		}
		return maxF;
	}

	public static void main(String[] args) {
		double[] values = new double[]{100, 60, 120};
		double[] weights = new double[]{20, 10, 30};
		System.out.println(220 == knapsack_backtracking(values, weights, 0, 50));
		System.out.println(300 == knapsack_backtracking_unbound(values, weights, 50));

		values = new double[]{40, 50, 100, 95, 30};
		weights = new double[]{2, 3.14, 1.98, 5, 3};
		System.out.println(235 == knapsack_backtracking(values, weights, 0, 10));
		System.out.println(500 == knapsack_backtracking_unbound(values, weights, 10));
	}

}
