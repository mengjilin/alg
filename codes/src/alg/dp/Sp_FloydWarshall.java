package alg.dp;

import java.util.Arrays;

/*
 * Time(V^3), Space(V^2), can detect negative cycles
 * 3 loops for each vertex, 'relax' each vertex
 */
public class Sp_FloydWarshall {

	static int INF = Integer.MAX_VALUE / 2;
	static int[][] floydWarshall(int[][] graph) {
		int n = graph.length;
		int[][] dist = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				dist[i][j] = graph[i][j];

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		
		return dist;
	}

	public static void main(String[] args) {
		int[][] graph = new int[][] { 
			{0,   5,  INF, 10},
            {INF,  0,  3,  INF},
            {INF, INF, 0,   1},
            {INF, INF, INF, 0} };
        int[][] r = floydWarshall(graph);
		int[][] expected = new int[][] { 
			{0,   5,   8,   9},
            {INF,  0,  3,   4},
            {INF, INF, 0,   1},
            {INF, INF, INF, 0} };
        boolean result = true;
        for (int i = 0; i < r.length; i++)
        	result &= Arrays.equals(r[i], expected[i]);
        System.out.println(result);
	}
}
