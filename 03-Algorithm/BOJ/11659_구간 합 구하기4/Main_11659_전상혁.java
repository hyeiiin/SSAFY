package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11659_전상혁 {
	static int N, M, numbers[], i, j;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int a = 1; a <= N; a++) {		
			numbers[a] = numbers[a-1] + Integer.parseInt(st.nextToken());	
		}

		for (int a = 0; a < M; a++) {
			st = new StringTokenizer(br.readLine());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());

			sb.append(numbers[j]-numbers[i-1]).append("\n");
		}
		System.out.println(sb);

	}

}
