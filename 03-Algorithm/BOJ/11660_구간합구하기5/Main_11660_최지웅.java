package lesson.boj_ans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_최지웅 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] pSum = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			pSum[i][1] = pSum[i - 1][1] + arr[i][1];
		}
		
		for (int j = 1; j <= N; j++) {
			pSum[1][j] = pSum[1][j - 1] + arr[1][j];
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				pSum[i][j] = pSum[i][j - 1] + pSum[i - 1][j] - pSum[i - 1][j - 1] + arr[i][j];
			}
		}
		
		sb = new StringBuilder();
		int x1, y1, x2, y2;
		int sum;
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			sum = 0;
			sum += pSum[x2][y2];
			sum -= pSum[x2][y1 - 1];
			sum -= pSum[x1 - 1][y2];
			sum += pSum[x1 - 1][y1 - 1];
			
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
