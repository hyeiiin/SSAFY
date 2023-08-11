
package sdf;
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15686_정준원 {
	static int arr[][];
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static int[] h;
	static int res = Integer.MAX_VALUE;
	static boolean[] visit;
	static ArrayList<int[]> chi;
	static ArrayList<int[]> house;
	static int N, L, M;

	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// System.out.println("n m" + n + m);
		chi = new ArrayList<>();
		house = new ArrayList<>();

		arr = new int[N][N];
		visit = new boolean[14];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1)
					house.add(new int[] { i, j });
				if (arr[i][j] == 2)
					chi.add(new int[] { i, j });
			}
		}

		comb(0, 0);
		System.out.println(res);

	}

	static void comb(int depth, int idx) {
		// System.out.println("comb" + depth);

		if (M == depth) {
			// System.out.println("final");
			int size = Integer.MAX_VALUE;
			int sum = 0;

			// System.out.println("house size" + house.size());
			// System.out.println("chi size" + chi.size());

			for (int i = 0; i < house.size(); i++) {
				size = Integer.MAX_VALUE;
				for (int j = 0; j < chi.size(); j++) {
					if (visit[j]) {
						size = Math.min(size,
								Math.abs(house.get(i)[0] - chi.get(j)[0]) + Math.abs(house.get(i)[1] - chi.get(j)[1]));
					}
				}

				// System.out.println("i size" + i + " " + size);
				sum += size;
				size = Integer.MAX_VALUE;
			}

			// System.out.println("sum" + sum);
			res = Math.min(res, sum);
			return;
		}

		// System.out.println("chisize" + chi.size());

		for (int i = idx; i < chi.size(); i++) {
			if (visit[i]) {
				continue;
			}
			visit[i] = true;
			comb(depth + 1, i + 1);
			visit[i] = false;
		}

	}

}
