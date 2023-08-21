package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_탁하윤 {
	
	static class Node{
		int vertex;
		Node next;
		
		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}
	
	static int N, M;
	static Node[] adjList;
	static int[] inDegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// N명의 학생들
		M = Integer.parseInt(st.nextToken());	// 키 비교 횟수
		adjList = new Node[N+1];				// 노드 연결 리스트
		inDegree = new int[N+1];				// 진입차수를 넣을 배열
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	// 학생 A가 학생 B의 앞에 서야 함
			int B = Integer.parseInt(st.nextToken());
			adjList[A] = new Node(B, adjList[A]);	// 유향 그래프
			++inDegree[B];	// 진입차수 넣기
		}
		
		ArrayList<Integer> orderList = topologySort();	// 리스트 만들기
		if(orderList.size() == N) {	// 리스트 사이즈가 학생들 수와 같다면
			for(int o:orderList) {	// 출력
				System.out.print(o+" ");
			}
		}
		
	}
	
	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> orderList = new ArrayList<>();	// 리턴할 리스트
		Queue<Integer> q = new LinkedList<Integer>();	// 진입차수가 0인 정점을 넣을 큐
		for (int i = 1; i <= N; i++) {	// 학생들 수 만큼 돌면서 진입차수가 0이면 q에 넣는다.
			if(inDegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {	// q가 공백 큐가 될 때까지 (bfs)
			int cur = q.poll();	// 현재 정점을 리스트에 넣기(진입 차수 0)
			orderList.add(cur);
			
			for (Node temp = adjList[cur]; temp != null; temp=temp.next) {	// temp가 널이 아닐때까지, 자신과 인접한 노드의 간선 제거
				if(--inDegree[temp.vertex]==0) q.offer(temp.vertex);	// 간선 제거 후 진입 차수가 0이 된 정점을 q에 넣기
			}
		}
		return orderList;
	}

}
