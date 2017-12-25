package geeksforgeeks.greedy;

import java.util.PriorityQueue;

public class RearrangeString {

	public static void main(String[] args) {
		String r = rearrange("aaaabc");
		System.out.print("abaca".equals(r));
	}

	static String rearrange(String s) {
		String r = "";
		int[] counts = new int[26];
		for (int i = 0; i < s.length(); i++) {
			counts[s.charAt(i)-'a']++;
		}
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cnt == b.cnt ? a.c - b.c : b.cnt - a.cnt);
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0) pq.add(new Node((char)('a' + i), counts[i]));
		}
		
		Node prev = new Node('a', -1);
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			r += curr.c;
			curr.cnt--;
			if (prev.cnt > 0) pq.add(prev);
			prev = curr;
		}
		if (r.length() != s.length()) return null;
		return r;
	}
	
	static class Node {
		char c;
		int cnt;
		Node(char c, int cnt) {
			this.c = c;
			this.cnt = cnt;
		}
	}
}
