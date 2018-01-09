package alg.geometry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

import alg.Test;

/*
 * Time(nlogn), Space(n)
 * Divide and Conquer
 * sort the points based on their x value,
 * divide all points into left convex and right convex, 
 * then merge them using upper/lower tangents
 */
public class ConvexHull_dc {
	
	static Point[] dc(Point[] points) {
		if (points.length < 6) return bruteHull(points);
		
		int mid = points.length / 2;
		Point[] left = new Point[mid];
		for (int i = 0; i < left.length; i++)
			left[i] = points[i];
		Point[] right = new Point[points.length - left.length];
		for (int i = 0; i < right.length; i++)
			right[i] = points[i+mid];
		
		Point[] leftHull = dc(left);
		Point[] rightHull = dc(right);
		return merge(leftHull, rightHull);
	}
	
	/*
	 * merge two polygons using upper/lower tangents.
	 * the points in a/b are in counter clockwise order.
	 * start from the line with rightmost point of a and leftmost point of b, then loop
	 * move the point in counter clockwise order in a until the line does not intersect with a;
	 * move the point in         clockwise order in b until the line does not intersect with b;
	 */
	static Point[] merge(Point[] a, Point[] b) {
		int arm = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[arm].x < a[i].x)
				arm = i;
		}
		
		int blm = 0;
		for (int i = 0; i < b.length; i++) {
			if (b[blm].x > b[i].x)
				blm = i;
		}
		
		// finding the upper tangent
		int i, j, next;
		boolean done = false;
		for (i = arm, j = blm; !done; ) {
			done = true;
			// move the point in a if next point if in clockwise order
			while (GeoCommon.orientation(b[j], a[i], a[next = (i+1)%a.length]) <= 0) 
				i = next;
			// move the point in b if next point if in counter clockwise order
			while (GeoCommon.orientation(a[i], b[j], b[next = (j+b.length-1)%b.length]) >= 0) {
				j = next;
				done = false;
			}
		}
		int uppera = i, upperb = j;
		
		// finding the lower tangent
		done = false;
		for (i = arm, j = blm; !done; ) {
			done = true;
			// move the point in a if next point if in counter clockwise order
			while (GeoCommon.orientation(b[j], a[i], a[next = (i+1)%a.length]) >= 0) 
				i = next;
			// move the point in b if next point if in clockwise order
			while (GeoCommon.orientation(a[i], b[j], b[next = (j+b.length-1)%b.length]) <= 0) {
				j = next;
				done = false;
			}
		}
		int lowera = i, lowerb = j;
		
		// merge the points in counter clockwise order
		LinkedList<Point> r = new LinkedList<>();
		r.add(a[uppera]);
		for (i = uppera; i != lowera; ) {
			i = (i+1)%a.length;
			r.add(a[i]);
		}
		r.add(b[lowerb]);
		for (j = lowerb; j != upperb; ) {
			j = (j+1)%b.length;
			r.add(b[j]);
		}
		
		return r.toArray(new Point[r.size()]);
	}
	
	static int[][] convexHull(int[][] points) {
		Point[] ps = new Point[points.length];
		for (int i = 0; i < points.length; i++)
			ps[i] = new Point(points[i][0], points[i][1]);
		Arrays.sort(ps, (a, b) -> a.x - b.x); // sort the points in order to divide into left and right
		Point[] r = dc(ps);
		
		// return an array starting from left most in counter clockwise order
		int[][] ret = new int[r.length][2];
		int lm = 0; 
		for (int i = 1; i < r.length; i++) {
			if (r[lm].x > r[i].x || (r[lm].x == r[i].x && r[lm].y > r[i].y))
				lm = i;
		}
		for (int i = 0; i < r.length; i++) {
			ret[i] = new int[] {r[(i+lm)%r.length].x, r[(i+lm)%r.length].y};
		}
		
		return ret;
	}
	
	/*
	 * sort the points forming a convex polygon in [counter] clockwise order
	 * 1. find a point inside the convex polygon(centroid)
	 * 		a point with average x/y is inside the convex, 
	 * 		to avoid floating-point value, their x/y can be increase n times first.
	 * 2. compare quadrant first, then their slope
	 */
	static void sort(Point[] points, boolean inClockwise) {
		Point mid = new Point(0, 0);
		for (Point p : points) {
			mid.x += p.x;
			mid.y += p.y;
			p.x *= points.length; // enlarge n times to avoid floating-point value
			p.y *= points.length; // enlarge n times to avoid floating-point value
		}
		
		int sign = inClockwise ? -1 : 1;
		Comparator<Point> comp = (a, b) -> {
			Point p = new Point(a.x - mid.x, a.y - mid.y);
			Point q = new Point(b.x - mid.x, b.y - mid.y);
			if (GeoCommon.quadrant(p) != GeoCommon.quadrant(q)) return (GeoCommon.quadrant(p) - GeoCommon.quadrant(q)) * sign;
			return (p.y*q.x - q.y*p.x) * sign;
		};
		
		Arrays.sort(points, comp);
		
		// restore their x/y
		for (Point p : points) {
			p.x /= points.length;
			p.y /= points.length;
		}
	}
	
	static Point[] bruteHull(Point[] points) {
		HashSet<Point> r = new HashSet<>(); // use Set to avoid duplicates 
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				int side = -2;
				for (int k = 0; k < points.length; k++) {
					if (k == i || k == j) continue;
					int sideK = GeoCommon.orientation(points[i], points[j], points[k]);
					if (side == -2) side = sideK;
					else if (side != sideK) {
						side = 2;
						break;
					}
				}
				if (side != 2) {
					r.add(points[i]);
					r.add(points[j]);
				}
			}
		}

		Point[] ret = r.toArray(new Point[r.size()]);
		sort(ret, false);
		return ret;
	}

	public static void main(String[] args) {
		int[][] points = new int[][]{{0, 0}, {0, 4}, {-4, 0}, {5, 0}, {0, -6}, {1, 0}};
		int[][] r = convexHull(points);
		int[][] exp = new int[][] {{-4, 0}, {0, -6}, {5, 0}, {0, 4}};
		System.out.println(Test.isSame(r, exp));
		
		points = new int[][]{{0,0}, {1,-4}, {-1,-5}, {-5,-3}, {-3,-1}, {-1,-3}, {-2,-2}, {-1,-1}, {-2,-1}, {-1,1}};
		r = convexHull(points);
		exp = new int[][] {{-5,-3}, {-1,-5}, {1,-4}, {0,0}, {-1,1}};
		System.out.println(Test.isSame(r, exp));
	}

}
