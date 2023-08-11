
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

public class Main_16435_정준원 {
	static int arr[][];
	static int arr2[][];
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static int[] h;

	static int N, L;

	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		// System.out.println("n m" + n + m);

		h = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			h[i] = Integer.parseInt(st.nextToken());

		}

		Arrays.sort(h);

		for (int i = 0; i < h.length; i++) {
			if (h[i] <= L) {
				L += 1;

			}
		}
		System.out.println(L);
	}

}
