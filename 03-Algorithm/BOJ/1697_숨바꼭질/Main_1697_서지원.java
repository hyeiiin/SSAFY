package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_1697_서지원 {
	
	static int N, K;
	static int[] visited = new int[100001];
	static Queue<Integer> q = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		
		bfs();
	}
	
	private static void bfs() {
		visited[N] = 1;
		q.offer(N);
		while (!q.isEmpty()) {
			int x = q.poll();
			if (x == K) {
				System.out.println(visited[x] - 1);
				return;
			}

			for (int p : new int[] {x - 1, x + 1, 2 * x}) {
				if (p >= 0 && p < 100001 && visited[p] == 0) {
					visited[p] = visited[x] + 1;
					q.offer(p);
				}
			}
		}
	}

}
