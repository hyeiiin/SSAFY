import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main { // 1697
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, K;  // 수빈, 동생의 위치
	static final int MAX_SIZE = 100001;  // 최대 이동 범위
	static boolean[] visited;  // 방문 배열
	static int res = Integer.MAX_VALUE;  // 최종 결괏값
	
	static class Pos {
		int cur;
		int cnt;
		
		public Pos(int cur, int cnt) {
			this.cur = cur;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[MAX_SIZE];
		
		bfs(N, 0);
		
		sb = new StringBuilder();
		sb.append(res);
		System.out.println(res);

	} // Main
	
	/**
	 * BFS()로 수빈이가 동생을 찾을 수 있는 가장 빠른 시간을 찾는 함수.
	 * DFS()로는 StackOverFlow가 걸려서 BFS()로 풀어야 함.
	 * @param cur : 현재 수빈이의 위치
	 * @param cnt : 초 카운트
	 */
	static void bfs(int cur, int cnt) {
		Deque<Pos> qu = new ArrayDeque<>();
		qu.offer(new Pos(cur, cnt));
		visited[cur] = true;
		
		while(!qu.isEmpty()) {
			Pos p = qu.poll();
			
			// 수빈이가 동생을 찾으면 결괏값 갱신
			if (p.cur == K) {
				res = Math.min(res, p.cnt);
			}
			
			
			// 1. 수빈이의 다음 위치가 범위 내부 && 방문 안함
			if (p.cur + 1 < MAX_SIZE && !visited[p.cur + 1]) {
				visited[p.cur + 1] = true;
				qu.offer(new Pos(p.cur + 1, p.cnt + 1));
			}
			
			// 2. 상동
			if (0 <= p.cur - 1 && !visited[p.cur - 1]) {
				visited[p.cur - 1] = true;
				qu.offer(new Pos(p.cur - 1, p.cnt + 1));
			}
			
			// 3. 상동
			if (p.cur * 2 < MAX_SIZE && !visited[p.cur * 2]) {
				visited[p.cur * 2] = true;
				qu.offer(new Pos(p.cur * 2, p.cnt + 1));
			}
			
		}
		
		
	}
	
}
