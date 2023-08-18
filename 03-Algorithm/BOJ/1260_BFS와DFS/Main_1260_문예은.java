package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_문예은 {
	static int[][] graph;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 정점수
		int M = Integer.parseInt(st.nextToken()); // 간선수
		int V = Integer.parseInt(st.nextToken()); // 시작 정점
		
		graph = new int[N+1][N+1]; // 인덱스0은 사용하지 않고, 정점 1번부터 사용
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 연결 노드를 양방향으로 1로 세팅
			graph[a][b] = 1;
			graph[b][a] = 1; 
		}
		visited = new boolean[N+1]; // 정점 1번부터 사용
		dfs(V);
		sb.append("\n");
		
		visited = new boolean[N+1]; // 정점 1번부터 사용
		bfs(V);
		
		System.out.println(sb.toString());
	}

	private static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(v); // 방문정점 큐에 넣기
		visited[v] = true;
		sb.append(v + " ");
		
		while (!queue.isEmpty()) { // 모든 인접정점까지 다 탐색했을 때 종료
			int n = queue.poll(); // 정점 꺼내기
			for(int i = 1; i < graph.length; i++) { // 전체 노드 탐색
				if(graph[n][i] == 1 && visited[i] == false) { // 서로 연결되어 있고, 인접정점에 아직 방문 전이면
					visited[i] = true;
					sb.append(i + " ");
					queue.offer(i); // 인접정점 큐에 넣기
				}
			}
		}
	}

	private static void dfs(int v) {
		visited[v] = true; // 방문정점
		sb.append(v + " ");
		
		if(v == graph.length) return; // 마지막 정점까지 방문했을 때 종료
		
		for (int i = 1; i < graph.length; i++) { // 정점 1번부터 사용
			if(graph[v][i]==1 && visited[i]==false) { // 서로 연결되어 있고, 인접정점에 아직 방문 전이면
				dfs(i); // 연결노드 찾으면 재귀 호출
			}
		}
	}
}