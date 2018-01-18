package alg.heap;

import java.util.Arrays;
import java.util.Comparator;

public class MinHeap<T> {
	
	public MinHeap(T[] data) {
		this.data = Arrays.copyOf(data, data.length);
		dataCnt = data.length;
		build();
	}
	
	public MinHeap(T[] data, Comparator<? super T> comparator) {
		this.data = Arrays.copyOf(data, data.length);
		dataCnt = data.length;
		this.comparator = comparator;
		build();
	}

	public int size() {
		return dataCnt;
	}

	public T peek() {
		if (dataCnt == 0) return null;
		return (T)data[0];
	}
	
	public T poll() {
		if (dataCnt == 0) return null;
		swap(data, 0, dataCnt - 1);
		dataCnt--;
		
		for (int i = 0; i < dataCnt; ) {
			int left = i * 2 + 1;
			int right = i * 2 + 2;
			if (left < dataCnt && lessThan(left, i) && (right >= dataCnt || lessThan(left, right))) {
				swap(data, i, left);
				i = left;
			} else if (right < dataCnt && lessThan(right, i)) {
				swap(data, i, right);
				i = right;
			} else {
				break;
			}
		}
		
		return (T)data[dataCnt];
	}
	
	private Object[] data;
	private int dataCnt;
	private Comparator<? super T> comparator;
	
	private void build() {
		for (int i = dataCnt / 2; i >= 0; i--) {
			int left = i * 2 + 1;
			if (left < dataCnt && lessThan(left, i))
				swap(data, i, left);
			
			int right = i * 2 + 2;
			if (right < dataCnt && lessThan(right, i))
				swap(data, i, right);
		}
	}
	
	private boolean lessThan(int i, int j) {
		if (comparator != null)
			return comparator.compare((T)data[i], (T)data[j]) < 0;
		return ((Comparable<? super T>)data[i]).compareTo((T)data[j]) < 0;
	}
	
	private void swap(Object[] a, int i, int j) {
		Object t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		Integer[] data = new Integer[] {2,4,5,6,7,8,9,3,4,5,45,6,5346,54,};
		MinHeap<Integer> heap = new MinHeap<>(data);
		while (heap.size() > 0) {
			System.out.print(heap.poll() + ", ");
		}
	}
}
