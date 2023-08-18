package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17135_박정인 {
	static class Position implements Comparable<Position> {
		int x, y, dist;

		public Position(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Position o) {
			if (this.dist != o.dist) {
				return Integer.compare(this.dist, o.dist);
			}

			return Integer.compare(this.y, o.y);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Position other = (Position) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Position [x=" + x + ", y=" + y + ", dist=" + dist + "]";
		}
		
		
	}

	static int N, M, D, map[][], copy[][];
	static int cnt, enemyCnt, max = Integer.MIN_VALUE, archer[];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static boolean[][] visited;

	static final int ENEMY = 1;
	static final int BLANK = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M];
		copy = new int[N + 1][M];
		visited = new boolean[N + 1][M];
		archer = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == ENEMY) {
					enemyCnt++;
				}
			}
		}
		
		comb(0, 0);

		System.out.println(max);
	}

	private static void comb(int cnt, int start) {
		if (cnt == 3) {
			copy();
			max = Math.max(max, round());

			return;
		}

		for (int i = start; i < M; i++) {
			archer[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	private static void copy() {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}				
	}
	
	private static int round() {
		int total = 0;
		cnt = enemyCnt;
		while (cnt > 0) {
			int kill = 0;
			// 궁수 공격 >> 대상 선정 >> bfs
			// 제거 대상 저장 Set >> 동일한 적이 대상일 수 있으니 한번에 처리
			Set<Position> set = new HashSet<>();

			for (int pos : archer) {
				Position enemy = selectEnemy(pos);
				if (enemy != null)
					set.add(enemy);
			}						

			// 궁수가 적 제거
			for (Position p : set) {				
				copy[p.x][p.y] = BLANK;
				kill++;
				cnt--;
//				System.out.println(p);
			}
//			System.out.println();
			
			// 나머지 적 이동, 성에 도달하면 제거
			// 적의 위치를 저장하는 배열을 두고 해당 배열에서 제거해 가면서 list.size() == 0이면 종료하고 
			// 이동할때도 해당 적의 위치들만 이동하는 형태로 하면 시간이 더 줄어들지 않을까?
			move();
			total += kill;
		}

		return total;
	}
	
	
	private static void move() {		
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == ENEMY) {
					copy[i][j] = BLANK;
					if (i == N - 1) {	// 성에 도달하는 적
						cnt--;
						continue;
					}
					copy[i + 1][j] = ENEMY;
				}
			}
		}				
	}

	private static Position selectEnemy(int y) {
		int x = N;		

		int min = Integer.MAX_VALUE;
		PriorityQueue<Position> pq = new PriorityQueue<>();
		Queue<Position> q = new ArrayDeque<>();
		for (int i = 0; i <= N; i++) {
			Arrays.fill(visited[i], false);
		}
		visited[x][y] = true;
		q.offer(new Position(x, y, 0));

		while (!q.isEmpty()) {
			Position now = q.poll();
			
			if (now.dist == D) {
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (!isRange(nx, ny))
					continue;
				
				if (now.dist + 1 <= D && now.dist + 1 <= min && copy[nx][ny] == ENEMY) {
					// min을 정수범위 최대값으로 해서 now.dist + 1 <= min이 처음에는 별 의미 없도록 하고
					// 다른 조건들이 만족해서 우선순위 큐에 Position이 들어가면 해당 값과 거리가 작거나 같은것들만 받을 수 있도록 하여
					// 일종의 가지치기
					min = now.dist + 1;		
					pq.offer(new Position(nx, ny, now.dist + 1));
				}
				
				if (visited[nx][ny])	continue;
				
				q.offer(new Position(nx, ny, now.dist + 1));
				visited[nx][ny] = true;				
			}
		}
		
		if (pq.size() == 0)	return null;
		return pq.poll();
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x <= N && y >= 0 && y < M;
	}
}
