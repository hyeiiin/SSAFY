package ssafy.boj;

import java.io.*;
import java.util.*;
// 줄 세우기
// 위상 정렬
public class _2252_Boj {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 정점별 진입 차수 저장
		int[] indegree= new int[n+1];
		
		// 그래프 초기화
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		// 유향그래프
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 선행 노드
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b); 
			indegree[b]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();

		// 1. 진입 차수가 0인 노드(시작점)을 큐에 모두 넣음
		for (int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		// 2. 큐에서 진입 차수가 0인 노드를 꺼냄.
		while (!queue.isEmpty()) {
			int node = queue.poll();
			sb.append(node).append(" ");
			// 그래프의 해당 노드와 연결된 다른 노드가 있다면
			if(!graph.get(node).isEmpty()) {
				// 자신과 인접한 노드의 진입차수를 감소시킴
				for (int i = 0; i < graph.get(node).size(); i++) {
					int temp = graph.get(node).get(i);
					indegree[temp]--;
					
					// 3. 진입 차수가 0이 되면 해당 노드도 queue에 넣음
					if(indegree[temp] == 0) {
						queue.offer(temp);
					}
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
