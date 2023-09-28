package sdf;

import java.io.*;
import java.util.*;

public class Main_9205_정준원 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			ArrayList<Point> all = new ArrayList<>();

			// 출발지 좌표
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			all.add(new Point(startX, startY));

			for (int j = 1; j <= n; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				all.add(new Point(x, y));
			}

			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			all.add(new Point(endX, endY));

			if (solve(all)) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}
	}

	static boolean solve(ArrayList<Point> all) {
		int size = all.size();
		boolean[][] visit = new boolean[size][size];

		// 모든 지점 간의 도달 가능성 계산
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Point p1 = all.get(i);
				Point p2 = all.get(j);
				int distance = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
				if (distance <= 1000) {
					visit[i][j] = true;
				}
			}
		}

 		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (visit[i][k] && visit[k][j]) {
						visit[i][j] = true;
					}
				}
			}
		}

		return visit[0][size - 1];
	}
}
