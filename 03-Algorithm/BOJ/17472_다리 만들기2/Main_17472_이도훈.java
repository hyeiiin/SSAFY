import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_이도훈 {

	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	static int[] parents;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}

		char mark = 'A';

		ArrayList<ArrayList<Pos>> islands = new ArrayList<>();

		// BFS로 섬 마킹
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '1') {

					islands.add(new ArrayList<>());
					// bfs 실행
					Queue<Pos> queue = new ArrayDeque<>();
					Pos start = new Pos(j, i);
					queue.add(start);

					map[i][j] = mark;
					islands.get(mark - 'A').add(start);

					while (!queue.isEmpty()) {
						Pos cur = queue.poll();

						for (int[] dir : dirs) {
							int mx = dir[0] + cur.x;
							int my = dir[1] + cur.y;

							if (mx < 0 || my < 0 || mx >= M || my >= N) {
								continue;
							}
							if (map[my][mx] != '1') {
								continue;
							}

							map[my][mx] = mark;
							Pos isl = new Pos(mx, my);
							queue.add(isl);
							islands.get(mark - 'A').add(isl);
						}

					}

					mark++;
				}
			}
		}

		int islandCnt = islands.size();

		parents = new int[islandCnt];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();

		// 마킹한 섬들 돌면서 각 섬까지 닿는지 거리를 배열로 저장
		for (ArrayList<Pos> island : islands) {
			for (Pos pos : island) {

				int curMark = map[pos.y][pos.x];

				for (int[] dir : dirs) {
					int mx = dir[0] + pos.x;
					int my = dir[1] + pos.y;

					if (mx < 0 || my < 0 || mx >= M || my >= N) {
						continue;
					}
					if (map[my][mx] != '0') {
						continue;
					}

					// 섬까지의 거리는 2칸 이상 이여야 한다.
					int dist = 0;

					// 다른 섬까지 이동
					while (true) {
						mx += dir[0];
						my += dir[1];

						if (mx < 0 || my < 0 || mx >= M || my >= N) {
							break;
						}

						dist++;

						// 다른 섬에 도착하면 인접배열에 저장
						if (map[my][mx] != '0') {
							if (dist > 1) {
								pq.add(new Node(curMark - 'A', map[my][mx] - 'A', dist));
							}
							break;
						}
					}
				}
			}

		}

		// MST 돌리기

		int cnt = 0;
		int dist = 0;

		while (!pq.isEmpty()) {
			Node poll = pq.poll();

			if (find(poll.to) == find(poll.from)) {
				continue;
			}

			union(poll.to,poll.from);

			dist += poll.dist;
		}

		int base = find(0);

		for (int i = 1; i < parents.length; i++) {
			if (base != find(i)) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(dist);

	}

	// 유니언 파인드 하자

	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa != pb) {
			parents[pb] = pa;
		}
	}

	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}



	static class Pos {

		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Node implements Comparable<Node> {

		int to;
		int from;
		int dist;

		public Node(int to, int from, int dist) {
			this.to = to;
			this.from = from;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(dist, o.dist);
		}
	}


}
