import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600_탁하윤 {
	static int K, W, H, map[][];
	static int[] mx = {0, 0, 1, -1};
	static int[] my = {1, -1, 0, 0};
	static int[] hx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(0, 0, 0, 0);
	}

	private static void bfs(int x, int y, int k, int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, k, cnt});
		boolean[][][] visited = new boolean[H][W][K+1];	// k이동 횟수에 따라 방문처리
		visited[x][y][k] = true;
		int ans = -1;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nx = now[0];
			int ny = now[1];
			int kCnt = now[2];
			int move = now[3];
			if(nx == H-1 && ny == W-1) {	// 도착점이면 while문 빠져나오기
				ans = move;	// 답 갱신
				break;
			}
			
			for (int i = 0; i < 4; i++) {	// 원숭이 이동
				int nmx = nx+mx[i];
				int nmy = ny+my[i];
				
				// 범위 밖 / 장애물 / 방문한 곳이라면 다음 좌표
				if(nmx<0 || nmx>=H || nmy<0 || nmy>=W) continue;
				if(map[nmx][nmy] == 1) continue;
				if(visited[nmx][nmy][kCnt]) continue;
				// q에 담고 방문처리
				q.offer(new int[] {nmx, nmy, kCnt, move+1});
				visited[nmx][nmy][kCnt] = true;
			}
			
			if(kCnt < K) {	// 말처럼 K번 이동할 수 있음
				for (int i = 0; i < 8; i++) {
					int nhx = nx+hx[i];
					int nhy = ny+hy[i];
					
					if(nhx<0 || nhx>=H || nhy<0 || nhy>=W) continue;
					if(map[nhx][nhy] == 1) continue;
					if(visited[nhx][nhy][kCnt+1]) continue;
					q.offer(new int[] {nhx, nhy, kCnt+1, move+1});
					visited[nhx][nhy][kCnt+1] = true;
				}
			}
		}
		System.out.println(ans);
	}

}
