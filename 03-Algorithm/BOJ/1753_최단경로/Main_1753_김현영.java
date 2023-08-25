import java.io.*;
import java.util.*;

public class Main_1753_김현영 {

	static class Node {
		int vertex;
		int weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int V = Integer.parseInt(st.nextToken()); // 정점의 수
		int E = Integer.parseInt(st.nextToken()); // 간선의 수
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken()) - 1; // 시작 정점

		// 인접 리스트 입력
		Node[] list = new Node[V];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1; // 출발 정점
			int v = Integer.parseInt(st.nextToken()) - 1; // 도착정점
			int w = Integer.parseInt(st.nextToken()); // 가중치
			list[u] = new Node(v, w, list[u]);
		}

		boolean[] isVisited = new boolean[V]; // 방문 여부
		int[] distance = new int[V]; // 각 정점까지의 최단경로 저장
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[k] = 0; // 시작 정점의 경로값 0으로 저장

		int min = 0;
		int stopOver = 0;

		for (int i = 0; i < V; i++) {
			stopOver = -1;
			min = Integer.MAX_VALUE;

			// 가장 가까운 정점 선택
			for (int j = 0; j < V; j++) {
				if (!isVisited[j] && min > distance[j]) {
					min = distance[j];
					stopOver = j;
				}
			}
			// 연결된 정점이 없으면 종료
			if (stopOver == -1)  
				break;
		 
			isVisited[stopOver] = true; // 방문처리


			// 연결된 정점의 경로 비교
			for (Node temp = list[stopOver]; temp != null; temp = temp.next) {
				if (!isVisited[temp.vertex] && distance[temp.vertex] > min + temp.weight) {
					distance[temp.vertex] = min + temp.weight;
				}
			}
		}

		for (int i = 0; i < V; i++) {
			if(distance[i]== Integer.MAX_VALUE)
				sb.append("INF\n");
			else
				sb.append(distance[i]).append("\n");
		}
		System.out.println(sb.toString());

	}
}
