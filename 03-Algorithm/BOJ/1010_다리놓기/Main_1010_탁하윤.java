package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] B = new int[M+1][M+1];
			
			for (int j = 0; j <= M; j++) {	// M개 중에 N개 뽑는 조합
				for (int k = 0, end = Math.min(j, N); k <= end; k++) {	// j와 N 중에 작은값 까지 for문 돌면서 조합 값 넣기
					if(k==0||j==k) B[j][k] = 1;	// jC0 == jCk == 1
					else B[j][k] = B[j-1][k-1] + B[j-1][k];	// jCk = j-1Ck-1 + j-1Ck
				}
			}
			System.out.println(B[M][N]);
		}

	}

}
