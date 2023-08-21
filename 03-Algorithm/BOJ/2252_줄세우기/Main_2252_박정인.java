package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2252
 * 스페셜 저지: 답 여러개 가능
 * @author SSAFY
 *
 */
public class Main_2252_박정인 {
	static int N, M;
	static int[] indegrees;
	static List<Integer>[] list;
	static List<Integer> result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];
		indegrees = new int[N + 1];
		result = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}				

		
		// N명의 학생들 키 순서대로 정렬하고자 하지만 일부 학생의 키만 선후관계를 알고있다 
		// 선후관계를 지키면서 가능한 정렬(여러경우 가능) >> 위상정렬
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			indegrees[to]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (indegrees[i] == 0) {				
				q.offer(i);
			}
		}
		
		while (!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append(" ");
			for (int next : list[now]) {
				indegrees[next]--;				
				if (indegrees[next] == 0) {
					q.offer(next);				
				}
			}
		}
		
		System.out.println(sb);
	}
}
