import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 2252
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M; // 학생 수, 키를 비교환 횟수
	static int[] cnt; // 위상 정렬 -> 진입 차수 관리 배열
//	static int[][] edge; // 간선 정보를 저장
	static ArrayList<List<Integer>> edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 1 <= 학생수 <= 3_2000
		M = Integer.parseInt(st.nextToken()); // 1 <= 비교 횟수 <= 10_0000

		// 위상 정렬 -> 각 정점(학생)의 진입 차수 관리 배열
		cnt = new int[N + 1]; // 학생 번호를 1번부터 관리

		// 간선 정보 입력 -> 인접 행렬 사용시 MLE, 인접 리스트 사용해야함
//		edge = new int[N + 1][N + 1];
		edge = new ArrayList<>();

		for (int i = 0; i < N + 1; i++) {
			edge.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// 인접 행렬로 간선 정보를 저장
//			edge[from][to] = 1;

			// 인접 리스트로 간선 정보를 저장
			edge.get(from).add(to);
//			edge.get(1).get(2);

			cnt[to]++; // 진입 차수 할당
		}

//		System.out.println(edge.toString());
//		System.out.println(Arrays.toString(cnt));

		sb = new StringBuilder();

		topologySort();
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);

	} // Main

	static void topologySort() {
		Deque<Integer> qu = new ArrayDeque<>();

		for (int i = 1; i < N + 1; i++) {
			// 1. 진입 차수가 0인 정점을 큐에 삽입
			if (cnt[i] == 0) {
				qu.offer(i);
			}
		}

		// 2. 모든 정점을 순회하기 전에 Queue가 비었다 = 사이클 발생 = 위상 정렬 불가
		while (!qu.isEmpty()) {

			// 진입 차수가 0인 것들 중 제일 앞쪽 원소를 꺼냄.
			int from = qu.poll();
			sb.append(from).append(" ");

			for (int to = 0; to < edge.get(from).size(); to++) {
				int temp = edge.get(from).get(to);
				
				// 새롭게 진입 차수가 0이 되면, 큐에 추가
				if (--cnt[temp] == 0) {
					qu.offer(temp);
				}
			}
		}

//		System.out.println("===================");
//		System.out.println(Arrays.toString(cnt));

	}

}
