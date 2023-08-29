import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


class Main {

	static int N;

	static int[][][] dp;

	static int[][] mutal = {{9, 3, 1}, {9, 1, 3}, {3, 1, 9}, {3, 9, 1}, {1, 3, 9}, {1, 9, 3}};

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		
		
		dp = new int[61][61][61];

		Queue<Node> queue = new ArrayDeque<>();

		queue.add(new Node(0, 0, 0, 0));

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (dp[cur.a][cur.b][cur.c] > cur.cnt) {
				continue;
			}

			for (int[] attack : mutal) {
				// 현재 위치부터  attack까지의 반복문으로 dp 갱신
				for (int i = cur.a; i <= Math.min(60, cur.a + attack[0]); i++) {
					for (int j = cur.b; j <= Math.min(60, cur.b + attack[1]); j++) {
						for (int k = cur.c; k <= Math.min(60, cur.c + attack[2]); k++) {
							if (dp[i][j][k] != 0) {
								continue;
							}
							dp[i][j][k] = cur.cnt + 1;

							queue.add(new Node(i, j, k, dp[i][j][k]));
						}
					}
				}
			}

		}
		
		if (N == 1) {
			System.out.println(dp[Integer.parseInt(st.nextToken())][0][0]);
		} else if (N == 2) {
			System.out.println(
				dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())][0]);
		} else {
			System.out.println(
				dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())][Integer.parseInt(
					st.nextToken())]);
		}

	}


	static class Node {

		int a;
		int b;
		int c;

		int cnt;

		public Node(int a, int b, int c, int cnt) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.cnt = cnt;
		}
	}


}
