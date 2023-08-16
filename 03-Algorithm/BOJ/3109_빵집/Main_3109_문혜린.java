package algorithm.baekjoon;

import java.io.*;
import java.util.*;

//빵집
public class Main_3109_문혜린 {
	static int R, C; //행, 열
	static boolean arr[][];
	static boolean visited[][]; //파이프 설치했는지 체크
	
	public static boolean dfs(int x, int y) {
		visited[x][y] = true; //방문 처리 (파이프 설치)
		
		if(y == C-1) { //가스관과 빵집이 연결됐을 경우
			return true;
		}
		else {
			//오른쪽 위 대각선 (위쪽부터 파이프 설치)
			//범위 안에 있고 갈 수 있으며 방문 아직 안했을 경우
			if(x-1>=0 && x-1<R && y+1>=0 && y+1<C && arr[x-1][y+1] && !visited[x-1][y+1]) {
				if(dfs(x-1, y+1)) { //결과 나왔는데 재귀 더 돌아가는 것 방지
					return true;
				}
			}
			//오른쪽
			//범위 안에 있고 갈 수 있으며 방문 아직 안했을 경우
			if(x>=0 && x<R && y+1>=0 && y+1<C && arr[x][y+1] && !visited[x][y+1]) {
				if(dfs(x, y+1)) {
					return true;
				}
			}
			//오른쪽 아래 대각선
			//범위 안에 있고 갈 수 있으며 방문 아직 안했을 경우
			if(x+1>=0 && x+1<R && y+1>=0 && y+1<C && arr[x+1][y+1] && !visited[x+1][y+1]) {
				if(dfs(x+1, y+1)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new boolean[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < C; j++) {
				char c = str.charAt(j);
				if(c == '.') { //갈 수 있으면
					arr[i][j] = true;
				}
				else if(c == 'x'){ //갈 수 없으면
					arr[i][j] = false;
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			if(dfs(i, 0)) { //끝까지 파이프 설치했을 경우
				cnt++; //카운트 증가
			}
		}
		System.out.println(cnt);
	}

}
