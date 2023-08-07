package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1158_박정인 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		List<Integer> list = new ArrayList<>();
		while (!q.isEmpty()) {
			for (int i = 0; i < K - 1; i++) {
				int n = q.poll();
				q.offer(n);
			}
			list.add(q.poll());
		}
		
		sb.append("<");
		for (int n : list) {
			sb.append(n).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		
		sb.append(">");
		System.out.println(sb);
	}	
}
