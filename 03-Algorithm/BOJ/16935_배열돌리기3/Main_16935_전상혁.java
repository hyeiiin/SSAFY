package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Main_16935_전상혁 {
	static int N,M,R, arr[][], tmp[][], change;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int mode = Integer.parseInt(st.nextToken());
			
			switch(mode) {
				case 1:
					one();
					break;
				case 2:
					two();
					break;
				case 3:
					three();
					break;
				case 4:
					four();
					break;
				case 5:
					five();
					break;
				case 6:
					six();
					break;
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
	}
	private static void one() {
		tmp = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			tmp[N-r-1] = arr[r];
		}
		arr = tmp;
	}
	private static void two() {
		tmp = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				tmp[r][M-c-1] = arr[r][c];
			}
		}
		arr = tmp;
	}
	private static void three() {
		//90도 회전할 경우 행과 열의 길이가 바뀜
		tmp = new int[M][N];
		//6x8 -> 8x6
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				tmp[c][N-r-1] = arr[r][c];
			}
		}
		//행과 열 바꾸기
		change = N;
		N = M;
		M = change;
		arr = tmp;
	}
	private static void four() {
		tmp = new int[M][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				tmp[M-c-1][r] = arr[r][c];
			}
		}
		change = N;
		N = M;
		M = change;
		arr = tmp;
	}
	private static void five() {
		//배열을 4등분
		//1번 -> 2번 (오른쪽) 2번 -> 3번 (아래) 3번 -> 4번 (왼쪽) 4번 -> 1번 (위)
		//N/2 x M/2 --> 6x8 이면 부분배열 크기: 3x4
		tmp = new int[N][M];
		//1번->2번
		for (int r = 0; r < N/2; r++) {
			for (int c = 0; c < M/2; c++) {
				tmp[r][M/2+c] = arr[r][c];
			}
		}
		//2번->3번
		for (int r = 0; r < N/2; r++) {
			for (int c = M/2; c < M; c++) {
				tmp[N/2+r][c] = arr[r][c];
			}
		}
		//3번->4번
		for (int r = N/2; r < N; r++) {
			for (int c = M/2; c < M; c++) {
				tmp[r][c-M/2] = arr[r][c];
			}
		}
		//4번->1번
		for (int r = N/2; r < N; r++) {
			for (int c = 0; c < M/2; c++) {
				tmp[r-N/2][c] = arr[r][c];
			}
		}
		arr = tmp;
	}
	private static void six() {
		//1번->4번, 4번->3번, 3번->2번, 2번->1번
		tmp = new int[N][M];
		//1->4
		for (int r = 0; r < N/2; r++) {
			for (int c = 0; c < M/2; c++) {
				tmp[N/2+r][c] = arr[r][c];
			}
		}
		//4->3
		for (int r = N/2; r < N; r++) {
			for (int c = 0; c < M/2; c++) {
				tmp[r][M/2+c] = arr[r][c];
			}
		}
		//3->2
		for (int r = N/2; r < N; r++) {
			for (int c = M/2; c < M; c++) {
				tmp[r-N/2][c] = arr[r][c];
			}
		}
		//2->1
		for (int r = 0; r < N/2; r++) {
			for (int c = M/2; c < M; c++) {
				tmp[r][c-M/2] = arr[r][c];
			}
		}
		arr = tmp;
	}
	
	
}
