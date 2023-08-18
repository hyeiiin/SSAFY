package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//DFS와 BFS
public class Main_1260_문혜린 {
	static ArrayList<ArrayList<Integer>> graph; //인접 리스트
	static boolean visited[]; //방문 처리
	static StringBuilder sb;
	
	public static void dfs(int v) { //깊이 우선 탐색
		sb.append(v + " "); //결과 출력
		visited[v] = true; //방문 처리
		
		//v와 연결된 정점 중
		for (int n : graph.get(v)) {
			//v와 연결된 정점 아직 방문 안했을 경우
			if(!visited[n]) {
				dfs(n);
			}
		}
	}
	public static void bfs(int v) { //너비 우선 탐색
		Queue<Integer> q = new LinkedList<>();
		q.add(v); //초기 정점 삽입
		visited[v] = true; //초기 정점 방문
		
		//큐가 비어있지 않을 때까지 반복하여 BFS
		while(!q.isEmpty()) {
			int a = q.remove();
			sb.append(a + " "); //결과 출력
			
			//a와 연결된 정점 중
			for (int n : graph.get(a)) {
				//아직 n 정점 방문 안했을 경우 큐 삽입
				if(!visited[n]) {
					q.add(n);
					visited[n] = true; //방문 처리
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //정점의 개수
		int M = Integer.parseInt(st.nextToken()); //간선의 개수
		int V = Integer.parseInt(st.nextToken()); //탐색 시작 정점 번호
		
		//인접 리스트
		graph = new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		//간선 정보
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		//정렬
		for (int i = 1; i < N+1; i++) {
			Collections.sort(graph.get(i));
		}
		
		//DFS
		visited = new boolean[N+1];
		dfs(V);
		sb.append("\n");
		//BFS
		visited = new boolean[N+1];
		bfs(V);
		
		System.out.println(sb);
	}

}
