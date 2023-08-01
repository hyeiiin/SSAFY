package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2805_박정인 {
	static int N;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += map[N / 2][i];
			}
			
			for (int i = 1; i <= N/2; i++) {
				for (int j = i; j < N - i; j++) {
					sum += map[N/2 - i][j];
					sum += map[N/2 + i][j];
				}
			}
			
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
