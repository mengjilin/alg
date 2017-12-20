package alg.dp;

import java.util.Arrays;

public class Sp_BellmanFord {

	public static void main(String[] args) {
		Graph g = new Graph();
		g.n = 8;
		g.edges = new int[][] {
			{0, 1, -1},
			{0, 2, 4},
			{1, 2, 3},
			{1, 4, 2},
			{3, 2, 5},
			{3, 1, 1},
			{4, 3, -3},
		};
		int[] r = bellmanFord(g);
		int[] expected = {0, -1, 2, -2, 1, INF, INF, INF};
		System.out.println(Arrays.compare(r, expected) == 0);
	}
	
	static int[] bellmanFord(Graph g) {
		int[] dist = new int[g.n];
		for (int i = 0; i < g.n; i++) dist[i] = INF;
		dist[0] = 0;
		int[] prev = new int[g.n];
		for (int i = 0; i < g.n; i++) {
			for (int[] e : g.edges) {
				if (dist[e[1]] > dist[e[0]] + e[2]) {
					dist[e[1]] = dist[e[0]] + e[2];
					prev[e[1]] = e[0];
				}
			}
		}
		return dist;
	}

	static int INF = Integer.MAX_VALUE / 2;
	static class Graph {
		int n; // count of nodes/vertices
		int[][] edges; // edge {src, dst, weight}
	}
}
