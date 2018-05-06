package alg.geometry;

public class GeoCommon {
	
	/*
	 * to compare the slope of line (a, b)[(b.y-a.y)/(b.x-a.x)] to the slope of line (b, c)[(c.y-b.y)/(c.x-b.x)]
	 * return:
	 * -1: if (a, b, c) is in clockwise or right turn
	 * 0 : if (a, b, c) is in collinear
	 * 1 : if (a, b, c) is in counter clockwise or left turn
	 */
	public static int orientation(Point a, Point b, Point c) {
		int diff = (c.y-b.y)*(b.x-a.x) - (b.y-a.y)*(c.x-b.x);
		return diff < 0 ? -1 : diff == 0 ? 0 : 1;
	}
	
	/*
	 * quadrant of a point 
	 */
	public static int quadrant(Point p) {
		if (p.x >= 0 && p.y >= 0) return 1;
		if (p.x <= 0 && p.y >= 0) return 2;
		if (p.x <= 0 && p.y <= 0) return 3;
		return 4;
	}
	
	/*
	 * if c is on segment (a, b)
	 */
	public static boolean isOnSegment(Point a, Point b, Point c) {
		return a.x <= c.x && c.x <= b.x &&
			   a.y <= c.y && c.y <= b.y &&
				orientation(a, b, c) == 0;
	}
	
	/*
	 * if segment (a1, a2) intersect with (b1, b2)
	 */
	public static boolean isSegmentIntersect(Point a1, Point a2, Point b1, Point b2) {
		// case 0: general intersect
		if (orientation(a1, a2, b1) != orientation(a1, a2, b2) &&
			orientation(b1, b2, a1) != orientation(b1, b2, a2))
			return true;
		
		// case 1: they are collinear
		if (isOnSegment(a1, a2, b1) || isOnSegment(a1, a2, b2) ||
				isOnSegment(b1, b2, a1) || isOnSegment(b1, b2, a2))
			return true;
		
		return false;
	}
	
	/*
	 * distance formula from point c to line (a, b)
	 * https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line
	 */
	public static double distance(Point a, Point b, Point c) {
		return Math.abs((b.y-a.y)*c.x - (b.x-a.x)*c.y + b.x*a.y - b.y*a.x) / 
				Math.sqrt((b.x-a.x)*(b.x-a.x) + (b.y-a.y)*(b.y-a.y));
	}

	public static void main(String[] args) {
		boolean r;
		r = isSegmentIntersect(new Point(1, 1), new Point(10, 1), new Point(1, 2), new Point(10, 2));
		System.out.println(r == false);

		r = isSegmentIntersect(new Point(10, 0), new Point(0, 10), new Point(0, 0), new Point(10, 10));
		System.out.println(r == true);

		r = isSegmentIntersect(new Point(-5, -5), new Point(0, 0), new Point(1, 1), new Point(10, 10));
		System.out.println(r == false);
	}

}
