package todo.lesson._0807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1233_최지웅 {

	static int N;
	static String[] tree;

	static boolean isOperator(int idx) {
		String cur = tree[idx];
		switch (cur) {
		case "+":
		case "-":
		case "*":
		case "/":
			return true;
		default:
			return false;
		}
	}

	static boolean hasChild(int idx) {
		return 2 * idx <= N || 2 * idx + 1 <= N;
	}

	static boolean checkLeaf(int idx) {

		String cur = tree[idx];

		if (cur == "+" || cur == "-" || cur == "*" || cur == "/")
			return false;
		else
			return true;
	}

	static boolean bfs() {

		Queue<Integer> queue = new ArrayDeque<>();

		if (isOperator(1)) {

			queue.offer(1);

			int idx;
			while (!queue.isEmpty()) {
				idx = queue.poll();

				if (isOperator(idx)) {
					if (!hasChild(idx))
						return false;
					else {
						if (2 * idx <= N + 1)
							queue.offer(2 * idx);
						if (2 * idx + 1 <= N + 1)
							queue.offer(2 * idx + 1);
						continue;
					}
				} else if (hasChild(idx)) {
					return false;
				}
			}
		} else if (hasChild(1)) {
			return false;
		} else return true;

		return true;
	}

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("demo.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		final int POSSIBLE = 1;
		final int IMPOSSIBLE = 0;

		int idx;

		for (int t = 1; t <= 10; t++) {

			N = Integer.parseInt(br.readLine());

			tree = new String[N + 1];

			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				idx = Integer.parseInt(st.nextToken());
				tree[idx] = st.nextToken();
				while (st.hasMoreTokens()) {
					st.nextToken();
				}
			}

			sb.append("#").append(t).append(" ");
			if (bfs()) {
				sb.append(POSSIBLE);
			} else
				sb.append(IMPOSSIBLE);
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
