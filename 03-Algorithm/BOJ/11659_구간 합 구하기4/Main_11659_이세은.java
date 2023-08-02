package practice;

import java.io.*;
import java.util.StringTokenizer;

public class Main_11659_이세은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(); // 결과값 저장 위한 sb
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int m = Integer.parseInt(st.nextToken()); // 구간합 개수

		int[] numbers = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		// 숫자 n개 배열에 저장
		for (int i = 1; i <= n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken()) + numbers[i - 1];
		}
		// m번 구간합 구하기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 구간합 시작
			int end = Integer.parseInt(st.nextToken()); // 구간합 끝

			sb.append(String.valueOf(numbers[end] - numbers[start - 1])).append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
