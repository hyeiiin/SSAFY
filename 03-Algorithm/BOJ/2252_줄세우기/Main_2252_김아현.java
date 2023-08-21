package ssafy.boj;

import java.io.*;
import java.util.*;
// 줄 세우기
public class _2252_Boj {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] indegree= new int[n+1];
		// 그래프 초기화
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 선행 노드
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			indegree[b]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			int node = queue.poll();
			sb.append(node).append(" ");
			if(!graph.get(node).isEmpty()) {
				for (int i = 0; i < graph.get(node).size(); i++) {
					int temp = graph.get(node).get(i);
					indegree[temp]--;
					
					if(indegree[temp] == 0) {
						queue.offer(temp);
					}
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
