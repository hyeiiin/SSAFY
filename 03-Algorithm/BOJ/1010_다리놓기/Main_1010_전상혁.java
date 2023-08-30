package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_전상혁 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); //강 서쪽 사이트 개수
			int M = Integer.parseInt(st.nextToken()); //강 동쪽 사이트 개수
			//mCn
			int[][] memo = new int[M+1][N+1];
			 
			for (int r = 0; r < M+1; r++) {
				for (int c = 0, end = Math.min(r, N); c <= end; c++) {
					//열 인덱스 0 -> 첫 사이트가 갈 수 있는 도착지, 첫 사이트는 모두 갈 수 있음
					if (c==0 || r==c) memo[r][c] = 1;
					//윗 행의 왼쪽(상좌) + 바로 위(상) 값 더해 경우의 수 쌓기
					else memo[r][c] = memo[r-1][c-1] + memo[r-1][c];
				}
				 
			 }
			 System.out.println(memo[M][N]);
		}
	}
	
}
