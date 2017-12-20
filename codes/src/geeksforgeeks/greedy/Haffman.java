package geeksforgeeks.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Haffman {

	public static void main(String[] args) {
		char[] arr = {'a', 'b', 'c', 'd', 'e', 'f'};
	    int[] freq = {5, 9, 12, 13, 16, 45};
	    String[] r = huffman(arr, freq);
	    String[] exp = new String[] {"f:0", "c:100", "d:101", "a:1100", "b:1101", "e:111"};
	    System.out.println(Arrays.compare(r, exp) == 0);
	}
	
	static String[] huffman(char[] arr, int[] freq) {
		if (arr.length == 0) return new String[0];
		if (arr.length == 1) return new String[] { arr[0] + ":0" };
		
		PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.freq - b.freq);
		for (int i = 0; i < arr.length; i++) {
			q.add(new Node(arr[i], freq[i]));
		}		
		while (q.size() >= 2) {
			Node left = q.poll();
			Node right = q.poll();
			Node node = new Node('a', left.freq + right.freq);
			node.lc = left;
			node.rc = right;
			q.add(node);
		}
		
		ArrayList<String> r = new ArrayList<>();
		dfs(q.peek(), "", r);
		return r.toArray(new String[r.size()]);
	}
	
	static void dfs(Node node, String prefix, ArrayList<String> result) {
		if (node.lc == null) {
			result.add(node.c + ":" + prefix);
		} else {
			dfs(node.lc, prefix + '0', result);
			dfs(node.rc, prefix + '1', result);
		}
	}

	static class Node {
		char c;
		int freq;
		Node lc; // left child
		Node rc; // right child
		
		Node(char c, int freq) {
			this.c = c;
			this.freq = freq;
		}
	}
}
