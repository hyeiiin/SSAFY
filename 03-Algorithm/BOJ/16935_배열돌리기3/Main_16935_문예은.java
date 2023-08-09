package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16935_문예은 {
	static int[][] boardOrigin;
	static int[][] boardCopy;
	static int N,M,R;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		R = Integer.parseInt(st.nextToken()); // 연산 횟수
		boardOrigin = new int[N][M]; // 초기 배열(돌리기 완성 후 붙여넣음)
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				boardOrigin[n][m]=Integer.parseInt(st.nextToken()); 
			}
		}
		int[] O = new int[R]; // 연산 횟수만큼 연산번호 받기
		st = new StringTokenizer(br.readLine());
		for (int r = 0; r < R; r++) {
			O[r] = Integer.parseInt(st.nextToken()); // 연산 번호
		}
		for (int o : O) {
			switch (o) {
			case 1: holymoly1(); break;
			case 2: holymoly2(); break;
			case 3: holymoly3(); break;
			case 4: holymoly4(); break;
			case 5: holymoly5(); break;
			case 6: holymoly6(); break;
			}
		}
		printArr();
	}
	private static void holymoly1() { // 상하반전
		boardCopy = new int[N][M]; // 돌리기 작업할 배열
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) boardCopy[N-j-1][i] = boardOrigin[j][i];
		}
		boardOrigin = boardCopy.clone();
	}
	private static void holymoly2() { // 좌우반전
		boardCopy = new int[N][M]; // 돌리기 작업할 배열
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) boardCopy[i][M-j-1] = boardOrigin[i][j];
		}
		boardOrigin = boardCopy.clone();
	}
	private static void holymoly3() {
		boardCopy = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) boardCopy[j][N-i-1] = boardOrigin[i][j];
		}
		int temp = M;
		M = N;
		N = temp;
		boardOrigin = boardCopy.clone();
	}
	private static void holymoly4() {
		boardCopy = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) boardCopy[M-j-1][i] = boardOrigin[i][j];
		}
		int temp = M;
		M = N;
		N = temp;
		boardOrigin = boardCopy.clone();
	}
	private static void holymoly5() {
		boardCopy = new int[N][M]; // 돌리기 작업할 배열
		turnArr(0, 0, N/2, M/2, 0, M/2); // 1 -> 2
		turnArr(0, M/2, N/2, M, N/2, M/2); // 2->3
		turnArr(N/2, M/2, N, M, N/2, 0); // 3->4
		turnArr(N/2, 0, N, M/2, 0, 0); // 4->1
		boardOrigin = boardCopy.clone();
	}
	private static void holymoly6() {
		boardCopy = new int[N][M]; // 돌리기 작업할 배열
		turnArr(0, 0, N/2, M/2, N/2, 0); // 1 -> 4
		turnArr(0, M/2, N/2, M, 0, 0); // 2->1
		turnArr(N/2, M/2, N, M, 0, M/2); // 3->2
		turnArr(N/2, 0, N, M/2, N/2, M/2); // 4->3
		boardOrigin = boardCopy.clone();
	}
	// 파라미터 : 나눌 구간 start점 x,y / end점 x,y / 붙여넣을 paste start점 x,y
	private static void turnArr(int sx, int sy, int ex, int ey, int psx, int psy) {
		int x = psx;
		int y = psy;
		for (int i = sx; i < ex; i++) {
			for (int j = sy; j < ey; j++) {
				boardCopy[x][y++] = boardOrigin[i][j];
			}
			y = psy;
			x++;
		}
	}
	private static void printArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(boardOrigin[i][j]+" ");
			}
			System.out.println();
		}
	}
}
