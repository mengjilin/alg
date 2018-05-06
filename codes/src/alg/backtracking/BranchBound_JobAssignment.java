package alg.backtracking;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * backtracking using bfs with a branch&bound function
 * !! bound function is incorrect !!
 */
public class BranchBound_JobAssignment {

	static int[] jobAssignment(int[][] costs) {
		int n = costs.length;
		PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.cost - b.cost);
		Node dummy = new Node(-1, -1);
		dummy.jobAssigned = new boolean[n];
		q.add(dummy);
		
		while (!q.isEmpty()) {
			Node min = q.poll();
			if (min.i == n-1) {
				return formatReturn(min);
			}
			
			for (int j = 0; j < n; j++) {
				if (!min.jobAssigned[j]) {
					Node child = new Node(min.i + 1, j);
					child.jobAssigned = Arrays.copyOf(min.jobAssigned, n);
					child.jobAssigned[j] = true;
					child.parent = min;
					child.pathCost = min.pathCost + costs[min.i+1][j];
					lowerBoundCost(costs, child);
					q.add(child);
				}
			}
		}
		return null;
	}
	
	// calculate the lower bound cost from current node
	static void lowerBoundCost(int[][] costs, Node node) {
		boolean[] assigned = new boolean[costs.length];
		node.cost = node.pathCost;
		for (int i = node.i + 1; i < costs.length; i++) {
			int minIdx = -1;
			for (int j = 0; j < costs.length; j++) {
				if (!assigned[j] && !node.jobAssigned[j] && 
						(minIdx == -1 || costs[i][minIdx] > costs[i][j]))
					minIdx = j;
			}
			node.cost += costs[i][minIdx];
			assigned[minIdx] = true;
		}
	}
	
	static int[] formatReturn(Node node) {
		int[] assigned = new int[node.jobAssigned.length];
		for (int i = assigned.length - 1; i >= 0; i--) {
			assigned[i] = node.j;
			node = node.parent;
		}
		return assigned;
	}
	
	static class Node {
		int i, j;
		int cost, pathCost;
		boolean[] jobAssigned;
		Node parent;
		Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) {
		int[][] costs = new int[][] {
	        {9, 2, 7, 8},
	        {6, 4, 3, 7},
	        {5, 8, 1, 8},
	        {7, 6, 9, 4},};
		int[] r = jobAssignment(costs);
		int[] exp = new int[] {1, 0, 2, 3};
		System.out.println(Arrays.equals(r, exp));
		 
		costs = new int[][] {
			{82, 83, 69, 92},
	        {77, 37, 49, 92},
	        {11, 69,  5, 86},
	        { 8,  9, 98, 23},};
		r = jobAssignment(costs);
		exp = new int[] {2, 1, 0, 3};
		System.out.println(Arrays.equals(r, exp));
		 
		costs = new int[][] {
			{82, 83, 69, 90},
	        {77, 37, 49, 92},
	        {11, 69,  5, 86},
	        {88, 79,  1, 23},};
		r = jobAssignment(costs);
		exp = new int[] {3, 1, 0, 2};
		System.out.println(Arrays.equals(r, exp));
	}

}
