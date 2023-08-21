package algorithm.baekjoon;

import java.io.*;
import java.util.*;

public class Main_10026_문혜린 {
	static int N; //N*N 그리드
	static char arr[][]; //그림
	static int cwRange = 0; //적록색약인 사람 구역
	static int nRange = 0; //적록색약 아닌 사람 구역
	static boolean visited[][]; //방문 여부
	//상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void dfs(int x, int y, char c) {
		visited[x][y] = true; //방문 처리
		
		//상하좌우 같은 색 탐색
		for (int i = 0; i < 4; i++) {
			//상하좌우 이동
			int nx = x + dx[i];
			int ny = y + dy[i];
			//범위 벗어나지 않고 아직 방문 안했을 경우
			if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]) {
				if(c == arr[nx][ny]) { //색이 같으면 dfs
					dfs(nx, ny, c);
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine()); //N*N 그리드
		visited = new boolean[N][N];
		//그림
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		//적록색약 아닌 사람 구역 수 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) { //방문 안한 곳 있으면 dfs
					dfs(i, j, arr[i][j]);
					nRange++; //구역 수 카운트 증가
				}
			}
		}
		visited = new boolean[N][N]; //방문 배열 초기화
		//적록색약인 사람 구역 수 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//적록색약인 사람은 R과 G를 구별 못하므로 G를 R로 변경
				if(arr[i][j] == 'G') {
					arr[i][j] = 'R';
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) { //방문 안한 곳 있으면 dfs
					dfs(i, j, arr[i][j]);
					cwRange++; //구역 수 카운트 증가
				}
			}
		}	
		System.out.println(nRange + " " + cwRange);
	}

}
