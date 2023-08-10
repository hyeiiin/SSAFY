package todo.lesson._0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_11286_최지웅 {

	static class PriorityQueue {

		int size = 0;
		List<Integer> heap = new ArrayList<>();

		final Integer DUMMY = 0;
		final int ROOT = 1;

		public PriorityQueue() {
			heap.add(DUMMY);
		}

		// util
		void swap(int i, int j) {
			int tmp = heap.get(i);
			heap.set(i, heap.get(j));
			heap.set(j, tmp);
		}
		
		boolean comp(int i, int j) {
			int iVal = heap.get(i), jVal = heap.get(j);
			if (Math.abs(iVal) == Math.abs(jVal)) return iVal < jVal;
			else return Math.abs(iVal) < Math.abs(jVal);
		}

		/* tree */
		int parent(int i) {
			return i / 2;
		}

		int left(int i) {
			return 2 * i;
		}

		int right(int i) {
			return 2 * i + 1;
		}

		/* queue - priority queue */
		void offer(int n) {
			heap.add(n);
			size++;

			// heapify
			int idx = size;
			while (idx != ROOT && comp(idx, parent(idx))) {
				swap(idx, parent(idx));
				idx = parent(idx);
			}
		}

		int poll() {
			int ret;
			if (size == 0)
				return DUMMY;
			else if (size == 1) {
				ret = heap.get(ROOT);
				heap.remove(size);
				size--;
			} else {
				swap(ROOT, size);
				ret = heap.get(size);
				heap.remove(size);
				size--;

				// heapify
				int idx = ROOT;
				int childIdx = ROOT;
				while (true) {
					
					if (left(idx) <= size) {
						
						childIdx = idx;
						
						if (comp(left(idx), idx))
							childIdx = left(idx);
						
						if (right(idx) <= size && comp(right(idx), childIdx)) {
							childIdx = right(idx);
						}
						
						if (comp(childIdx, idx)) {
							swap(childIdx, idx);
							idx = childIdx;
							continue;
						}
						
						break;
					} else {
						break;
					}
				}
			}
			return ret;
		}
		
		boolean isEmpty() {
			return size == 0;
		}
		
		@Override
		public String toString() {
			return heap.toString();
		}

	}
	
	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue pq = new PriorityQueue();
		
		int N = Integer.parseInt(br.readLine());
		int x;
		final int EMPTY = 0;
		
		for (int n = 1; n <= N; n++) {
			x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (pq.isEmpty()) {
					System.out.println(EMPTY);
				} else {
					System.out.println(pq.poll());
				}
			} else {
				pq.offer(x);
			}
		}
	}

}
