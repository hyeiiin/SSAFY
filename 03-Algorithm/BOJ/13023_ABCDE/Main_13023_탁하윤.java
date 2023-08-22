package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_13023_탁하윤 {
	static int N, M;
	static ArrayList<Integer>[] list;
	static boolean exist, visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 학생 수
		M = Integer.parseInt(st.nextToken());	// 친구관계 수
		
		list = new ArrayList[N];	// 0번 부터 N번 인덱스
		for (int i = 0; i < N; i++) {	// 0번 부터 N번 인덱스마다 ArrayList로 초기화
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {	// 친구관계 초기화
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);	// 양방향으로 친구관계 넣기
			list[b].add(a);
		}
		
		for (int i = 0; i < N; i++) { 
			visited = new boolean[N];	// 방문처리 배열
			dfs(i, 1);	// 0번 친구부터 깊이 1부터 시작
			if(exist) {	// 만약 5명의 친구 관계과 완성되면 1출력 후 종류
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);	// 5명의 친구 관계가 완성되지 않았다면 

	}
	
	static void dfs(int x, int depth) {	// x: 현재 친구 번호, depth: 현재 깊이
		if(depth == 5) {	// 깊이가 5라면 친구 관계 완성
			exist = true;	// flag를 true로 하고 종료
			return;
		}
		
		for (int i = 0; i < list[x].size(); i++) {	// 현재 친구의 친구관계 크기 만큼 돌면서
			if(!visited[list[x].get(i)]) {	// 아직 방문안한 친구라면 
				visited[x] = true;	// 방문처리하고
				dfs(list[x].get(i), depth+1);	// 친구의 친구를 타고 다시 dfs(depth 증가)
				visited[x] = false;	// 모든 경로를 탐색할 수 있도록 돌아오면 false
			}
		}
	}

}
