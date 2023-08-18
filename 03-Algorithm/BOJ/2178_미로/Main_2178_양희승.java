import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_2178 {
	
	static int N, M;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] graph;
	static Queue<int[]> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// N: 행 | M: 열 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
        // 미로 입력
		graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				graph[i][j] = input.charAt(j) - '0';
			}
		}
		
        // 출발 위치부터 탐색 시작
		System.out.println(BFS(0, 0));	
	}
	
    // BFS 탐색
	private static int BFS(int X, int Y) {
		
        // queue 에 {x, y} 좌표를 배열 형태로 삽입
		queue.add(new int[] {X, Y});

        // queue 가 빌 때 까지 반복
		while (queue.size() != 0) {

            // queue.popleft --> x, y 변수 저장
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];

            // 상 하 좌 우 탐색
			for (int i = 0; i < 4; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

                // 미로 범위를 벗어 날 경우
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                // 벽일 경우
				if (graph[nx][ny] == 0) continue;

                // 통로일 경우 탐색
				if (graph[nx][ny] == 1) {
					graph[nx][ny] = graph[x][y] + 1;
					queue.add(new int[] {nx, ny});
					
				}
			}
		}
		
        // 도착위치 return
		return graph[N-1][M-1];
	}
}
