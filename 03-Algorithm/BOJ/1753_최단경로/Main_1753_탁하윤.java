package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1753_탁하윤 {
	static class Node implements Comparable<Node>{
		int vertex;	// 정점 번호
		int weight;	// 간선 비용
		
		Node(int vertex, int weight){
			this.vertex = vertex;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;	// 오름차순 정렬
		}
	}
	
	static final int INF = Integer.MAX_VALUE;	// 갈 수 없는 곳 표시(큰값)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());	// 정점 개수
		int E = Integer.parseInt(st.nextToken());	// 간선 개수
		int S = Integer.parseInt(br.readLine());	// 시작 정점
		
		ArrayList<Node>[] graph = new ArrayList[V+1];	// 연결 그래프
		for (int i = 1; i < V+1; i++) {
			graph[i] = new ArrayList<>();	// 정점 i에 연결되어 있는 정점들 넣을 리스트 초기화
		}
		
		for (int i = 0; i < E; i++) {	// 간선 개수 만큼 돌면서 연결그래프 값 넣기
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, weight));
		}
		
		int[] dist = new int[V+1];	// 최소 비용 저장 배열
		Arrays.fill(dist, INF);	// 최소값 갱신을 위한 큰값 초기화
		
		PriorityQueue<Node> q = new PriorityQueue<>();	//	다익스트라
		boolean[] visited = new boolean[V+1];			// 방문 처리 배열
		q.offer(new Node(S, 0));	// 처음 시작값은 비용이 0
		dist[S] = 0;	// 비용 0
		while(!q.isEmpty()) {	// 큐가 공백 큐가 될 때 까지
			Node cur = q.poll();	// 현재 정점 꺼내기
			
			if(visited[cur.vertex]) {	// 만약 방문한 정점이라면 다음 정점
				continue;
			}
			visited[cur.vertex] = true;	// 현재 정점 방문 처리
			
			for (int i = 0; i < graph[cur.vertex].size(); i++) {	// 현재 정점의 인접 정점들의 사이즈만큼 돌면서
				Node next = graph[cur.vertex].get(i);	// 다음 정점 꺼내기
				
				if(dist[next.vertex] > dist[cur.vertex]+next.weight) {	// 다음 정점의 비용이 현재 정점의 최소비용과 다음 정점의 비용을 더한것보다 크다면
					dist[next.vertex] = dist[cur.vertex]+next.weight;	// 최소값 갱신
					q.offer(new Node(next.vertex, dist[next.vertex]));	// 큐에 다음 정점 넣기
				}
			}
		}
		
		for (int i = 1; i < V + 1; i++) {	// 출력
			if (dist[i] == INF) {	// 비용이 큰값이라면 큰값 출력
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}

	}

}
