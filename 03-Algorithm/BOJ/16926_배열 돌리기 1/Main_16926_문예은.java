package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_문예은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 행
		int M = Integer.parseInt(st.nextToken()); // 열
		int R = Integer.parseInt(st.nextToken()); // 회전 수
		int[][] arrOrgin = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				arrOrgin[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		for (int r = 0; r < R; r++) {
			int[][] arrCopy = new int[N][M]; // 회전할 때마다 값 넣을 새로운 배열 생성
			int cnt = 0; // 네모 배열 껍질 수(깊이)
			int n = N;
			int m = M;
			while(Math.min(n, m)/2 > cnt) { // 겉부터 속까지 모든 껍질 회전하면 종료
				for (int i = cnt+1; i < m-cnt; i++) // 윗줄 왼쪽이동
					arrCopy[cnt][i-1] = arrOrgin[cnt][i];
				for (int i = cnt+1; i < n-cnt; i++) // 오른쪽줄 위로이동
					arrCopy[i-1][m-1-cnt]= arrOrgin[i][m-1-cnt]; 
				for (int i = m-2-cnt; i >= cnt; i--) // 아랫줄 오른쪽이동
					arrCopy[n-1-cnt][i+1] = arrOrgin[n-1-cnt][i];
				for (int i = n-2-cnt; i >= cnt; i--) // 왼쪽줄 아래로이동
					arrCopy[i+1][cnt] = arrOrgin[i][cnt]; 
				cnt++;
			}
			arrOrgin = arrCopy.clone(); // 최종 회전까지 계속해서 덮어씌우기
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arrOrgin[i][j]+ " ");
			}
			System.out.println();
		}
	}
}
