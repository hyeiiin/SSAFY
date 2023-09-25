package sdf;

import java.io.*;
import java.util.*;

public class Main_14502_정준원 {

	static int arr[][];
	static int arr2[][];
	static boolean visit[][];

	static final int dx[] = { 1, -1, 0, 0 }; // 상하좌우 방향 설정
	static final int dy[] = { 0, 0, 1, -1 }; // 상화좌우 방향 설정

	public static int N, M, result = 0;

	static Queue<Point> q = new LinkedList<Point>();

	static class Point {

		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		makeWall(0);

		System.out.print(result);
	}

	public static void makeWall(int cnt) {

		if (cnt == 3) { // 벽 3개 다 세웠을 경우
			spread(); // 바이러스 퍼뜨리기
			count(); // 안전 영역 카운트
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					makeWall(cnt + 1);
					arr[i][j] = 0;
				}
			}
		}

	}

	static void spread() {
		arr2 = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				arr2[i][j] = arr[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr2[i][j] == 2) {
					q.add(new Point(i, j));
				}
			}
		}

		int x, y;

		while (!q.isEmpty()) {

			Point p = q.poll();

			for (int i = 0; i < 4; i++) {

				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx >= N || ny >= M || nx < 0 || ny < 0) {
					continue;
				}

				if (arr2[nx][ny] == 0) {
					arr2[nx][ny] = 2;
					q.offer(new Point(nx, ny));
				}
				
			}

		}

	}

	static void count() {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr2[i][j] == 0)
					cnt++;
			}
		}
		result = Math.max(cnt, result);
	}

}
