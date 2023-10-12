package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_탁하윤 {
	static int N, M, C, map[][], max, val;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 벌통들의 크기
			M = Integer.parseInt(st.nextToken());	// 선택할 수 있는 벌통 개수
			C = Integer.parseInt(st.nextToken());	// 채취 가능 꿀 최대 양
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = 0;
			combi(0, 0, 0);	// 0: 일꾼 1: 작업 위치 2: 채취양
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void combi(int cnt, int idx, int sum) {
		if(cnt == 2) {
			max = Math.max(max, sum);
			return;
		}
		for (int i = idx; i < N*N; i++) {
			int x = i/N;	// 행
			int y = i%N;	// 열
			if(y>N-M) continue;	// 채취 불가능
			val = 0;	// 꿀 채취 가치
			calc(x, y, y+M, 0, 0); // 채취 행, 채취 열 시작, 채취열 끝, 채취 양, 채취양*채취양
			combi(cnt+1, i+M, sum+val);
		}
		
	}
	private static void calc(int x, int y, int end, int sum, int c) {
		if(sum>C) return;	// 채취 가능한 꿀의 최대 양 보다 많으면 안됨
		
		val = Math.max(val, c);
		for (int i = y; i < end; i++) {
			calc(x, i+1, end, sum+map[x][i], c+map[x][i]*map[x][i]);	// 벌꿀 채취!
		}
	}

}
