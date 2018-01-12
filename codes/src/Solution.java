

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
    	test();
    	//hackerrank.MagicSquare.main1(args);
    	//alg.Kmp.main(args);
    	//geeksforgeeks.array.CountEmployees.main(args);
    	
    }
    
    static void test() {
    	PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.i - b.i);
    	q.add(new Node(1, 2));
    	q.add(new Node(1, 3));
    	System.out.println(q.size());
    }
    
    static class Node implements Cloneable {
    	int i, j;
    	Node (int i, int j) {
    		this.i = i;
    		this.j = j;
    	}
    	Node(Node other) {
    		i = other.i;
    		j = other.j;
    	}
    	public Node clone() {
    		return new Node(i, j);
    	}
    }
}
