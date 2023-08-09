package todo.lesson._0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_11286_최지웅 {

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) == Math.abs(o2)) return o1.intValue() - o2.intValue();
				return Integer.compare(Math.abs(o1), Math.abs(o2));
			}
			
		});
		
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
