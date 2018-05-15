package geeksforgeeks.geometry;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;

import alg.Test;
import alg.geometry.GeoCommon;
import alg.geometry.Point;

/*
 * 1. sweep line from left to right after sorting points based on its x value.
 * for beginning point, add this line to BST and check if it intersects with its pred and succ;
 * for ending point, remove this line from BST and check if its pred and succ intersect;
 */
public class SegmentsIntersect {
	
	static int[][] segmentsIntersect(int[][] lines) {
		PointInfo[] ps = new PointInfo[lines.length*2];
		for (int i = 0; i < lines.length; i++) {
			ps[i*2]   = new PointInfo(lines[i][0], lines[i][1], i, true);
			ps[i*2+1] = new PointInfo(lines[i][2], lines[i][3], i, false);
		}
		Arrays.sort(ps, (a, b) -> a.x - b.x);		
		TreeSet<PointInfo> activeLines = new TreeSet<>((a, b) -> a.y != b.y ? a.y-b.y : a.x-b.x);
		
		LinkedList<int[]> r = new LinkedList<>();
		for (PointInfo p : ps) {
			if (p.isFirst) {
				activeLines.add(p);
				PointInfo pPeer = new PointInfo(lines[p.line][2], lines[p.line][3], p.line, true);
				
				PointInfo pred = activeLines.lower(p);
				if (pred != null && GeoCommon.isSegmentIntersect(p, pPeer, pred, new Point(lines[pred.line][2], lines[pred.line][3])))
					r.add(new int[] {p.line, pred.line});

				PointInfo succ = activeLines.higher(p);
				if (succ != null && GeoCommon.isSegmentIntersect(p, pPeer, succ, new Point(lines[succ.line][2], lines[succ.line][3])))
					r.add(new int[] {p.line, succ.line});
			} else {
				PointInfo pPeer = new PointInfo(lines[p.line][0], lines[p.line][1], p.line, false);
				PointInfo pred = activeLines.lower(pPeer);
				PointInfo succ = activeLines.higher(pPeer);
				if (pred != null && succ != null && GeoCommon.isSegmentIntersect(
						pred, new Point(lines[pred.line][2], lines[pred.line][3]), 
						succ, new Point(lines[succ.line][2], lines[succ.line][3])))
					r.add(new int[] {pred.line, succ.line});
				activeLines.remove(pPeer);
			}
		}
		
		TreeSet<int[]> ret = new TreeSet<>((a, b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
		for (int[] a : r) ret.add(new int[] {Math.min(a[0], a[1]), Math.max(a[0], a[1])});
		
		return ret.toArray(new int[ret.size()][2]);
	}
	
	static class PointInfo extends Point {
		int line;
		boolean isFirst;
		PointInfo(int x, int y, int line, boolean isFirst) {
			super(x, y);
			this.line = line;
			this.isFirst = isFirst;
		}
	}

	public static void main(String[] args) {
		int[][] lines = new int[][]{{1,3,3,3}, {2,5,12,1}, {3,1,12,3}, {6,4,10,4}, {7,1,9,1}, {0,4,8,3}};
		int[][] r = segmentsIntersect(lines);
		int[][] exp = new int[][]{{1,2}, {1,5}};
		System.out.println(Arrays.deepEquals(r, exp));
	}

}
