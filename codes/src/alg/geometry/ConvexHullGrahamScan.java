package alg.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

import alg.Test;

/*
 * Time(nlogn), Space(n)
 * Graham Scan to construct the convex hull
 * 1. select the bottom-most point as p0
 * 2. sort the other points based on its polar angle from p0, then only the farest is left for points with same angle.
 * 3. put p0, p1, p2 into a stack, loop the remained points from p3 to pm and p0,
 * 4. extract top until (2ndTop, top, pi) is in counter clockwise (left turn), put pi into stack.
 */
public class ConvexHullGrahamScan {
	
	static int[][] convexHull(int[][] points) {
		Point[] ps = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			ps[i] = new Point(points[i][0], points[i][1]);
		}
		
		Point[] r = grahamScan(ps);
		
		// return an array starting from left most in counter clockwise order
		int[][] ret = new int[r.length][2];
		for (int i = 0; i < r.length; i++) {
			ret[i] = new int[] {r[i].x, r[i].y};
		}
		
		return ret;
	}
	
	/*
	 * sort the points based on its polar angle from p0
	 */
	static Point[] sortAndDedupe(Point[] points) {
		Comparator<Point> comp = (a, b) -> {
			Point p = new Point(a.x - points[0].x, a.y - points[0].y);
			Point q = new Point(b.x - points[0].x, b.y - points[0].y);
			if (GeoCommon.quadrant(p) != GeoCommon.quadrant(q)) return GeoCommon.quadrant(p) - GeoCommon.quadrant(q);
			int r = GeoCommon.orientation(points[0], a, b);
			if (r != 0) return -r;
			return (p.x*p.x + p.y*p.y) - (q.x*q.x + q.y*q.y);
		};
		
		Arrays.sort(points, 1, points.length, comp);
		
		ArrayList<Point> ret = new ArrayList<>();
		ret.add(points[0]);
		for (int i = 1; i < points.length; i++) {
			if (i == points.length-1 || GeoCommon.orientation(points[0], points[i], points[i+1]) != 0)
				ret.add(points[i]);
		}
		return ret.toArray(new Point[ret.size()]);
	}
	
	static Point[] grahamScan(Point[] points) {
		if (points.length < 4) return points;
		
		int lm = 0;
		for (int i = 0; i < points.length; i++) {
			if (points[lm].y > points[i].y || (points[lm].y == points[i].y && points[lm].x > points[i].x))
				lm = i;
		}
		Point temp = points[0];
		points[0] = points[lm];
		points[lm] = temp;
		
		points = sortAndDedupe(points);
		if (points.length < 4) return points;
		
		Stack<Point> st = new Stack<>();
		st.add(points[0]);
		st.add(points[1]);
		st.add(points[2]);
		for (int i = 3; i < points.length; i++) {
			Point a, b, c = points[i];
			do {
				b = st.pop();
				a = st.peek();
			} while (st.size() > 1 && GeoCommon.orientation(a, b, c) <= 0);
			st.push(b);
			st.push(c);
		}
		
		return st.toArray(new Point[st.size()]);
	}
	
	public static void main(String[] args) {
		int[][] points = new int[][]{{0, 3}, {1, 1}, {2, 2}, {4, 4}, {0, 0}, {1, 2}, {3, 1}, {3, 3}};
		int[][] r = convexHull(points);
		int[][] exp = new int[][] {{0, 0}, {3, 1}, {4, 4}, {0, 3}};
		System.out.println(Test.isSame(r, exp));
	}

}
