import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4485_이세은 {

	static int n, min = Integer.MAX_VALUE;
	static int[][] map, minus;
	static boolean[][] visited;
	static int[] moveR = { -1, 1, 0, 0 };
	static int[] moveC = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int problem = 1;
		while (true) {
			n = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			if (n == 0)
				return;
			else {
				min = Integer.MAX_VALUE;
				map = new int[n][n];
				minus = new int[n][n];
				visited = new boolean[n][n];
				for (int i = 0; i < n; i++) {
					StringTokenizer st = new StringTokenizer(br.readLine());
					for (int j = 0; j < n; j++) {
						map[i][j] = Integer.parseInt(st.nextToken());
						minus[i][j] = Integer.MAX_VALUE;
					}
				}
				minus();
				sb.append("Problem ").append(problem++).append(": ").append(min);
				System.out.println(sb.toString());

			}
		}
	}

	private static void minus() {
		
		//오름차순으로 작은 루피를 가지는 좌표 부터 먼저 방문
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2] - o2[2];
			}
		});
		minus[0][0] = map[0][0];
		q.offer(new int[] { 0, 0, minus[0][0]});
		
		while (!q.isEmpty()) {
			int[] val = q.poll();
			int r = val[0];
			int c = val[1];
			int mr = val[2];
			for (int k = 0; k < 4; k++) {
				int newR = r + moveR[k];
				int newC = c + moveC[k];
				if (isValid(newR, newC) && !visited[newR][newC]) {
					visited[r][c] = true;
					
					//잃는 루피가 더 없다면 minus배열 업데이트
					if (minus[newR][newC] > mr + map[newR][newC]) {
						minus[newR][newC] = mr + map[newR][newC];
						q.offer(new int[] {newR, newC, mr+map[newR][newC]});
					}
					
					//끝 좌표까지 왔을 경우 최솟값 업데이트
					if(newR == n-1 && newC == n-1) {
						min = Math.min(min, minus[newR][newC]);
					}
				}
			}

		}

	}

	private static boolean isValid(int r, int c) {
		if (r < 0 || c < 0 || r >= n || c >= n)
			return false;
		return true;
	}

}
