package geeksforgeeks.geometry;

import alg.geometry.GeoCommon;
import alg.geometry.Point;

public class PointInsidePolygon {

	/*
	 * use a horizontal ray, count the intersection with all sides of polygon
	 * corner case: only count if points of a side is under the ray 
	 */
	static boolean isPointInsidePolygon(int[][] polygon, int[] point) {
		Point[] ps = new Point[polygon.length];
		for (int i = 0; i < polygon.length; i++) {
			ps[i] = new Point(polygon[i][0], polygon[i][1]);
		}
		Point p = new Point(point[0], point[1]);
		Point extPoint = new Point(INF, p.y);
		
		int intersectCount = 0;
		for (int i = 0; i < ps.length; i++) {
			int j = (i+1) % ps.length;
			if (GeoCommon.isOnSegment(ps[i], ps[j], p)) return true;
			
			if (GeoCommon.isSegmentIntersect(ps[i], ps[j], p, extPoint) &&
			   (GeoCommon.orientation(p,  extPoint, ps[i]) == -1 || GeoCommon.orientation(p,  extPoint, ps[j]) == -1))
				intersectCount++;
		}
		
		return intersectCount%2 == 1;
	}
	
	static int INF = 10000;
	
	public static void main(String[] args) {
		int[][] polygon = new int[][]{{0, 0}, {10, 0}, {10, 10}, {0, 10}};
		System.out.println(false == isPointInsidePolygon(polygon, new int[] {20, 20}));
		System.out.println(true == isPointInsidePolygon(polygon, new int[] {5, 5}));
		
		polygon = new int[][]{{0, 0}, {5, 5}, {5, 0}};
		System.out.println(true == isPointInsidePolygon(polygon, new int[] {3, 3}));
		System.out.println(true == isPointInsidePolygon(polygon, new int[] {5, 1}));
		System.out.println(false == isPointInsidePolygon(polygon, new int[] {8, 1}));
		
		polygon = new int[][]{{0, 0}, {10, 0}, {10, 10}, {0, 10}};
		System.out.println(false == isPointInsidePolygon(polygon, new int[] {-1, 10}));
	}

}
