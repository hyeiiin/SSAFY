package baekjoon;

import java.util.*;
import java.io.*;

public class Main_4485_김태훈 {

	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { -1, 0, 1, 0 };
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int N, map[][], gomap[][];

	static void init() throws IOException {

		map = new int[N][N];
		gomap = new int[N][N];
		for (int i = 0; i < N; i++) {
            Arrays.fill(gomap[i], Integer.MAX_VALUE);
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
	}

	static void bfs() {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		gomap[0][0] = map[0][0];
		que.offer(new int[] { 0, 0 });

		while (!que.isEmpty()) {
			int size = que.size();
			for (int q = 0; q < size; q++) {

				int[] a = que.poll();
				int x = a[0];
				int y = a[1];

				for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(check(nx, ny)) {
                        int sum = gomap[x][y] + map[nx][ny];

                        if (gomap[nx][ny] > sum) {
                            gomap[nx][ny] = sum;
                            que.offer(new int[] { nx, ny }); 
                        }
                    }
                }
			}
		}	
		sb.append(gomap[N - 1][N - 1]);
	}

	private static boolean check(int nx, int ny) {

		return nx >= 0 && nx < N && ny >= 0 && ny < N;
	}

	public static void main(String[] args) throws IOException {
		int T = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			sb.append("Problem ").append(T++).append(": ");
			init();
			bfs();
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
