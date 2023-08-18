import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_1260 {
	
	static int N, M, V;
	static boolean[] Visited;
	// Integer 값을 담는 List 배열 (2차원) // 원소가 담길 공간
	static List<Integer>[] Graph = new LinkedList[1001];
	static Queue<Integer> queue = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// N: 정점 개수 / M: 간선 개수 / V: 탐색 시작 정점 번호
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 2차원 리스트 배열에 정점 정보 N개를 담을 리스트 N개 생성(초기화)
		for (int i = 0; i < N + 1; i++) {
			Graph[i] = new LinkedList<>();
		}
		
		// 2차원 리스트 배열에 원소 담기 : 상호 연결된 정점의 정보를 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			Graph[a].add(b);
			Graph[b].add(a);
		}
		
		// 오름차순 정ㄹ려
		for (int i = 1; i <= N; i++) {
			Collections.sort(Graph[i]);
		}
		
		Visited = new boolean[N+1];
		DFS(Graph, V, Visited);
		System.out.println();
		Visited = new boolean[N+1];
		BFS(Graph, V, Visited);
	}
	
	private static void DFS(List<Integer>[] Graph, int V, boolean[] Visited) {
		Visited[V] = true;
		System.out.print(V + " ");
		for (int i : Graph[V]) {
			if (!Visited[i]) {
				DFS(Graph, i, Visited);
			}
		}
	}
	
	private static void BFS(List<Integer>[] Graph, int V, boolean[] Visited) {
		queue.add(V);
		Visited[V] = true;
		while (queue.size() != 0) {
			V = queue.poll();
			System.out.print(V + " ");
			for (int i : Graph[V]) {
				if (!Visited[i]) {
					queue.add(i);
					Visited[i] = true;
				}
			}
		}
	}
}
