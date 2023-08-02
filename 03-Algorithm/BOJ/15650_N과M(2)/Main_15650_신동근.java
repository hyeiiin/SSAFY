package 백준;

import java.util.*;
import java.io.*;

public class Main_11660_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] sumMap = new int[N+1][N+1];
		
		// 2차원배열에서 누적합 알고리즘 이용
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int num = Integer.parseInt(st.nextToken());
				sumMap[i][j] = sumMap[i][j-1] + num;	// 배열의 각 행마다 누적합을 적용해준다
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 1 <= M <= 100,000 
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());	// 행의 시작 위치
			int startY = Integer.parseInt(st.nextToken());	// 열의 시작 위치
			int endX = Integer.parseInt(st.nextToken());	// 행의 끝 위치
			int endY = Integer.parseInt(st.nextToken());	// 열의 끝 위치
			int result = 0;	// 결과값 저장할 변수
			
			// 시작 행부터 끝 행까지 반복문 돌려주기 (x1, y1, x2, y2 <= 1000 이므로 1000 * M 해도 1억 이므로) 
			// 제한시간 1초안에 세잎 가능하다
			for(int j=startX; j<=endX; j++) {
				result += (sumMap[j][endY] - sumMap[j][startY-1]);	// 각 행의 구간합들 더해주기
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb);

	}

}
