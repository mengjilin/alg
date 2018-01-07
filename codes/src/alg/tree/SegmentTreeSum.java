package alg.tree;

/*
 * build: Time(n), Space(n)
 * query: Time(logn), Space(logn)
 * update: Time(logn), Space(logn)
 */
public class SegmentTreeSum {

	int[] data;
	int[] segtree;
	
	SegmentTreeSum(int[] a) {
		data = a;
		int n = 2 * (int)Math.pow(2, Math.ceil(Math.log(a.length)/Math.log(2))) - 1;
		segtree = new int[n];
		build(0, 0, a.length-1);
	}
	
	/*
	 * build the segment tree from node tree[nd] with its range[ndi..ndj]
	 */
	int build(int nd, int ndi, int ndj) {
		if (ndi == ndj) return segtree[nd] = data[ndi]; // leaf
		int mid = ndi + (ndj-ndi)/2;
		return segtree[nd] = build(2*nd+1, ndi, mid) + build(2*nd+2, mid+1, ndj);
	}
	
	/*
	 * query the sum in original data[i..j] from node tree[nd] with its range[ndi..ndj]
	 */
	int sum(int nd, int ndi, int ndj, int i, int j) {
		if (j < ndi || ndj < i) return 0; // no overlap
		if (i <= ndi && ndj <= j) return segtree[nd]; // fully cover
		int mid = ndi + (ndj-ndi)/2;
		return sum(2*nd+1, ndi, mid, i, j) + sum(2*nd+2, mid+1, ndj, i, j);
	}
	
	int sum(int i, int j) {
		return sum(0, 0, data.length-1, i, j);
	}
	
	void update(int nd, int ndi, int ndj, int i, int diff) {
		if (i < ndi || ndj < i) return; // no overlap
		segtree[nd] += diff;
		if (ndi != ndj) {
			int mid = ndi + (ndj-ndi)/2;
			update(2*nd+1, ndi, mid, i, diff);
			update(2*nd+2, mid+1, ndj, i, diff);
		}
	}
	
	void update(int qi, int newValue) {
		update(0, 0, data.length-1, qi, newValue - data[qi]);
	}

	public static void main(String[] args) {
		int[] a = {1, 13, 5, 7, 9, 11};
		SegmentTreeSum tree = new SegmentTreeSum(a);
		System.out.println(25 == tree.sum(1, 3));
		tree.update(1, 3);
		System.out.println(15 == tree.sum(1, 3));
	}

}
