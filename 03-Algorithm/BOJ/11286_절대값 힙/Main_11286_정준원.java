package sdf;

import java.io.*;

import java.util.*;

public class Main_11286_정준원 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws IOException {

		int n, m, t;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) > Math.abs(o2)) {
					return Math.abs(o1) - Math.abs(o2);
				}

				else if (Math.abs(o1) == Math.abs(o2)) {
					return o1 - o2;
				}

				return -1;
			}
		});

		for (int i = 0; i < n; i++) {

			int b = Integer.parseInt(br.readLine());

			if (b == 0) {
				if (pq.isEmpty())
					sb.append(0).append('\n');
				else
					sb.append(pq.poll()).append('\n');

			} else
				pq.offer(b);

		}

		System.out.print(sb);

	}

}
