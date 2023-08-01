package ssafy;

import java.util.*;
import java.io.*;

// n m
// 3 1
public class _15649_Boj {
	static int N;
	static int M;
	static boolean[] visited; // 인덱스에 해당하는 숫자가 사용 중인지 저장하는 배열
	static int[] numbers; // 순열 저장 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		numbers = new int[M];

		makePer(0);
	}

	static void makePer(int idx) {
		StringBuilder sb = new StringBuilder();
		if (idx == M) {
			for (int i = 0; i < numbers.length; i++) {
				sb.append(numbers[i]);
				sb.append(" ");
			}
			System.out.println(sb);
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i] == true) {
				continue;
			}

			numbers[idx] = i;
			visited[i] = true;
			makePer(idx + 1);
			visited[i] = false;
		}
	}

}
