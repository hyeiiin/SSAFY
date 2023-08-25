import java.util.*;
import java.io.*;

public class Main_1753_조은서 {

	static class Node implements Comparable<Node>{
		int vertex, weight;
		
		public Node(int vertex, int weight) {
			this.vertex= vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int V, E;
	static ArrayList<Node>[] list;
	static boolean[] visited;
	static int[] distance;
	static final int INF = Integer.MAX_VALUE;
	static PriorityQueue<Node> pq;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수
		
		int start = Integer.parseInt(br.readLine()); // 시작 정점 번호
		
		list = new ArrayList[V+1];
		
		for(int i=0; i<=V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, weight));
		}
		
		
		dijkstra(start);
		
		System.out.println(sb);

	}
	
	static void dijkstra(int start) {
		// -- 다익스트라 start by PriorityQueue (하나씩 방문해서 최소값을 찾는 건 O(N^2) -> 시간 초과)
		visited = new boolean[V+1];
		distance = new int[V+1];
		
		Arrays.fill(distance, INF);

		distance[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			int cur = pq.poll().vertex;
			
			if(visited[cur]) continue;
			visited[cur] = true;
			
			for(Node next : list[cur]) {
				if(distance[next.vertex] > distance[cur] + next.weight) {
					distance[next.vertex]  = distance[cur] + next.weight;
					pq.offer(new Node(next.vertex, distance[next.vertex]));
				}
			}
		}
		
		for(int i =1; i<distance.length; i++) {
			if(distance[i]==INF) System.out.println("INF" + " ");
			else System.out.println(distance[i] + " ");
		}
		// -- end
	}

}
