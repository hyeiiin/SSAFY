package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//ABCDE
public class Main_13023_문혜린 {
	//A-B-C-D-E
	//5명이 친구이고 친구 관계가 4개인 경우 1 리턴
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static boolean visited[]; //방문 처리 배열
	static int result = 0;
	
	public static void dfs(int node, int depth) {
		if(depth == 4) { //4단계 관계까지 오면 성공
			result = 1; //1 리턴
			return;
		}
		else {
			visited[node] = true; //방문 처리
			for (int i = 0; i < graph.get(node).size(); i++) { //node와 인접한 노드 있으면 dfs
				if(!visited[graph.get(node).get(i)]) { //아직 방문 안했을 경우
					dfs(graph.get(node).get(i), depth+1); //깊이+1 해주기
				}
			}
			visited[node] = false; //다른 경우도 찾기 위해 백트래킹
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //사람의 수
		int M = Integer.parseInt(st.nextToken()); //친구 관계의 수
		
		//초기화
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//친구 관계 연결
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		//출발 노드 정하기 (모든 노드 탐색)
		for (int i = 0; i < N; i++) {
			visited = new boolean[N]; //초기화
			if(result == 0) { //이미 옳은 관계면 더 탐색할 필요 없음
				dfs(i, 0);
			}
		}
		System.out.println(result);
	}

}
