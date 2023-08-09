package todo.lesson._0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16935_최지웅 {
	
	static int N, M;
	static int[][] arr;
	static int[][] copy;
	static int[][] ret;
	
	static void copy() {
		int N = arr.length;
		int M = arr[0].length;
		copy = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copy[r][c] = arr[r][c];
			}
		}
	}
	
	static void copyHalf(int srcI, int srcJ, int destI, int destJ) {
		
		int N = arr.length;
		int M = arr[0].length;
		
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				arr[destI + i][destJ + j] = copy[srcI + i][srcJ + j];
			}
		}
	}
	
	static void upDown() {
		
		int N = arr.length;
		int M = arr[0].length;
		
		for (int c = 0; c < M; c++) {
			for (int r = 0; r < N; r++) {
				arr[r][c] = copy[(N - 1) - r][c];
			}
		}
	}
	
	static void leftRight() {

		int N = arr.length;
		int M = arr[0].length;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				arr[r][c] = copy[r][(M - 1) - c];
			}
		}
	}
	
	static int[][] rotate() {
		
		int N = arr.length;
		int M = arr[0].length;
		
		int[][] ret = new int[M][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				ret[c][(N - 1) - r] = arr[r][c];
			}
		}
		
		return ret;
	}
	
	static void rotateHalf() {
		
		int N = arr.length;
		int M = arr[0].length;
		
		copyHalf(0, 0, 0, M / 2);
		copyHalf(0, M / 2, N / 2, M / 2);
		copyHalf(N / 2, M / 2, N / 2, 0);
		copyHalf(N / 2, 0, 0, 0);
	}

	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		int cmd;
		final int UPDOWN = 1;
		final int LEFTRIGHT = 2;
		final int RIGHT = 3;
		final int LEFT = 4;
		final int HALFRIGHT = 5;
		final int HALFLEFT = 6;
		
		for (int r = 1; r <= R; r++) {
			cmd = Integer.parseInt(st.nextToken());
						
			switch(cmd) {
			case UPDOWN:
				copy();
				upDown();
				break;
			case LEFTRIGHT:
				copy();
				leftRight();
				break;
			case RIGHT:
				copy();
				arr= rotate();
				break;
			case LEFT:
				copy();
				arr = rotate(); // 90'
				arr = rotate(); // 180'
				arr = rotate(); // 270' = -90'
				break;
			case HALFRIGHT:
				copy();
				rotateHalf();
				break;
			case HALFLEFT:
				for (int n = 1; n <= 3; n++) {
					copy();
					rotateHalf();
				}
				break;			
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
