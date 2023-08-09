package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * https://www.acmicpc.net/problem/11286
 * @author SSAFY
 *
 */
public class Main_11286_박정인 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> {
			int absN1 = n1 < 0 ? -1 * n1 : n1;
			int absN2 = n2 < 0 ? -1 * n2 : n2;
			if (absN1 != absN2) {
				return Integer.compare(absN1, absN2);
			}
			return Integer.compare(n1, n2);
		});
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if (n == 0) {
				if (pq.isEmpty()) {
					sb.append(0);
				} else {
					sb.append(pq.poll());
				}
				sb.append("\n");
				continue;
			}
			
			pq.offer(n);
		}
		
		System.out.println(sb);
	}
}
