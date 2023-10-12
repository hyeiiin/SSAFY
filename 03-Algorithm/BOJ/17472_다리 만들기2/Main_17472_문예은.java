import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_문예은 {
	static class Bridge{
		int startI,endI,len; // 시작 섬번호, 끝 섬번호, 다리 길이
		public Bridge(int startI, int endI, int len) {
			this.startI = startI;
			this.endI = endI;
			this.len = len;
		}
	}
	static int N,M;
	static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[] parent;
	public static void main(String[] args) throws IOException {
		// 다리만들기2
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int num;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				num = Integer.parseInt(st.nextToken()); 
				if (num == 1) {
					map[n][m] = -1; // 초기 섬 영역을 -1로 변환
				} else {
					map[n][m] = 0;
				}
			}
		}
		// 1. 섬 영역 구하기
		Queue<int[]> queue = new ArrayDeque<int[]>();
		int islandNum = 1; // 각 섬 번호
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m]==0) continue;
				if (map[n][m]==-1) {
					queue.add(new int[] {n,m});
					map[n][m] = islandNum;
					
					while (!queue.isEmpty()) {
						int[] cur = queue.poll();
						for (int i = 0; i < 4; i++) {
							int nextR = cur[0] + del[i][0];
							int nextC = cur[1] + del[i][1];
							if (isIn(nextR, nextC)) {
								if (map[nextR][nextC]==0) continue;
								if (map[nextR][nextC]==-1) {
									queue.add(new int[] {nextR,nextC});
									map[nextR][nextC] = islandNum;
								}
							}
						}
					}
					islandNum++;
				}
			}
		}
		// 2. 생성 가능한 모든 다리 구하기
		PriorityQueue<Bridge> bridgePQ = new PriorityQueue<>((o1,o2)-> {
			return o1.len - o2.len;
		});
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m]==0) continue;
				for (int i = 0; i < 4; i++) {
					int nextR = n + del[i][0];
					int nextC = m + del[i][1];
					if (!isIn(nextR, nextC) || map[nextR][nextC]!=0) continue;
					int bridgeLen = 1;
					while (true) {
						nextR += del[i][0];
						nextC += del[i][1];
						if (!isIn(nextR, nextC)) {
							break;
						}
						
						if (map[nextR][nextC]!=0) {
							if (bridgeLen > 1) {
								bridgePQ.add(new Bridge(map[n][m], map[nextR][nextC], bridgeLen));
								break;
							} else {
								break;
							}
						} else if (map[nextR][nextC]==0) {
							bridgeLen++;
						}
					}
				}
			}
		}
		// 3. 모든 섬이 연결되는지 파악
		parent = new int[islandNum];
		for (int i = 1; i < islandNum; i++) {
			parent[i] = i;
		}
		int answer = 0;
		while (!bridgePQ.isEmpty()) {
			Bridge bridge = bridgePQ.poll();
			int start = bridge.startI;
			int end = bridge.endI;
			int len = bridge.len;
			if (find(start) != find(end)) {
				answer += len;
				union(start, end);
			}
		}
		// 4. 연결되는 다리 최소합 파악, 연결되지 않으면 0 출력
		int p = parent[1]; // 다 연결되었다면 1번 섬이 모든 섬의 부모로 설정됨
		for (int i = 2; i < islandNum; i++) {
			if (p != find(parent[i])) { // 해당 섬의 부모가 1번이 아니면
				answer = -1; // 0으로 재변경
			}
		}
		System.out.println(answer);
	}
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x==y) return;
		if (x < y) 
			parent[y] = x;
		else parent[x] = y; 
	}
	private static int find(int x) {
		if (x == parent[x]) return x;
		return find(parent[x]);
	}
	private static boolean isIn(int r, int c) {
		if (r >= N || r < 0 || c >= M || c < 0) {
			return false;
		}
		return true;
	}
	/*
	 * 1. 섬 영역 구하기 bfs - 0,0부터 1발견하면 사방탐색 확장하면서 동일영역 표시, 1씩 증가
	 * 2. 모든 다리 구하기 - 0,0부터 수평,수직확장, 다른 번호의 섬 만날 때까지 이동,
	 * 				 섬1 섬2 다리길이 로 저장, 
	 * 				동일한 섬번호 만나거나, 이미 있는 섬클래스 값이거나, 범위 넘어가면 건너뛰기 
	 * 3. 다리 조합 - 
	 *    모든 섬이 연결되는지 파악 bfs
	 * 4. 연결되는 다리 중 최소값 파악
	 * 
	 * 1*3 지도부터 10*10 지도까지 주어짐
	 */
}
