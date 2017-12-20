package alg.heap;

import java.util.Arrays;
import java.util.Comparator;

public class MinHeap<E> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public MinHeap(E[] data, Comparator<E> comparator) {
		this.data = Arrays.copyOf(data, data.length);
		dataCount = data.length;
		this.comparator = comparator;
		heapify();
	}
	
//	public void add(E e) {
//		
//	}

	public E peek() {
		if (dataCount == 0) return null;
		return (E)data[0];
	}
	
	public E poll() {
		if (dataCount == 0) return null;
		swap(data, 0, dataCount - 1);
		dataCount--;
		for (int i = 0; i < dataCount; ) {
			int left = i * 2 + 1;
			if (left < dataCount && comparator.compare((E)data[left], (E)data[i]) < 0) {
				swap(data, i, left);
				i = left;
			}
			
			int right = i * 2 + 2;
			if (right < dataCount && comparator.compare((E)data[right], (E)data[i]) < 0) {
			hthttp://yfain.github.io/Java4Kids/#_objectstp://yfain.github.io/Java4Kids/#_running_helloworld_in_idea	swap(data, i, right);
				i = right;
			}
		}
		
		return (E)data[dataCount];
	}
	
	private Object[] data;
	private int dataCount;
	private Comparator<E> comparator;
	
	private void heapify() {
		for (int i = 0; i <= dataCount / 2; i++) {
			int left = i * 2 + 1;
			if (left < dataCount && comparator.compare((E)data[left], (E)data[i]) < 0)
				swap(data, i, left);
			
			int right = i * 2 + 2;
			if (right < dataCount && comparator.compare((E)data[right], (E)data[i]) < 0)
				swap(data, i, right);
		}
	}
	
	private static void swap(Object[] a, int i, int j) {
		Object t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
