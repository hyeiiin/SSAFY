package ssafy.Boj;

import java.io.*;
import java.util.*;
public class Main_13023_김아현 {
	
	static boolean[] visited;
	static List<List<Integer>> list;
	static boolean check = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 사람 수
		int m = Integer.parseInt(st.nextToken()); // 친구 관계 수
		
		visited = new boolean[n];
		list = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		for (int i = 0; i < n; i++) {
			if(!check) {
				dfs(i,1);
			}
			
			if(check) break;
		}
		
		if(check){
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		
	}

	static void dfs(int start, int depth) {
		if(depth == 5) {
			check = true;
			return;
		}
		
		visited[start] = true; // start노드 방문처리
		for (int node : list.get(start)) {
			if(visited[node]) continue; // 방문하려는 노드를 방문한 적 있으면 pass			
			dfs(node, depth+1);
		}
		visited[start] = false;
	}
}