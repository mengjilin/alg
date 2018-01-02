package geeksforgeeks.dc;

public class LargestRectangularArea {
	
	int[] data;
	int[] segtree;
	
	public LargestRectangularArea(int[] a) {
		data = a;
		int n = 2 * (int)Math.pow(2, Math.ceil(Math.log(a.length) / Math.log(2))) - 1;
		segtree = new int[n];
		build(0, 0, data.length-1);
	}
	
	int build(int nd, int ndi, int ndj) {
		if (ndi == ndj) return segtree[nd] = ndi;
		int mid = (ndi+ndj) / 2;
		int il = build(2*nd+1, ndi, mid);
		int ir = build(2*nd+2, mid+1, ndj);
		return segtree[nd] = data[il] <= data[ir] ? il : ir;
	}
	
	int minIndex(int nd, int ndi, int ndj, int i, int j) {
		if (j < ndi || ndj < i) return -1; // no overlap
		if (i <= ndi && ndj <= j) return segtree[nd]; // fully cover
		int mid = (ndi+ndj) / 2;
		int il = minIndex(2*nd+1, ndi, mid, i, j);
		int ir = minIndex(2*nd+2, mid+1, ndj, i, j);
		if (il == -1) return ir;
		if (ir == -1) return il;
		return data[il] <= data[ir] ? il : ir;
	}
	
	int maxArea(int i, int j) {
		if (i > j) return -1;
		if (i == j) return data[i];
		int mid = minIndex(0, 0, data.length-1, i, j);
		int maxl = maxArea(i, mid-1);
		int maxr = maxArea(mid+1, j);
		return Math.max(Math.max(maxl, maxr), data[mid]*(j-i+1));
	}

	static int largestRectangularArea(int[] a) {
		LargestRectangularArea m = new LargestRectangularArea(a);
		return m.maxArea(0, a.length-1);
	}
	
	public static void main(String[] args) {
		int[] bars = {6, 1, 5, 4, 5, 2, 6};
		System.out.println(12 == largestRectangularArea(bars));
	}

}
