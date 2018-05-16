package alg.dp;

/*
 * Time(nW), Space(W), weights must be integer
 * bounded: F(n,W) = max(F(n-1,W), F(n-1,W-w[n])+values[n])
 * unbound: F(W) = max(F(W-1), F(W-w[i])+values[i]), i=[0..n-1]
 */
public class Knapsack_dp {

	// bounded: improve space. weights are sorted
	static int knapsack_dp(int[] values, int[] weights, int capacity) {
		int[] maxF = new int[capacity + 1];
		for (int i = 0; i < values.length; i++) {
			for (int w = capacity; w > 0; w--) { // reverse iteration to avoid items being reused.
				if (w >= weights[i])
					maxF[w] = Math.max(maxF[w], maxF[w - weights[i]] + values[i]);
			}
		}
		return maxF[capacity];
	}

	// bounded: standard
	static int knapsack_dp2(int[] values, int[] weights, int capacity) {
		int[][] maxF = new int[values.length + 1][capacity + 1];
		for (int i = 1; i <= values.length; i++) {
			for (int w = 1; w <= capacity; w++) {
				if (w < weights[i - 1]) maxF[i][w] = maxF[i - 1][w];
				else maxF[i][w] = Math.max(maxF[i - 1][w], maxF[i - 1][w - weights[i - 1]] + values[i - 1]);
			}
		}
		return maxF[values.length][capacity];
	}

	// unbounded
	static int knapsack_dp_unbound(int[] values, int[] weights, int capacity) {
		int[] maxF = new int[capacity + 1];
		for (int w = 1; w <= capacity; w++) {
			for (int i = 0; i < values.length; i++) {
				if (w >= weights[i])
					maxF[w] = Math.max(maxF[w], maxF[w - weights[i]] + values[i]);
			}
		}
		return maxF[capacity];
	}

	public static void main(String[] args) {
		int[] values = {60, 100, 120};
		int[] weights = {10, 20, 30};
		System.out.println(220 == knapsack_dp(values, weights, 50));
		System.out.println(220 == knapsack_dp2(values, weights, 50));
		System.out.println(300 == knapsack_dp_unbound(values, weights, 50));
	}

}
