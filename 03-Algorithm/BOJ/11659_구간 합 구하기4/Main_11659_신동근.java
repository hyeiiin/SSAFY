package 백준;

import java.util.*;
import java.io.*;

public class Main_11659_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 누적합 알고리즘 이용
		int[] sumArr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			sumArr[i] = sumArr[i-1] + num;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int result = sumArr[end] - sumArr[start-1];
			sb.append(result).append("\n");
		}
		System.out.print(sb);

	}

}
