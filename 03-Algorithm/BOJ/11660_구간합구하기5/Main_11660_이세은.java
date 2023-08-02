package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_11660_이세은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(); // 결과값 저장 위한 sb
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int m = Integer.parseInt(st.nextToken()); // 구간합 개수

		int[][] numbers = new int[n + 1][n + 1];

		// 숫자 n개 2차원 배열에 저장
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				numbers[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		int[][] numbersSum = new int[n + 1][n + 1];
		// 2차원 배열 누적합 구하기, 사각형 범위로 누적합 구하기
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				numbersSum[i][j] = numbers[i][j] +  numbersSum[i - 1][j] + numbersSum[i][j - 1] - numbersSum[i - 1][j - 1]; // 자신의 누적합에서 위쪽 숫자, 왼쪽 숫자, 대각선 위 누적합 빼주기
			}
		}

		// m번 누적합 구하기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken()); // 구간합 시작
			int startY = Integer.parseInt(st.nextToken()); // 구간합 시작
			int endX = Integer.parseInt(st.nextToken()); // 구간합 끝
			int endY = Integer.parseInt(st.nextToken()); // 구간합 끝
			
			sb.append(String.valueOf(numbersSum[endX][endY] - numbersSum[endX][startY-1]- numbersSum[startX-1][endY]+numbersSum[startX-1][startY-1])).append("\n");

		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
