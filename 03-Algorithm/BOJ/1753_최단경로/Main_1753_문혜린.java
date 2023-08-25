package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//최단경로
class Node {
	int vertex; //인접 정점
	int weight; //가중치
	
	public Node(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
}
public class Main_1753_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); //정점의 개수
		int E = Integer.parseInt(st.nextToken()); //간선의 개수
		int start = Integer.parseInt(br.readLine()); //시작 정점의 번호
		
		ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
		int[] distance = new int[V+1]; //시작점에서 자신까지 오는 최단 거리
		boolean[] visited = new boolean[V+1]; //정점 방문 배열
		
		for (int i = 0; i < V+1; i++) {
			graph.add(new ArrayList<Node>());
			distance[i] = Integer.MAX_VALUE; //INF 초기화
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); //from
			int v = Integer.parseInt(st.nextToken()); //to
			int w = Integer.parseInt(st.nextToken()); //weight
			graph.get(u).add(new Node(v, w));
		}
		
		//가중치 작은 순서대로 정점 방문하기 위한 우선순위 큐
		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) { //가중치 작은 순으로 오름차순
				return o1.weight-o2.weight;
			}
		});
		
		//시작 정점 초기화
		q.add(new Node(start, 0));
		distance[start] = 0;
		visited[start] = true;
		
		while(!q.isEmpty()) {
			//가장 가중치 작은 정점 탐색
			Node now = q.remove();
			
			//정점 아직 방문 안했을 경우 방문 처리
			if(!visited[now.vertex]) {
				visited[now.vertex] = true;
			}
			//인접 정점 중
			for (Node next : graph.get(now.vertex)) {
				//아직 방문하지 않았고 현재 노드를 거쳐서 다음 노드로 가는 거리가 짧은 경우
				if(!visited[next.vertex] && distance[next.vertex]>now.weight+next.weight) {
					//거리 갱신
					distance[next.vertex] = now.weight+next.weight;
					//다음 노드, 갱신한 거리 삽입
					q.add(new Node(next.vertex, distance[next.vertex]));
				}
			}
		}
		//결과 출력
		for (int i = 1; i < V+1; i++) {
			//경로가 존재하지 않는 경우에는 INF
			if(distance[i] == Integer.MAX_VALUE) {
				sb.append("INF"+"\n");
			}
			else {
				sb.append(distance[i] + "\n");
			}
		}
		System.out.println(sb);
	}

}
