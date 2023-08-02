package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2805_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스 개수
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());	// 농장 크기
			int[][] farm = new int[N][N];				// 농장 배열
			char[] ch;									// 농장의 수익을 담을 배열
			int center = N/2;							// 농장의 중심
			int profit = 0;								// 농장 이익
			
			// 농장 만들기
			for(int i=0; i<N; i++) {
				ch = br.readLine().toCharArray();
				for (int j=0; j<N; j++) {
					farm[i][j] = ch[j] - '0';
				}
			}
			
			// 마름모 모양의 수익 내기
			for(int i=0; i<N; i++) {
				// 마름모로 만들기 위해서 시작점과 알아내기
				int gap = Math.abs(i-center); 
				for(int j=gap; j<N-gap; j++) {
					profit += farm[i][j];
				}
			}
			
			System.out.printf("#%d %d\n", tc, profit);
			
		}

	}

}
