package alg.dp;

import java.util.*;

public class Dijkstra {

	public static void main(String[] args) {
		Graph g = new Graph();
		g.n = 9;
		g.edges = new Edge[g.n];
		g.addTwoEdges(0, 1, 4).addTwoEdges(0, 7, 8);  
		g.addTwoEdges(1, 2, 8).addTwoEdges(1, 7, 11);
		g.addTwoEdges(2, 3, 7).addTwoEdges(2, 8, 2).addTwoEdges(2, 5, 4);
		g.addTwoEdges(3, 4, 9).addTwoEdges(3, 5, 14);
		g.addTwoEdges(4, 5, 10);
		g.addTwoEdges(5, 6, 2);
		g.addTwoEdges(6, 7, 1).addTwoEdges(6, 8, 6);
		g.addTwoEdges(7, 8, 7);
		int[] r = dijkstra(g);
		int[] expected = new int[] {0, 4, 12, 19, 21, 11, 9, 8, 14};
		System.out.println(Arrays.compare(r, expected) == 0);
	}
	
	static int[] dijkstra(Graph g) {
		int[] dist = new int[g.n];
		for (int i = 1; i < g.n; i++) dist[i] = INF;
		
		TreeSet<HeapNode> q = new TreeSet<>((a, b) -> a.dist == b.dist ? a.v - b.v : a.dist - b.dist);
		//PriorityQueue<HeapNode> q = new PriorityQueue<>(g.n, (a, b) -> Integer.compare(a.dist, b.dist));
		q.add(new HeapNode(0, 0));
		for (int i = 1; i < g.n; i++) {
			q.add(new HeapNode(i, INF));
		}
		
		while (!q.isEmpty()) {
			HeapNode node = q.pollFirst();
			for (Edge e = g.edges[node.v]; e != null; e = e.next) {
				if (dist[e.v] > dist[node.v] + e.w) {
					HeapNode tmp = new HeapNode(e.v, dist[e.v]);
					dist[e.v] = dist[node.v] + e.w;
					boolean test = q.remove(tmp);
					tmp.dist = dist[e.v];
					q.add(tmp);
				}
			}
		}
		
		return dist;
	}

	static int INF = Integer.MAX_VALUE / 2;
	static class Graph {
		int n; // count of nodes/vertices
		Edge[] edges; // edges using adjacent list 
		Graph addTwoEdges(int v1, int v2, int w) {
			Edge e1 = new Edge(v2, w);
			e1.next = edges[v1];
			edges[v1] = e1;
			
			Edge e2 = new Edge(v1, w);
			e2.next = edges[v2];
			edges[v2] = e2;
			return this;
		}
	}
	
	static class Edge {
		int v; // destination vertex
		int w; // weight/distance
		Edge next;
		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	static class HeapNode {
		int v;
		int dist;
		HeapNode(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}
	}
}
