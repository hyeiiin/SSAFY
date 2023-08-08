
package sdf;
//

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Deque;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class Main_16926_정준원 {
//	static int arr[][];
//	static int arr2[][];
//	static int[] dr = { 0, 1, 0, -1 };
//	static int[] dc = { 1, 0, -1, 0 };
//	static int n;
//	static int m;
//
//	public static void main(String args[]) throws NumberFormatException, IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		n = Integer.parseInt(st.nextToken());
//		m = Integer.parseInt(st.nextToken());
//		int R = Integer.parseInt(st.nextToken());
//		arr = new int[n][m];
//		StringBuilder sb = new StringBuilder();
//
//		for (int i = 0; i < n; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < m; j++) {
//				arr[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//
//		for (int k = 0; k < R; k++) {
//			roll();
//			System.out.println();
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
//
//			System.out.println();
//		}
//
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				sb.append(arr[i][j]).append(" ");
//			}
//			sb.append('\n');
//		}
//		System.out.println(sb.toString());
//
//	}
//
//	static void bfs(int r, int c) {
//		Queue<int[]> q = new LinkedList<>();
//		q.add(new int[] { r, c });
//		int first = arr[r][c];
//
//		int dir = 0;
//
//		while (!q.isEmpty()) {
//			int[] v = q.poll();
//
//			int nr = v[0] + dr[dir];
//			int nc = v[1] + dc[dir];
//			System.out.println("nr nc" + nr + " " + nc);
//			if (r == nr && c == nc) {
//				System.out.println("final ");
////				arr[r][c] = next.poll();
//
//				break;
//			}
//
//			if (isvalid(nr, nc)) {
//				arr[v[0]][v[1]] = arr[nr][nc];
//
//				q.add(new int[] { nr, nc });
//			}
//
//			if (!isvalid(nr, nc)) {
//				dir++;
//				if (dir >= 4)
//					break;
//				nr = v[0] + dr[dir];
//				nc = v[1] + dc[dir];
//
//				if (!isvalid(nr, nc)) {
//					break;
//				}
//				System.out.println("!is val nr nc" + nr + " " + nc);
//
//				q.add(new int[] { nr, nc });
//
//				// System.out.println("!isval");
//			}
//
//		}
//
//		arr[r + 1][c] = first;
//
//	}
//
//	static boolean isvalid(int r, int c) {
//		return r >= 0 && r < n && c >= 0 && c < m;
//	}
//
//	static void roll() {
//
//		int num = 0;
//		num = Math.min(n, m) / 2;
//		for (int i = 0; i < num; i++) {
//			bfs(i, i);
//		}
//	}
//
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_정준원 {
	static int N, M, R;
	static int map[][], after[][];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1]; // 인덱스 1부터 시작하도록
		after = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // -입력 끝
		rotate(R); // count: 바깥에서부터 몇번째 줄 할 차례인지

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
//				System.out.print(map[i][j]+ " ");
				sb.append(map[i][j]).append(" ");
			}
//			System.out.println();
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void rotate(int R) {
		if (R == 0)
			return;
		int count = 1;
		while (true) {
			if (count > (M / 2) || count > (N / 2)) {// count가 N,M길이의 반을 넘어가면 돌릴 이유 X->종료
				break;
			}
			for (int i = count; i < M + 1 - count; i++) {
				after[count][i] = map[count][i + 1];// 위
				after[N + 1 - count][i + 1] = map[N + 1 - count][i];// 아래
			}

			for (int i = count; i < N + 1 - count; i++) {
				after[i + 1][count] = map[i][count];// 왼쪽
				after[i][M + 1 - count] = map[i + 1][M + 1 - count];// 오른쪽
			}
			count++;
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = after[i][j];
			}
		}
		rotate(--R);

	}

}
