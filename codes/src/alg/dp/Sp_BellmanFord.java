package alg.dp;

import java.util.Arrays;

/*
 * Time(VE), Space(V), can detect negative cycles
 * for each vertex, loop for all edges, 'relax' each vertex
 */
public class Sp_BellmanFord {
	
	static int[] bellmanFord(int n, int[][] edges) {
		int[] dist = new int[n];
		for (int i = 0; i < n; i++) dist[i] = INF;
		dist[0] = 0;
		int[] prev = new int[n];
		for (int i = 0; i < n; i++) {
			for (int[] e : edges) {
				if (dist[e[1]] > dist[e[0]] + e[2]) {
					dist[e[1]] = dist[e[0]] + e[2];
					prev[e[1]] = e[0];
				}
			}
		}
		return dist;
	}

	static int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) {
		int[][] edges = new int[][] {
			{0, 1, -1},
			{0, 2, 4},
			{1, 2, 3},
			{1, 4, 2},
			{3, 2, 5},
			{3, 1, 1},
			{4, 3, -3},
		};
		int[] r = bellmanFord(8, edges);
		int[] expected = {0, -1, 2, -2, 1, INF, INF, INF};
		System.out.println(Arrays.equals(r, expected));
	}
}
