package swea;
import java.util.*;
import java.io.*;
public class Solution_2115_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int N, M, C, map[][], memo[][];
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	//벌통들의 크기 N,
		M = Integer.parseInt(st.nextToken());	//선택할 수 있는 벌통의 개수 M,
		C = Integer.parseInt(st.nextToken());	//꿀을 채취할 수 있는 최대 양 C
		map = new int[N][N];
		memo = new int[N][N-M+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void solve() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N-M+1; j++) {
				go(i, j, 0, 0, 0);		//	go(x좌표, y좌표, idx, sum, total) 조합
			}
		}
		
		
		int max1 = 0;
		int max1_x=0;
		int max1_y=0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N-M+1; y++) {
				if (max1 < memo[x][y]) {
					max1 = memo[x][y];
					max1_x = x;
					max1_y = y;
				}
			}
		}
		
		int max2 = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N-M+1; y++) {
				if(x == max1_x && Math.abs(y - max1_y) < M) continue; // 영역이 겹치면 제외
				if (max2 < memo[x][y]) max2 = memo[x][y];
			}
		}
		
		sb.append(max1+max2);
	}
	

	
	static void go(int x, int y, int idx, int sum, int total) {
		if(sum > C) return; 	//C보다 크면 그 다음 조합은 볼 필요도 없음
		if(idx == M) {		//종료조건
			memo[x][y] = memo[x][y] > total ? memo[x][y] : total;
			return;
		}
		int tmp = map[x][y+idx];
		go(x, y, idx+1, sum+tmp, total+tmp*tmp);
		go(x, y, idx+1, sum, total);
	}

	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			init();
			solve();
			
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
