package geeksforgeeks.backtracking;

/*
 * Time: O(n!), Space: O(n)
 */
public class HamiltonianCycle {
	
	static boolean cycle(int[][] graph, int[] steps, int i) {
		if (i == graph.length) {
			if (graph[steps[i-1]-1][0] == 1) {
				print(steps);
				return true;
			} else {
				return false;
			}
		}
		
		for (int j = 0; j < graph.length; j++) {
			if (isSafe(graph, steps, steps[i-1]-1, j)) {
				steps[i] = j + 1;
				boolean r = cycle(graph, steps, i+1);
				if (r) return true;
				steps[i] = 0; // backtracking
			}
		}
		
		return false;
	}
	
	static boolean isSafe(int[][] graph, int[] steps, int i, int j) {
		if (graph[i][j] == 0) return false;
		for (int k = 0; k <= i; k++) {
			if (steps[k] == j + 1) return false;
		}
		return true;
	}
	
	static void print(int[] steps) {
		for (int i : steps) System.out.print(i + " ");
		System.out.println();
	}
	
	static void hamiltonianCycle(int[][] graph) {
		int[] steps = new int[graph.length];
		steps[0] = 1;
		cycle(graph, steps, 1);
	}

	public static void main(String[] args) {
		int[][] graph = new int[][] {
			{0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0}};
	    hamiltonianCycle(graph);
	    
	    graph = new int[][] {
	    	{0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 0},
            {0, 1, 1, 0, 0}};
	    hamiltonianCycle(graph);
	}

}
