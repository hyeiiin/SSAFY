package lesson.boj_ans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11659_최지웅 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		
		int[] pSum = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			if (i == 0) pSum[i] = 0;
			else {
				pSum[i] = pSum[i - 1] + arr[i];
			}
		}
		
		int i, j;
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			sb.append(pSum[j] - pSum[i - 1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
