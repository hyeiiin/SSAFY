package todo.lesson._0808;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_최지웅 {

	static int[][] copyA;
	static int[][] A;
	static int N, M;
	
	static void copyArr() {
		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				copyA[n][m] = A[n][m];
			}
		}
	}
	
	static void rotateBox(int rep) {
		
		for (int r = rep; r <= N - rep; r++) {
			A[r + 1][rep] = copyA[r][rep];
		}
		
		for (int c = rep; c <= M - rep; c++) {
			A[N - rep + 1][c + 1] = copyA[N - rep + 1][c];
		}
		
		for (int r = N - rep + 1; r >= rep + 1; r--) {
			A[r - 1][M - rep + 1] = copyA[r][M - rep + 1];
		}
		
		for (int c = M - rep + 1; c >= rep + 1; c--) {
			A[rep][c - 1] = copyA[rep][c];
		}
	}
	
	static void rotate() {
		copyArr();
		for (int n = N, m = M, rep = 1; n >= 2 && m >= 2; n -=2, m -= 2, rep += 1) {
			rotateBox(rep);
		}
	}

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		A = new int[N + 1][M + 1];
		copyA = new int[N + 1][M + 1];
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				A[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 1; r <= R; r++)
			rotate();
		
		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				sb.append(A[n][m] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
